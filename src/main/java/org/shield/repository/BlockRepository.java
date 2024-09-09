package org.shield.repository;

import org.shield.entities.Block;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface BlockRepository extends MongoRepository<Block, String> {
    @Query("{ 'field': id }")
    List<Long> findAllId();
    Block findById(long id);
}