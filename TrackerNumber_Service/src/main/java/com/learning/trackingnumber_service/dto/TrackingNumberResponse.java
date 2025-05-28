package com.learning.trackingnumber_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.Instant;

public class TrackingNumberResponse {

    @NotNull
    @Pattern(regexp = "^[A-Z0-9]{1,16}$")
    @JsonProperty("tracking_number")
    private String trackingNumber;

    @NotNull
    @JsonProperty("created_at")
    private Instant createdAt;

    public TrackingNumberResponse() {}

    public TrackingNumberResponse(String trackingNumber,
                                  Instant createdAt){
        this.trackingNumber = trackingNumber;
        this.createdAt = createdAt;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    @Override
    public String toString() {
        return "TrackingNumberResponse{" +
                "trackingNumber='" + trackingNumber + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
