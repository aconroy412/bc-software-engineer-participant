package com.northstar.crm.exception;

import java.util.Map;

public class ErrorResponse {
    private final long timestamp;
    private final int status;
    private final String error;
    private final String message;
    private final String correlationId;
    private final Map<String, String> errors;

    // constructor
    public ErrorResponse(
            int status, String error, String message, String correlationId, Map<String, String> errors) {
        this.timestamp = System.currentTimeMillis();
        this.status = status;
        this.error = error;
        this.message = message;
        this.correlationId = correlationId;
        this.errors = errors;
    }

    //getters
    public long getTimestamp() { return timestamp; }
    public int getStatus() { return status; }
    public String getError() { return error; }
    public String getMessage() { return message; }
    public String getCorrelationId() { return correlationId; }
    public Map<String, String> getFields() { return errors; }
    
    public String toJson() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"timestamp\":").append(timestamp).append(",");
        sb.append("\"status\":").append(status).append(",");
        sb.append("\"error\":\"").append(error).append("\",");
        sb.append("\"message\":\"").append(message).append("\",");
        sb.append("\"correlationId\":\"").append(correlationId).append("\",");
        sb.append("\"errors\":{");
        boolean first = true;
        for (Map.Entry<String, String> entry : errors.entrySet()) {
            if (!first) {
                sb.append(",");
            }
            first = false;
            sb.append("\"").append(entry.getKey()).append("\":\"").append(entry.getValue()).append("\"");
        }
        sb.append("}");
        sb.append("}");
        return sb.toString();
    }
}