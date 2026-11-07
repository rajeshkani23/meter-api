
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
class MeterServiceTest {

    @Mock
    private UrjaPortalClient urjaPortalClient;

    @InjectMocks
    private MeterService meterService;

    @Test
    void shouldRejectEmptyMeterId() {

        assertThrows(
                IllegalArgumentException.class,
                () -> meterService.getMeterLocation("")
        );

        verifyNoInteractions(urjaPortalClient);
    }

    @Test
    void shouldRejectBlankMeterId() {

        assertThrows(
                IllegalArgumentException.class,
                () -> meterService.getMeterLocation("   ")
        );

        verifyNoInteractions(urjaPortalClient);
    }

    @Test
    void shouldAcceptValidMeterIdForLocation() throws Exception {

        meterService.getMeterLocation("J100001");

        verify(urjaPortalClient).getMeterLocation("J100001");
    }

    @Test
    void shouldAcceptValidMeterIdForEnergy() throws Exception {

        meterService.getMeterEnergy("J100001");

        verify(urjaPortalClient).getMeterEnergy("J100001");
    }
}
