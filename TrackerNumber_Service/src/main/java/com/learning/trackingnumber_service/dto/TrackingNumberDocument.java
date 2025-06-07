package com.learning.trackingnumber_service.dto;

import jakarta.validation.constraints.Pattern;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

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

    private final String origin_country_id;
    private final String destination_country_id;
    private final Instant created_at;
    private final double weight;
    private final UUID customer_id;
    private final String customer_name;
    private final String customer_slug;



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

    public void setTracking_number(@Pattern(regexp = "^[A-Z0-9]{1,16}$") String tracking_number) {
        this.tracking_number = tracking_number;
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

    @Override
    public int hashCode() {
        return Objects.hash(tracking_number);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
