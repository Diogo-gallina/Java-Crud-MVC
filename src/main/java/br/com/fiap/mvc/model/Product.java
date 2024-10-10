package br.com.fiap.mvc.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "TB_CP_CRUD_MVC_PRODUCT")
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name", length = 70, nullable = false)
    private String name;

    @Column(name="description", length = 200, nullable = false)
    private String description;

    @Column(name = "value", precision = 5)
    private Double value;

    @Column(name = "stock_quantity")
    private Integer stockQuantity;

    @Column(name="is_available")
    private Boolean isAvailable;

    @Column(name="category")
    private ProductCategory category;
}
