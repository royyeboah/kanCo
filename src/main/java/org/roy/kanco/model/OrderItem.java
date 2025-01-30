package org.roy.kanco.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name="order_id", nullable = false)
    @JsonIgnoreProperties("products")
    private Order order;

    @ManyToOne
    @JoinColumn(name="product_id", nullable = false)
    @JsonIgnoreProperties("orderItems")
    private Product product;

    private String productName;

    private Double productPrice;

    private Integer quantity;
}
