package ro.tuc.ds2020.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ro.tuc.ds2020.dto.energy_consumption.EnergyConsumptionCreateDto;
import ro.tuc.ds2020.dto.energy_consumption.EnergyConsumptionGetDto;
import ro.tuc.ds2020.entity.EnergyConsumption;

@Mapper(componentModel = "spring")
@Component
public interface EnergyConsumptionMapper {

    EnergyConsumption energyConsumptionCreateDtoToEnergyConsumption(EnergyConsumptionCreateDto energyConsumptionCreateDto);
    EnergyConsumptionGetDto energyConsumptionToEnergyConsumptionGetDto(EnergyConsumption energyConsumption);
}
