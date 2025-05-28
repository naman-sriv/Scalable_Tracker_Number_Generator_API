package com.learning.trackingnumber_service.dto;

import jakarta.validation.constraints.Pattern;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Document(collection = "tracking_numbers")
public class TrackingNumberDocument {

    @Id
    private String id;

    @Indexed(unique = true)
    @Pattern(regexp = "^[A-Z0-9]{1,16}$")
    private String tracking_number;

    private String origin_country_id;
    private String destination_country_id;
    private Instant created_at;
    private double weight;
    private UUID customer_id;
    private String customer_name;
    private String customer_slug;

    public TrackingNumberDocument() {
    }

    public TrackingNumberDocument(String tracking_number,
                                  String origin_country_id,
                                  String destination_country_id,
                                  Instant created_at,
                                  double weight,
                                  UUID customer_id,
                                  String customer_name,
                                  String customer_slug) {
        this.tracking_number = tracking_number;
        this.created_at = created_at;
        this.origin_country_id = origin_country_id;
        this.destination_country_id = destination_country_id;
        this.weight = weight;
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.customer_slug = customer_slug;
    }

    public String getId() {
        return id;
    }

    public String getTracking_number() {
        return tracking_number;
    }

    public double getWeight() {
        return weight;
    }

    public Instant getCreated_at() {
        return created_at;
    }

    public String getOrigin_country_id() {
        return origin_country_id;
    }

    public String getDestination_country_id() {
        return destination_country_id;
    }

    public UUID getCustomer_id() {
        return customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public String getCustomer_slug() {
        return customer_slug;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTracking_number(String tracking_number) {
        this.tracking_number = tracking_number;
    }

    public void setCreated_at(Instant createdAt) {
        this.created_at = createdAt;
    }

    public void setCustomer_id(UUID customerId) {
        this.customer_id = customerId;
    }

    public void setCustomer_name(String customerName) {
        this.customer_name = customerName;
    }

    public void setCustomer_slug(String customerSlug) {
        this.customer_slug = customerSlug;
    }

    public void setDestination_country_id(String destination_country_id) {
        this.destination_country_id = destination_country_id;
    }

    public void setOrigin_country_id(String origin_country_id) {
        this.origin_country_id = origin_country_id;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tracking_number);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
