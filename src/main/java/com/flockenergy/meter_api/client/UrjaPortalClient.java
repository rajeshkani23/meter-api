
package com.flockenergy.meter_api.client;

import com.flockenergy.meter_api.dto.TransformerPageResponse;
import com.flockenergy.meter_api.dto.MeterLocationResponse;
import com.flockenergy.meter_api.dto.MeterEnergyResponse;

import com.flockenergy.meter_api.exception.PortalException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@Component
public class UrjaPortalClient {

    private final String baseUrl;
    private final String email;
    private final String password;

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    private volatile boolean authenticated = false;

    public UrjaPortalClient(
            @Value("${urja.portal.base-url}") String baseUrl,
            @Value("${urja.portal.email}") String email,
            @Value("${urja.portal.password}") String password) {

        this.baseUrl = baseUrl.replaceAll("/+$", "");
        this.email = email;
        this.password = password;

        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);

        this.httpClient = HttpClient.newBuilder()
                .cookieHandler(cookieManager)
                .followRedirects(HttpClient.Redirect.NEVER)
                .build();

        this.objectMapper = new ObjectMapper();
    }

    // Login to the Urja portal
    public String login() throws IOException, InterruptedException {

        String formData = "email="
                + URLEncoder.encode(email, StandardCharsets.UTF_8)
                + "&password="
                + URLEncoder.encode(password, StandardCharsets.UTF_8);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/login"))
                .header("Accept", "application/json")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Origin", baseUrl)
                .header("Referer", baseUrl + "/login")
                .header("x-sveltekit-action", "true")
                .POST(HttpRequest.BodyPublishers.ofString(formData))
                .build();

        HttpResponse<String> response = httpClient.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        if (response.statusCode() != 200) {
            authenticated = false;
            throw new PortalException(
                    "Failed to fetch transformers. HTTP status: "
                            + response.statusCode()
            );
        }

        // The login action succeeded; CookieManager retains session cookies.
        authenticated = true;

        return response.body();
    }

    // Ensure portal authentication before making a request
    private synchronized void ensureAuthenticated()
            throws IOException, InterruptedException {

        if (!authenticated) {
            login();
        }
    }

    // Send an authenticated GET request and retry once if the session expires
    private String sendAuthenticatedGet(
            HttpRequest request,
            String operation
    ) throws IOException, InterruptedException {

        ensureAuthenticated();

        HttpResponse<String> response = httpClient.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        // The portal may have expired the existing session.
        if (response.statusCode() == 401 || response.statusCode() == 403) {

            authenticated = false;

            // Log in again.
            ensureAuthenticated();

            // Retry the original request once.
            response = httpClient.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );
        }

        if (response.statusCode() != 200) {
            throw new IllegalStateException(
                    "Failed to " + operation
                            + ". Portal HTTP status: " + response.statusCode()
            );
        }

        return response.body();
    }

    // Fetch meter location
    public MeterLocationResponse getMeterLocation(String meterId)
            throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(
                        baseUrl + "/portal/meters/" + meterId + "/geo"))
                .GET()
                .build();

        String responseBody = sendAuthenticatedGet(
                request,
                "fetch meter location"
        );

        return objectMapper.readValue(
                responseBody,
                MeterLocationResponse.class
        );
    }

    // Fetch meter energy readings
    public MeterEnergyResponse getMeterEnergy(String meterId)
            throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(
                        baseUrl + "/portal/meters/" + meterId + "/energy"))
                .GET()
                .build();

        String responseBody = sendAuthenticatedGet(
                request,
                "fetch meter energy"
        );

        return objectMapper.readValue(
                responseBody,
                MeterEnergyResponse.class
        );
    }

    // Fetch transformers
    public TransformerPageResponse getTransformers(int page)
            throws IOException, InterruptedException {

        if (page < 1) {
            throw new IllegalArgumentException(
                    "Page number must be greater than or equal to 1"
            );
        }

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/portal/dts?page=" + page))
                .GET()
                .build();

        String responseBody = sendAuthenticatedGet(
                request,
                "fetch transformers"
        );

        return objectMapper.readValue(
                responseBody,
                TransformerPageResponse.class
        );
    }
}
