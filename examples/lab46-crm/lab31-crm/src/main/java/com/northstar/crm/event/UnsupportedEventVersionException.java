package com.northstar.crm.event;

/**
 * UnsupportedEventVersionException
 */
public class UnsupportedEventVersionException extends RuntimeException{
    public UnsupportedEventVersionException() {
        super("Unsupported customer event version");
    }
}
