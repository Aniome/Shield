package org.shield.repository;

import jakarta.validation.constraints.NotEmpty;
import org.shield.entities.UserBlockchain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserBlockchain, Long> {
    Optional<UserBlockchain> findByUsername(String username);
    @SuppressWarnings("SpringDataMethodInconsistencyInspection")
    List<UserBlockchain> findByUsernameAndAndRole(@NotEmpty String username, String role);
    @Query(value = "SELECT id FROM UserBlockchain")
    List<Long> findAllId();
    @Query(value = "UPDATE UserBlockchain SET password=UserBlockchain.password WHERE UserBlockchain.username=username")
    boolean updateByUsernameAndAndPassword(@NotEmpty String username, @NotEmpty String password);
}
