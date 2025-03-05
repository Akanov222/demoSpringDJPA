package edu.demosql.first.repository.domain;

import lombok.*;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
public class Product {

    private Long productId;
    private String maker;
    private int model;
    private String type;

    public Product() {
    }

    public Product(String maker, int model, String type) {
        this.maker = maker;
        this.model = model;
        this.type = type;
    }

    public Product(Long productId, String maker, int model, String type) {
        this.productId = productId;
        this.maker = maker;
        this.model = model;
        this.type = type;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public int getModel() {
        return model;
    }

    public void setModel(int model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return
                maker + ',' +
                model + ',' +
                type;
    }
}
