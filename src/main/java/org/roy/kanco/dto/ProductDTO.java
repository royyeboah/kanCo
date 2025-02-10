package org.roy.kanco.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO {

    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private Integer categoryId;
}
