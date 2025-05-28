package com.learning.trackingnumber_service.repository;

import com.learning.trackingnumber_service.dto.TrackingNumberDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrackingNumberRepository extends ReactiveMongoRepository<TrackingNumberDocument,String> {

}
