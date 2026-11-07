
package com.flockenergy.meter_api.service;

import com.flockenergy.meter_api.client.UrjaPortalClient;
import com.flockenergy.meter_api.dto.MeterLocationResponse;
import com.flockenergy.meter_api.dto.MeterEnergyResponse;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MeterService {

    private final UrjaPortalClient urjaPortalClient;

    public MeterService(UrjaPortalClient urjaPortalClient) {
        this.urjaPortalClient = urjaPortalClient;
    }

    public MeterLocationResponse getMeterLocation(String meterId)
            throws IOException, InterruptedException {

        validateMeterId(meterId);

        return urjaPortalClient.getMeterLocation(meterId);
    }

    public MeterEnergyResponse getMeterEnergy(String meterId)
            throws IOException, InterruptedException {

        validateMeterId(meterId);

        return urjaPortalClient.getMeterEnergy(meterId);
    }

    private void validateMeterId(String meterId) {

        if (meterId == null || meterId.isBlank()) {
            throw new IllegalArgumentException(
                    "Meter ID must not be empty"
            );
        }
    }
}
