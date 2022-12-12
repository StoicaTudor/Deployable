package ro.tuc.ds2020.dto.device;

import ro.tuc.ds2020.entity.User;

import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DeviceCreateDto {

    @NotNull
    @Size(min = 3, max = 20)
    private String name;

    @NotNull
    @Size(min = 3, max = 512)
    private String description;

    @NotNull
    private double maximumHourlyEnergyConsumption;

    @OneToOne
    private User user;


    public String getName() {
        return name;
    }

    public DeviceCreateDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public DeviceCreateDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public double getMaximumHourlyEnergyConsumption() {
        return maximumHourlyEnergyConsumption;
    }

    public DeviceCreateDto setMaximumHourlyEnergyConsumption(double maximumHourlyEnergyConsumption) {
        this.maximumHourlyEnergyConsumption = maximumHourlyEnergyConsumption;
        return this;
    }

    public User getUser() {
        return user;
    }

    public DeviceCreateDto setUser(User user) {
        this.user = user;
        return this;
    }
}
