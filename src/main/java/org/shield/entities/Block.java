package org.shield.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Block {
    @Id
    private Long id;

    Long timestamp;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date dateOfManufacture;

    Long proof;

    @NotEmpty()
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    String manufacturer;

    @Size(min = 2, max = 30, message = "nameOfProduct should be between 2 and 30 characters")
    String nameOfProduct;

    Long previousHash;

    @Size(min = 2, max = 30, message = "productDescription should be between 2 and 30 characters")
    String productDescription;
}
