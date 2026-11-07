
package com.flockenergy.meter_api.service;

import com.flockenergy.meter_api.client.UrjaPortalClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
class TransformerServiceTest {

    @Mock
    private UrjaPortalClient urjaPortalClient;

    @InjectMocks
    private TransformerService transformerService;

    @Test
    void shouldRejectPageZero() {

        assertThrows(
                IllegalArgumentException.class,
                () -> transformerService.getTransformers(0)
        );

        verifyNoInteractions(urjaPortalClient);
    }

    @Test
    void shouldRejectNegativePage() {

        assertThrows(
                IllegalArgumentException.class,
                () -> transformerService.getTransformers(-1)
        );

        verifyNoInteractions(urjaPortalClient);
    }

    @Test
    void shouldAcceptPageOne() throws Exception {

        transformerService.getTransformers(1);

        verify(urjaPortalClient).getTransformers(1);
    }

    @Test
    void shouldAcceptPageTwo() throws Exception {

        transformerService.getTransformers(2);

        verify(urjaPortalClient).getTransformers(2);
    }

}
