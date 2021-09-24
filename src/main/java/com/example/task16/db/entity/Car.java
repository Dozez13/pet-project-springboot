package com.example.task16.db.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;


/**
 * Car entity
 *
 * @author Pavlo Manuilenko
 */
@Entity

@Table(name = "car_info")
@Getter
@Setter
public class Car implements Serializable {
    private static final long serialVersionUID = -1473879605284728654L;
    @Id
    @GeneratedValue
    @Column(name = "carid",columnDefinition = "BINARY(16)")
    private UUID carId ;
    @Column(name = "numofPas")
    private Integer numOfPas;
    @Enumerated(EnumType.STRING)
    @Column(name = "carstate")
    private CarState carState;
    @Column(name = "carname", length = 25)
    private String carName;
    @Column(name = "carimage")
    @Lob
    private byte[] carImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carcategory")
    private CarCategory carCategory;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(getCarId(), car.getCarId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCarId());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Car{");
        sb.append("carId=").append(carId);
        sb.append(", numOfPas=").append(numOfPas);
        sb.append(", carState='").append(carState).append('\'');
        sb.append(", carName='").append(carName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
