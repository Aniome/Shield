package org.shield;

import java.util.Date;
import java.util.HashMap;

public class Block {
    int index;
    Date timestamp;
    Date dateOfManufacture;
    int proof;
    String manufacturer;
    String nameOfProduct;
    String previousHash;
    String productDescription;

    public Block(int index, Date timestamp, int proof, Date dateOfManufacture, HashMap<String, String> info){
        this.index = index;
        this.timestamp = timestamp;
        this.proof = proof;
        previousHash = info.get("previousHash");
        this.dateOfManufacture = dateOfManufacture;
        manufacturer = info.get("manufacturer");
        nameOfProduct = info.get("nameOfProduct");
        productDescription = info.get("productDescription");
    }
}
