
package com.flockenergy.meter_api.controller;

import com.flockenergy.meter_api.dto.TransformerPageResponse;
import com.flockenergy.meter_api.service.TransformerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/transformers")
public class TransformerController {

    private final TransformerService transformerService;

    public TransformerController(TransformerService transformerService) {
        this.transformerService = transformerService;
    }

    @GetMapping
    public TransformerPageResponse getTransformers(
            @RequestParam(defaultValue = "1") int page)
            throws IOException, InterruptedException {

        return transformerService.getTransformers(page);
    }
}
