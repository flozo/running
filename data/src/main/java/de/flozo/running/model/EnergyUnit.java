package de.flozo.running.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "energy_units")
public class EnergyUnit extends BaseEntity {

    @Column
    private String unitName;

    @Column
    private String unitSymbol;


}
