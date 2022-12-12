package ro.tuc.ds2020.service;

import org.springframework.stereotype.Service;
import ro.tuc.ds2020.dto.device.DeviceCreateDto;
import ro.tuc.ds2020.dto.device.DeviceGetDto;
import ro.tuc.ds2020.dto.device.DeviceUpdateDto;
import ro.tuc.ds2020.dto.energy_consumption.EnergyConsumptionGetDto;
import ro.tuc.ds2020.entity.Device;
import ro.tuc.ds2020.entity.EnergyConsumption;
import ro.tuc.ds2020.mapper.DeviceMapper;
import ro.tuc.ds2020.mapper.EnergyConsumptionMapper;
import ro.tuc.ds2020.repository.DeviceRepository;
import ro.tuc.ds2020.service.exceptions.DeviceNotFoundException;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DeviceService {

    private final DeviceMapper deviceMapper;
    private final DeviceRepository deviceRepository;
    private final EnergyConsumptionMapper energyConsumptionMapper;

    public DeviceService(
            DeviceMapper deviceMapper,
            DeviceRepository deviceRepository,
            EnergyConsumptionMapper energyConsumptionMapper) {

        this.deviceMapper = deviceMapper;
        this.deviceRepository = deviceRepository;
        this.energyConsumptionMapper = energyConsumptionMapper;
    }

    public DeviceGetDto createDevice(
            DeviceCreateDto deviceCreationDto) {

        Device device = deviceMapper.deviceCreateDtoToDevice(deviceCreationDto);
        deviceRepository.save(device);

        return deviceMapper.deviceToDeviceGetDto(device);
    }

    public DeviceGetDto updateDevice(
            DeviceUpdateDto deviceUpdateDto,
            UUID deviceId) {

        Device device = deviceMapper.deviceUpdateDtoToDevice(deviceUpdateDto);
        deviceRepository.save(device);

        return deviceMapper.deviceToDeviceGetDto(device);
    }

    public DeviceGetDto getDeviceById(UUID deviceId) throws DeviceNotFoundException {

        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new DeviceNotFoundException("ID", deviceId));

        return deviceMapper.deviceToDeviceGetDto(device);
    }

    public void deleteDevice(UUID deviceId) {
        deviceRepository.deleteById(deviceId);
    }

    public List<EnergyConsumptionGetDto> getAscendingOrderedEnergyConsumptions(UUID deviceId) throws DeviceNotFoundException {

        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new DeviceNotFoundException("ID", deviceId));

        List<EnergyConsumption> energyConsumptionList = device.getEnergyConsumption()
                .stream()
                .sorted(Comparator.comparing(EnergyConsumption::getDate))
                .collect(Collectors.toList());

        return energyConsumptionList.stream()
                .map(energyConsumptionMapper::energyConsumptionToEnergyConsumptionGetDto)
                .collect(Collectors.toList());
    }

    public List<DeviceGetDto> getAllDevices() {

        List<Device> devices = deviceRepository.findAll();

        return devices.stream()
                .map(deviceMapper::deviceToDeviceGetDto)
                .collect(Collectors.toList());
    }

    public DeviceGetDto createEmptyDevice() {

        Device device = new Device();
        deviceRepository.save(device);

        return deviceMapper.deviceToDeviceGetDto(device);
    }
}
