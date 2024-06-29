package org.shield.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.HashMap;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Block {
    @Id
    private Long id;

    Long timestamp;

    @NotNull
    Date dateOfManufacture;

    Long proof;

    @NotEmpty()
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    String manufacturer;

    @Size(min = 2, max = 30, message = "nameOfProduct should be between 2 and 30 characters")
    String nameOfProduct;

    String previousHash;

    @Size(min = 2, max = 30, message = "productDescription should be between 2 and 30 characters")
    String productDescription;


    public Block(Long index, Long timestamp, Long proof, Date dateOfManufacture, HashMap<String, String> info){
        this.id = index;
        this.timestamp = timestamp;
        this.proof = proof;
        previousHash = info.get("previousHash");
        this.dateOfManufacture = dateOfManufacture;
        manufacturer = info.get("manufacturer");
        nameOfProduct = info.get("nameOfProduct");
        productDescription = info.get("productDescription");
    }
}
