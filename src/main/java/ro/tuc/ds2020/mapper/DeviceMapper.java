package ro.tuc.ds2020.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ro.tuc.ds2020.dto.device.DeviceCreateDto;
import ro.tuc.ds2020.dto.device.DeviceGetDto;
import ro.tuc.ds2020.dto.device.DeviceUpdateDto;
import ro.tuc.ds2020.entity.Device;

@Mapper(componentModel = "spring")
@Component
public interface DeviceMapper {

    Device deviceCreateDtoToDevice(DeviceCreateDto deviceCreateDto);
    DeviceGetDto deviceToDeviceGetDto(Device device);
    Device deviceUpdateDtoToDevice(DeviceUpdateDto deviceUpdateDto);

    DeviceUpdateDto deviceToDeviceUpdateDto(Device device);
}
