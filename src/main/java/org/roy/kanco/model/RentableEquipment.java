package org.roy.kanco.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@DiscriminatorValue("EQUIPMENT")
@Getter
@Setter
public class RentableEquipment extends Product{

    private Double rentalRate;
    private Boolean availability;

}
