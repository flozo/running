package de.flozo.running.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SuperBuilder
@Entity
@Table(name = "energy_units")
public class EnergyUnit extends BaseEntity {

    @Column
    private String unitName;

    @Column
    private String unitSymbol;


}
