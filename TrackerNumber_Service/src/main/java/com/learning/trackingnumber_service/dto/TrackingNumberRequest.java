package com.learning.trackingnumber_service.dto;

import jakarta.validation.constraints.*;

import java.time.Instant;
import java.util.UUID;

public class TrackingNumberRequest {

    @NotBlank
    @Size(min=2,max=2)
    @Pattern(regexp = "^[A-Z]{2}$")
    private String origin_country_id;

    @NotBlank
    @Size(min=2,max=2)
    @Pattern(regexp = "^[A-Z]{2}$")
    private String destination_country_id;

    @NotNull
    private Instant created_at;

    @NotNull
    @DecimalMin(value="0.001")
    @DecimalMax(value = "999.999")
    private double weight;

    @NotNull
    private UUID customer_id;

    @NotNull
    @Size(min=5,max=50)
    private String customer_name;

    @NotNull
    @Size(min=5,max=50)
    private String customer_slug;

    public TrackingNumberRequest() {}

    public TrackingNumberRequest(String origin_country_id,
                                 String destination_country_id,
                                 Instant created_at,
                                 double weight,
                                 UUID customer_id,
                                 String customer_name,
                                 String customer_slug) {
        this.created_at = created_at;
        this.customer_name = customer_name;
        this.weight = weight;
        this.customer_slug = customer_slug;
        this.customer_id = customer_id;
        this.origin_country_id = origin_country_id;
        this.destination_country_id = destination_country_id;
    }

    public void setOrigin_country_id(String origin_country_id) {
        this.origin_country_id = origin_country_id;
    }

    public void setDestination_country_id(String destination_country_id) {
        this.destination_country_id = destination_country_id;
    }

    public void setCustomer_slug(String customerSlug) {
        this.customer_slug = customerSlug;
    }

    public void setCustomer_name(String customerName) {
        this.customer_name = customerName;
    }

    public void setCustomer_id(UUID customerId) {
        this.customer_id = customerId;
    }

    public void setCreated_at(Instant createdAt) {
        this.created_at = createdAt;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public UUID getCustomer_id() {
        return customer_id;
    }

    public String getCustomer_slug() {
        return customer_slug;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public Instant getCreated_at() {
        return created_at;
    }

    public double getWeight() {
        return weight;
    }

    public String getDestination_country_id() {
        return destination_country_id;
    }

    public String getOrigin_country_id() {
        return origin_country_id;
    }

    @Override
    public String toString() {
        return "TrackingNumberRequest{" +
                "origin_country_id='" + origin_country_id + '\'' +
                ", destination_country_id='" + destination_country_id + '\'' +
                ", createdAt=" + created_at +
                ", weight=" + weight +
                ", customerId=" + customer_id +
                ", customerName='" + customer_name + '\'' +
                ", customerSlug='" + customer_slug + '\'' +
                '}';
    }
}
