
package com.flockenergy.meter_api.controller;

import com.flockenergy.meter_api.service.MeterService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MeterController.class)
class MeterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MeterService meterService;

    @Test
    void shouldReturnOkWhenMeterLocationIsRequested()
            throws Exception {

        mockMvc.perform(
                get("/api/meters/J100001/location")
        ).andExpect(status().isOk());

        verify(meterService).getMeterLocation("J100001");
    }

    @Test
    void shouldReturnOkWhenMeterEnergyIsRequested()
            throws Exception {

        mockMvc.perform(
                get("/api/meters/J100001/energy")
        ).andExpect(status().isOk());

        verify(meterService).getMeterEnergy("J100001");
    }
}
