package org.roy.kanco.model;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@DiscriminatorValue("HARDWARE")
@Getter
@Setter
public class Hardware extends Product{
}
