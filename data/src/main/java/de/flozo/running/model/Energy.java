package de.flozo.running.model;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Embeddable
public class Energy {

    @Column(name = "energy_value")
    private Double value;

    @OneToOne
    @JoinColumn(name = "energy_unit_id")
    private EnergyUnit unit;


}
