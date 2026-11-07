package com.flockenergy.meter_api;

import com.flockenergy.meter_api.client.UrjaPortalClient;
import com.flockenergy.meter_api.dto.TransformerPageResponse;
import com.flockenergy.meter_api.dto.MeterLocationResponse;
import com.flockenergy.meter_api.dto.MeterEnergyResponse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assumptions;

class UrjaPortalIntegrationTest {

    @Test
    void testPortalIntegration() throws Exception {

        String email = System.getenv("URJA_PORTAL_EMAIL");
        String password = System.getenv("URJA_PORTAL_PASSWORD");

        // Skip the test if credentials are not configured.
        Assumptions.assumeTrue(
                email != null && !email.isBlank()
                        && password != null && !password.isBlank(),
                "Portal credentials are not configured"
        );

        UrjaPortalClient client = new UrjaPortalClient(
                "https://urja-ops.flockenergy.tech",
                email,
                password
        );

        // 1. Login
        String loginResponse = client.login();
        System.out.println("Login response: " + loginResponse);

        // 2. Fetch meter location
        MeterLocationResponse location =
                client.getMeterLocation("J100001");

        System.out.println("Meter location: " + location);

        // 3. Fetch meter energy
        MeterEnergyResponse energy =
                client.getMeterEnergy("J100001");

        System.out.println("Energy readings received: "
                + energy.getData().size());

        // 4. Fetch transformer page 1
        TransformerPageResponse page1 =
                client.getTransformers(1);

        System.out.println("Total transformers: " + page1.getTotal());
        System.out.println("Page: " + page1.getPage());
        System.out.println("Page size: " + page1.getPageSize());
        System.out.println("Records received: " + page1.getData().size());

        // 5. Fetch transformer page 2
        TransformerPageResponse page2 =
                client.getTransformers(2);

        System.out.println("Page 2 records: " + page2.getData().size());
    }
}
