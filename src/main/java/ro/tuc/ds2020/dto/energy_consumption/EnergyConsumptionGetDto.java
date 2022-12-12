package ro.tuc.ds2020.dto.energy_consumption;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

public class EnergyConsumptionGetDto {

    @NotNull
    private UUID id;

    @NotNull
    private LocalDateTime date;

    @NotNull
    private int consumption;


    public UUID getId() {
        return id;
    }

    public EnergyConsumptionGetDto setId(UUID id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public EnergyConsumptionGetDto setDate(LocalDateTime date) {
        this.date = date;
        return this;
    }

    public int getConsumption() {
        return consumption;
    }

    public EnergyConsumptionGetDto setConsumption(int consumption) {
        this.consumption = consumption;
        return this;
    }
}
