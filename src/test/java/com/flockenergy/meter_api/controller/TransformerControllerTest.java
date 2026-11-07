
package com.flockenergy.meter_api.controller;

import com.flockenergy.meter_api.service.TransformerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransformerController.class)
class TransformerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TransformerService transformerService;

    @Test
    void shouldReturnOkWhenPageOneIsRequested() throws Exception {

        mockMvc.perform(get("/api/transformers")
                        .param("page", "1"))
                .andExpect(status().isOk());

        verify(transformerService).getTransformers(1);
    }

    @Test
    void shouldUseDefaultPageWhenPageIsNotProvided() throws Exception {

        mockMvc.perform(get("/api/transformers"))
                .andExpect(status().isOk());

        verify(transformerService).getTransformers(1);
    }
}
