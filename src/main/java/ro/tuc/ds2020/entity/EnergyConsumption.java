package ro.tuc.ds2020.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "EnergyConsumption")
public class EnergyConsumption {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    private UUID id;

    @Column(name="date", nullable = false)
    private LocalDateTime date;

    @Column(name="consumption", nullable = false)
    private int consumption;


    public UUID getId() {
        return id;
    }

    public EnergyConsumption setId(UUID id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public EnergyConsumption setDate(LocalDateTime date) {
        this.date = date;
        return this;
    }

    public int getConsumption() {
        return consumption;
    }

    public EnergyConsumption setConsumption(int consumption) {
        this.consumption = consumption;
        return this;
    }
}
