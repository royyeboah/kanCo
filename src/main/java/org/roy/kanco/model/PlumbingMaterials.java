package org.roy.kanco.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@DiscriminatorValue("PLUMBING_MATERIALS")
@Getter
@Setter
public class PlumbingMaterials extends Product{
}
