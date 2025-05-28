package com.learning.trackingnumber_service.controller;

import com.learning.trackingnumber_service.dto.TrackingNumberRequest;
import com.learning.trackingnumber_service.dto.TrackingNumberResponse;
import com.learning.trackingnumber_service.service.TrackingNumberGenerationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Instant;

@RestController
@RequestMapping("/api")
public class TrackingNumberServiceController {

    private final TrackingNumberGenerationService trackingNumberGenerationService;

    public TrackingNumberServiceController(TrackingNumberGenerationService trackingNumberGenerationService){
        this.trackingNumberGenerationService = trackingNumberGenerationService;
    }

    @GetMapping("/next-tracking-number")
    public Mono<ResponseEntity<TrackingNumberResponse>> getNestTrackingNumber(@Valid @ModelAttribute TrackingNumberRequest request) {

        return trackingNumberGenerationService.generateTrackingNumber(
                request.getOrigin_country_id(),
                request.getDestination_country_id(),
                request.getCreated_at(),
                request.getWeight(),
                request.getCustomer_id(),
                request.getCustomer_name(),
                request.getCustomer_slug()).map(
                        generatedTrackingInfo -> new TrackingNumberResponse(
                                generatedTrackingInfo.generatedTrackingNumber(),
                                generatedTrackingInfo.trnGeneratedAt()))
                                .map(
                                        response -> ResponseEntity.status(HttpStatus.CREATED).body(response)
                                ).onErrorResume(e->{
                                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                            .body(new TrackingNumberResponse("ERROR", Instant.now())));
                });

    }
}
