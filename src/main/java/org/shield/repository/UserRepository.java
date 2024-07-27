package org.shield.repository;

import org.shield.entities.UserBlockchain;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository<UserBlockchain, Long> {
    List<UserBlockchain> findByUsername(String username);
}
