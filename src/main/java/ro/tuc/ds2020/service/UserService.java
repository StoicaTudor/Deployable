package ro.tuc.ds2020.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.dto.device.DeviceGetDto;
import ro.tuc.ds2020.dto.user.UserCreateDto;
import ro.tuc.ds2020.dto.user.UserGetDto;
import ro.tuc.ds2020.dto.user.UserUpdateDto;
import ro.tuc.ds2020.entity.Device;
import ro.tuc.ds2020.entity.User;
import ro.tuc.ds2020.entity.UserRole;
import ro.tuc.ds2020.mapper.DeviceMapper;
import ro.tuc.ds2020.mapper.UserMapper;
import ro.tuc.ds2020.repository.DeviceRepository;
import ro.tuc.ds2020.repository.UserRepository;
import ro.tuc.ds2020.service.exceptions.DeviceNotFoundException;
import ro.tuc.ds2020.service.exceptions.UserNotFoundException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final DeviceRepository deviceRepository;
    private final DeviceMapper deviceMapper;
    private final UserMapper userMapper;

    @Autowired
    public UserService(
            UserRepository userRepository,
            DeviceRepository deviceRepository,
            DeviceMapper deviceMapper,
            UserMapper userMapper) {

        this.userRepository = userRepository;
        this.deviceRepository = deviceRepository;
        this.deviceMapper = deviceMapper;
        this.userMapper = userMapper;
    }

    public UserGetDto createUser(UserCreateDto userCreationDto) {

        User user = userMapper.userCreateDtoToUser(userCreationDto);
        userRepository.save(user);

        return userMapper.userToUserGetDto(user);
    }

    public UserGetDto createEmptyUser() {

        User user = new User().setRoles(new HashSet<>(Arrays.asList(UserRole.CLIENT)));
        userRepository.save(user);

        return userMapper.userToUserGetDto(user);
    }

    public UserGetDto updateUser(UserUpdateDto userUpdateDto, UUID userId) {

        User user = userMapper.userUpdateDtoToUser(userUpdateDto);
        userRepository.save(user);

        return userMapper.userToUserGetDto(user);
    }

    public UserGetDto getUserById(UUID userId) throws UserNotFoundException {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("ID", userId));

        return userMapper.userToUserGetDto(user);
    }

    public List<UserGetDto> getAllUser() {

        List<User> users = userRepository.findAll();

        return users.stream()
                .map(userMapper::userToUserGetDto)
                .collect(Collectors.toList());
    }

    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }

    public void addDeviceToUser(UUID userId, UUID deviceId) throws UserNotFoundException, DeviceNotFoundException {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("ID", userId));

        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new DeviceNotFoundException("ID", deviceId));

        user.getDevices().add(device);
        userRepository.save(user);
    }

    public List<DeviceGetDto> getDevices(UUID userId) throws Exception {

        List<Device> devices = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("No devices found for user with ID" + userId))
                .getDevices();

        return devices.stream()
                .map(deviceMapper::deviceToDeviceGetDto)
                .collect(Collectors.toList());
    }
}
