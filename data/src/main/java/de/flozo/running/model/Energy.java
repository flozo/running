package de.flozo.running.model;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Energy extends BaseEntity {

    private Double value;
    private EnergyUnit unit;


}
