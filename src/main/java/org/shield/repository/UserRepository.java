package org.shield.repository;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.shield.entities.UserBlockchain;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository<UserBlockchain, Long> {
    List<UserBlockchain> findByUsername(String username);
    List<UserBlockchain> findByUsernameAndAndRole(String username, String role);
}
