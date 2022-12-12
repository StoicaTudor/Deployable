package ro.tuc.ds2020.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.entity.Device;
import ro.tuc.ds2020.entity.EnergyConsumption;
import ro.tuc.ds2020.entity.User;
import ro.tuc.ds2020.entity.UserRole;
import ro.tuc.ds2020.repository.DeviceRepository;
import ro.tuc.ds2020.repository.EnergyConsumptionRepository;
import ro.tuc.ds2020.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

/**
 * this sht does not entirely work
 */
@Service
public class TestDataLoader {

    private final UserRepository userRepository;
    private final DeviceRepository deviceRepository;
    private final EnergyConsumptionRepository energyConsumptionRepository;

    @Autowired
    public TestDataLoader(
            UserRepository userRepository,
            DeviceRepository deviceRepository,
            EnergyConsumptionRepository energyConsumptionRepository) {

        this.userRepository = userRepository;
        this.deviceRepository = deviceRepository;
        this.energyConsumptionRepository = energyConsumptionRepository;
    }

    private final List<EnergyConsumption> energyConsumptions = Arrays.asList(

            new EnergyConsumption().setConsumption(5)
                    .setDate(LocalDateTime.of(2022, 1, 1, 3, 0)),

            new EnergyConsumption().setConsumption(10)
                    .setDate(LocalDateTime.of(2022, 1, 1, 4, 0)),

            new EnergyConsumption().setConsumption(15)
                    .setDate(LocalDateTime.of(2022, 1, 1, 5, 0)),

            new EnergyConsumption().setConsumption(100)
                    .setDate(LocalDateTime.of(2022, 1, 1, 6, 0)),

            new EnergyConsumption().setConsumption(2)
                    .setDate(LocalDateTime.of(2022, 1, 1, 7, 0)),

            new EnergyConsumption().setConsumption(65)
                    .setDate(LocalDateTime.of(2022, 1, 2, 10, 0)),

            new EnergyConsumption().setConsumption(10)
                    .setDate(LocalDateTime.of(2022, 1, 2, 11, 0)),

            new EnergyConsumption().setConsumption(20)
                    .setDate(LocalDateTime.of(2022, 1, 2, 12, 0)),

            new EnergyConsumption().setConsumption(34)
                    .setDate(LocalDateTime.of(2022, 1, 2, 13, 0)),

            new EnergyConsumption().setConsumption(0)
                    .setDate(LocalDateTime.of(2022, 1, 2, 14, 0))
    );

    private final List<Device> devices = Arrays.asList(

            new Device().setName("device0")
                    .setDescription("description0")
                    .setMaximumHourlyEnergyConsumption(200)
                    .setEnergyConsumption(energyConsumptions)
    );

    private final List<User> users = Arrays.asList(

            new User().setName("user0")
                    .setPassword("pass0")
                    .setRoles(new HashSet<>(Arrays.asList(UserRole.ADMIN))),

            new User().setName("user1")
                    .setPassword("pass1")
                    .setRoles(new HashSet<>(Arrays.asList(UserRole.CLIENT)))
    );

    public void load() {

        energyConsumptionRepository.saveAll(energyConsumptions);
        userRepository.saveAll(users);

        devices.get(0).setUser(userRepository.findByName("user1").orElse(null));
        deviceRepository.saveAll(devices);

        User user1 = Objects.requireNonNull(userRepository.findByName("user1").orElse(null))
                .setDevices(devices);
        userRepository.save(user1);
    }
}
