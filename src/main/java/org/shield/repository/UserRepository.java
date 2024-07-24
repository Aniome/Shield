package org.shield.repository;

import org.shield.entities.UserBlockchain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserBlockchain, Long> {
    Optional<UserBlockchain> findByUsername(String username);
    @Query(value = "SELECT role FROM users WHERE :name=users.username", nativeQuery = true)
    List<String> findAllByRoleAndUsername(@Param("name") String name);
}
