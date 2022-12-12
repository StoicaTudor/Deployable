package ro.tuc.ds2020.dto.energy_consumption;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

public class EnergyConsumptionCreateDto {

    @NotNull
    private UUID id;

    @NotNull
    private LocalDateTime date;

    @NotNull
    private int consumption;


    public UUID getId() {
        return id;
    }

    public EnergyConsumptionCreateDto setId(UUID id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public EnergyConsumptionCreateDto setDate(LocalDateTime date) {
        this.date = date;
        return this;
    }

    public int getConsumption() {
        return consumption;
    }

    public EnergyConsumptionCreateDto setConsumption(int consumption) {
        this.consumption = consumption;
        return this;
    }
}
