package org.roy.kanco.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("CHEMICALS")
@Builder
public class Chemicals extends Product{

    private String chemicalType;

    private LocalDate expiry_date;

    private Double volume;

}


