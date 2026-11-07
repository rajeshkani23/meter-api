package com.flockenergy.meter_api.service;

import com.flockenergy.meter_api.client.UrjaPortalClient;
import com.flockenergy.meter_api.dto.TransformerPageResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class TransformerService {

    private final UrjaPortalClient urjaPortalClient;

    public TransformerService(UrjaPortalClient urjaPortalClient) {
        this.urjaPortalClient = urjaPortalClient;
    }

    public TransformerPageResponse getTransformers(int page)
            throws IOException, InterruptedException {

        if (page < 1) {
            throw new IllegalArgumentException(
                    "Page number must be greater than or equal to 1"
            );
        }

        return urjaPortalClient.getTransformers(page);
    }
}
