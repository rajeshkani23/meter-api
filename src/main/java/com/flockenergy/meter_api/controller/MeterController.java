
package com.flockenergy.meter_api.controller;

import com.flockenergy.meter_api.service.MeterService;
import com.flockenergy.meter_api.dto.MeterLocationResponse;
import com.flockenergy.meter_api.dto.MeterEnergyResponse;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/meters")
public class MeterController {

    private final MeterService meterService;

    public MeterController(MeterService meterService) {
        this.meterService = meterService;
    }

    // Get meter location
    @GetMapping("/{meterId}/location")
    public MeterLocationResponse getMeterLocation(
            @PathVariable String meterId)
            throws IOException, InterruptedException {

        return meterService.getMeterLocation(meterId);
    }

    // Get meter energy
    @GetMapping("/{meterId}/energy")
    public MeterEnergyResponse getMeterEnergy(
            @PathVariable String meterId)
            throws IOException, InterruptedException {

        return meterService.getMeterEnergy(meterId);
    }
}
