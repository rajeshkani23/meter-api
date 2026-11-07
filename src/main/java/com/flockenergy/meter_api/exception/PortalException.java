
package com.flockenergy.meter_api.exception;

public class PortalException extends RuntimeException {

    public PortalException(String message) {
        super(message);
    }

    public PortalException(String message, Throwable cause) {
        super(message, cause);
    }
}
