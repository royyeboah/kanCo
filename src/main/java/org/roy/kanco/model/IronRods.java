package org.roy.kanco.model;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@DiscriminatorValue("IRON_RODS")
@Getter
@Setter
public class IronRods extends Product{

}
