package ro.tuc.ds2020.dto.device;

import ro.tuc.ds2020.entity.EnergyConsumption;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

public class DeviceGetDto {

    @NotNull
    private UUID id;

    @NotNull
    @Size(min = 3, max = 20)
    private String name;

    @NotNull
    @Size(min = 3, max = 512)
    private String description;

    @NotNull
    private double maximumHourlyEnergyConsumption;

    private List<EnergyConsumption> energyConsumption;

    public UUID getId() {
        return id;
    }

    public DeviceGetDto setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public DeviceGetDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public DeviceGetDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public double getMaximumHourlyEnergyConsumption() {
        return maximumHourlyEnergyConsumption;
    }

    public DeviceGetDto setMaximumHourlyEnergyConsumption(double maximumHourlyEnergyConsumption) {
        this.maximumHourlyEnergyConsumption = maximumHourlyEnergyConsumption;
        return this;
    }

    public List<EnergyConsumption> getEnergyConsumption() {
        return energyConsumption;
    }

    public DeviceGetDto setEnergyConsumption(List<EnergyConsumption> energyConsumption) {
        this.energyConsumption = energyConsumption;
        return this;
    }
}
