package com.learning.trackingnumber_service.service;

import com.github.f4b6a3.ulid.UlidCreator;
import com.learning.trackingnumber_service.dto.TrackingNumberDocument;
import com.learning.trackingnumber_service.repository.TrackingNumberRepository;
import com.mongodb.DuplicateKeyException;
import org.hibernate.validator.internal.constraintvalidators.hv.LengthValidator;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

@Lazy
@Service
public class TrackingNumberGenerationService {

    private final TrackingNumberRepository trackingNumberRepository;

    public TrackingNumberGenerationService(TrackingNumberRepository trackingNumberRepository
    ){
        this.trackingNumberRepository = trackingNumberRepository;
    }

//    public GeneratedTrackingInfo generateTrackingNumber(String origin_country_id,
//                                         String destination_country_id,
//                                         Instant createdAt,
//                                         double weight,
//                                         UUID customerId,
//                                         String customerName,
//                                         String customerSlug) {
//        String generatedTrackingNumber;
//        Instant trnGeneratedAt;
//        int maxRetries = 3;
//        int retryCount = 0;
//
//        while(retryCount < maxRetries){
//
//            generatedTrackingNumber = generateULID();
//            trnGeneratedAt = Instant.now();
//            TrackingNumberDocument document = new TrackingNumberDocument(
//                    generatedTrackingNumber,
//                    origin_country_id,
//                    destination_country_id,
//                    createdAt,
//                    weight,
//                    customerId,
//                    customerName,
//                    customerSlug
//            );
//            try{
//                trackingNumberRepository.save(document);
//                return new GeneratedTrackingInfo(generatedTrackingNumber,trnGeneratedAt);
//            }catch (DuplicateKeyException e){
//                retryCount++;
//            }catch (Exception e){
//                throw new RuntimeException(e.getMessage());
//            }
//        }
//        throw new RuntimeException("Failed to generate a unique tracking number after " + maxRetries + " retries.");
//    }

    public Mono<GeneratedTrackingInfo> generateTrackingNumber(String origin_country_id,
                                                              String destination_country_id,
                                                              Instant createdAt,
                                                              double weight,
                                                              UUID customerId,
                                                              String customerName,
                                                              String customerSlug) {


        return Mono.defer(()-> {
            String generatedTrackingNumber = generateULID();;
            Instant trnGeneratedAt = Instant.now();

            TrackingNumberDocument document = new TrackingNumberDocument(generatedTrackingNumber,
                                                                        origin_country_id,
                                                                        destination_country_id,
                                                                        createdAt,
                                                                        weight,
                                                                        customerId,
                                                                        customerName,
                                                                        customerSlug);

            return trackingNumberRepository.save(document).thenReturn(new GeneratedTrackingInfo(generatedTrackingNumber,trnGeneratedAt));
        }).retryWhen(Retry.backoff(3, Duration.ofMillis(50))
                .filter(e-> e instanceof DuplicateKeyException)
                .onRetryExhaustedThrow((retryBackoffSpec,retrySignal)
        -> new RuntimeException("Failed to generate a unique tracking number after " + retrySignal.totalRetries() + " retries due to: " + retrySignal.failure().getMessage()))
        ).onErrorResume(e -> {
            // Catch any other exceptions not handled by retryWhen
            // e.g., if you only retried DuplicateKeyException, other DB errors fall here
            return Mono.error(new RuntimeException("Failed to process tracking number generation: " + e.getMessage(), e));
        });
    }


    public String generateULID(){
        String ulid = UlidCreator.getUlid().toString();
        String unique = ulid.substring(ulid.length()-10);
        String prefix = "TRN";
        String generatedId = (prefix+unique).toUpperCase();

        if(generatedId.length() > 16){
            generatedId = generatedId.substring(0,16);
        }
        return generatedId.replaceAll("[^A-Z0-9]","");
    }

    public record GeneratedTrackingInfo(String generatedTrackingNumber, Instant trnGeneratedAt) {}


}
