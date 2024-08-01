package org.shield.repository;

import jakarta.validation.constraints.NotEmpty;
import org.shield.entities.UserBlockchain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserBlockchain, Long> {
    List<UserBlockchain> findByUsername(String username);
    @SuppressWarnings("SpringDataMethodInconsistencyInspection")
    List<UserBlockchain> findByUsernameAndAndRole(@NotEmpty String username, String role);
    @Query(value = "SELECT id FROM UserBlockchain")
    List<Long> findAllId();
}
