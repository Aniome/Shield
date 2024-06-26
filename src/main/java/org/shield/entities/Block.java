package org.shield.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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

    Date dateOfManufacture;

    Long proof;

    String manufacturer;

    String nameOfProduct;

    String previousHash;

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
