package org.shield.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.shield.validation.ValidateUsername;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class UserBlockchain {
    @Id
    private Long id;
    @ValidateUsername
    private String username;
    private String password;
    private String email;
    private String role;
}
