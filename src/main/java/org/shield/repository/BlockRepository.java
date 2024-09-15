package org.shield.repository;

import org.shield.entities.Block;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface BlockRepository extends MongoRepository<Block, String> {
    @Query(value = "{}", fields = "{'_id': 1 }")
    List<String> findAllId();
    Block findAllById(Long id);
}