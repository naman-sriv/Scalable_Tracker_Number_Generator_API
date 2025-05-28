package com.learning.trackingnumber_service.service;

import com.learning.trackingnumber_service.repository.TrackingNumberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.UUID;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TrackingNumberGenerationServiceTest {

    @Mock
    private TrackingNumberRepository trackingNumberRepository;

    @InjectMocks
    private TrackingNumberGenerationService trackingNumberGenerationService;

    private String originCountryId = "IND";
    private String destinationCountryId = "USA";
    private Instant createdAt = Instant.now();
    private double weight = 10.5;
    private UUID customerId = UUID.randomUUID();
    private String customerName = "Test Customer";
    private String customerSlug = "test-customer";

    @Test
    void generateULID_shouldReturnCorrectFormat() {
        String ulid = trackingNumberGenerationService.generateULID();
        assertNotNull(ulid);
        assertTrue(ulid.startsWith("TRN"));
        assertEquals(13,ulid.length());
        assertTrue(ulid.matches("^[A-Z0-9]+$"));
    }

    @Test
    void generateULID_shouldGenerateUniqueIDs() {
        String ulid1 = trackingNumberGenerationService.generateULID();
        String ulid2 = trackingNumberGenerationService.generateULID();

        assertNotEquals(ulid1, ulid2);
    }
}
