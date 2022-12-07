package de.flozo.running.model;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Embeddable
public class Energy extends BaseEntity {

    @Column(name = "energy_value")
    private Double value;

    @Enumerated(EnumType.STRING)
    private EnergyUnit unit;


}
