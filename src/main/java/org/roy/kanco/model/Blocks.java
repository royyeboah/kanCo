package org.roy.kanco.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="blocks")
@DiscriminatorValue("BLOCKS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Blocks extends Product{

    @Enumerated(EnumType.STRING)
    private Size blockSize;

    @Enumerated(EnumType.STRING)
    private BlockType type;

}

enum BlockType{
    HOLLOW, SOLID, CONCRETE;
}

enum Size{
    FOUR_INCHES,
    FIVE_INCHES,
    SIX_INCHES
}


