package de.flozo.running.model;

public class Energy extends BaseEntity {

    private Double value;
    private EnergyUnit unit;

    public Energy() {
    }

    public Energy(Long id, Double value, EnergyUnit unit) {
        super(id);
        this.value = value;
        this.unit = unit;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public EnergyUnit getUnit() {
        return unit;
    }

    public void setUnit(EnergyUnit unit) {
        this.unit = unit;
    }
}
