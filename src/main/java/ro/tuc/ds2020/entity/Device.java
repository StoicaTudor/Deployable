package ro.tuc.ds2020.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Device")
public class Device {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    private UUID id;

    @Column(name = "name", nullable = false, length = 50)
    private String name = "";

    @Column(name = "description", nullable = false, length = 512)
    private String description = "";

    @Column(name = "maximumHourlyEnergyConsumption", nullable = false)
    private double maximumHourlyEnergyConsumption = 0;

    @OneToOne
    private User user;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<EnergyConsumption> energyConsumption;

    public UUID getId() {
        return id;
    }

    public Device setId(UUID id) {
        this.id = id;
        return this;
    }


    public String getName() {
        return name;
    }

    public Device setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Device setDescription(String description) {
        this.description = description;
        return this;
    }

    public double getMaximumHourlyEnergyConsumption() {
        return maximumHourlyEnergyConsumption;
    }

    public Device setMaximumHourlyEnergyConsumption(double maximumHourlyEnergyConsumption) {
        this.maximumHourlyEnergyConsumption = maximumHourlyEnergyConsumption;
        return this;
    }

    public List<EnergyConsumption> getEnergyConsumption() {
        return energyConsumption;
    }

    public Device setEnergyConsumption(List<EnergyConsumption> energyConsumption) {
        this.energyConsumption = energyConsumption;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Device setUser(User user) {
        this.user = user;
        return this;
    }
}
