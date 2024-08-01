package org.shield.repository;

import org.shield.entities.Block;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlockRepository extends JpaRepository<Block, Long> {
    @Query(value = "SELECT id FROM Block")
    List<Long> findAllId();
}
