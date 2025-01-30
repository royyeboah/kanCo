package org.roy.kanco.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@DiscriminatorValue("BUILDING_MATERIALS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BuildingMaterials extends Product{

    @Column(nullable = false)
    private Double volume;

    @Enumerated(EnumType.STRING)
    private materialType buildingMaterialType;

}
