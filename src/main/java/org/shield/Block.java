package org.shield;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.HashMap;

@NoArgsConstructor
@Entity
@Table
public class Block {
    @Getter
    @Setter
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "Timestamp")
    Long timestamp;

    @Column(name = "DateOfManufacture")
    Date dateOfManufacture;

    @Column(name = "Proof")
    Long proof;

    @Column(name = "Manufacturer")
    String manufacturer;

    @Column(name = "NameOfProduct")
    String nameOfProduct;

    @Column(name = "PreviousHash")
    String previousHash;

    @Column(name = "ProductDescription")
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
