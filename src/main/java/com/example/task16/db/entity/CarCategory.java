package com.example.task16.db.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


/**
 * CarCategory entity
 *
 * @author Pavlo Manuilenko
 */
@Entity
@Table(name = "car_category")
@Getter
@Setter
public class CarCategory implements Serializable {

    private static final long serialVersionUID = -4179236910442782235L;
    @Id
    @GeneratedValue
    @Column(name = "id",columnDefinition = "BINARY(16)")
    private UUID id ;
    @Column(name = "carcategoryName", nullable = false, length = 15,unique = true)
    private String carCategoryName;
    @Column(name = "costperOnekilometer")
    private Double costPerOneKilometer;
    @Column(name = "discountperprice")
    private Double discountPerPrice;
    @Column(name = "carcategoryimage")
    @Lob
    private byte[] carCategoryImage;
    @OneToMany(mappedBy = "carCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Car> cars = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarCategory that = (CarCategory) o;
        return Objects.equals(getCarCategoryName(), that.getCarCategoryName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCarCategoryName());
    }


}
