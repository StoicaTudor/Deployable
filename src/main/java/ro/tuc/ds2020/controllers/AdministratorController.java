package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dto.device.DeviceCreateDto;
import ro.tuc.ds2020.dto.device.DeviceGetDto;
import ro.tuc.ds2020.dto.device.DeviceUpdateDto;
import ro.tuc.ds2020.dto.user.UserCreateDto;
import ro.tuc.ds2020.dto.user.UserGetDto;
import ro.tuc.ds2020.dto.user.UserUpdateDto;
import ro.tuc.ds2020.service.DeviceService;
import ro.tuc.ds2020.service.TestDataLoader;
import ro.tuc.ds2020.service.UserService;
import ro.tuc.ds2020.service.exceptions.DeviceNotFoundException;
import ro.tuc.ds2020.service.exceptions.UserNotFoundException;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(UrlMappings.ADMINISTRATOR)
//@CrossOrigin(origins = "*")
public class AdministratorController {

    private final UserService userService;
    private final DeviceService deviceService;
    private final TestDataLoader testDataLoader;

    @Autowired
    public AdministratorController(
            UserService userService,
            DeviceService deviceService,
            TestDataLoader testDataLoader) {

        this.userService = userService;
        this.deviceService = deviceService;
        this.testDataLoader = testDataLoader;
    }

    @GetMapping(UrlMappings.DUMMY_DATA)
    public ResponseEntity<?> loadDummyData() {

        testDataLoader.load();
        return ResponseEntity.badRequest().build();
    }

    @PostMapping(UrlMappings.CREATE_USER)
    public ResponseEntity<UserGetDto> createUser(@Valid @RequestBody UserCreateDto userCreationDto) {

        UserGetDto userGetDto = userService.createUser(userCreationDto);
        return ResponseEntity.ok(userGetDto);
    }

    //    @PostMapping(UrlMappings.CREATE_EMPTY_USER)
    @RequestMapping(
            value = UrlMappings.CREATE_EMPTY_USER,
            produces = "application/json",
            method = {RequestMethod.POST})
    public ResponseEntity<UserGetDto> createUserEmptyUser() {

        UserGetDto userGetDto = userService.createEmptyUser();
        return ResponseEntity.ok(userGetDto);
    }

    //    @PutMapping(UrlMappings.UPDATE_USER)
    @RequestMapping(
            value = UrlMappings.UPDATE_USER,
            produces = "application/json",
            method = {RequestMethod.PUT})
    public ResponseEntity<?> updateUser(
            @Valid @RequestBody UserUpdateDto userUpdateDto,
            @RequestParam("userId") UUID userId) {

        UserGetDto userGetDto;

        try {
            userGetDto = userService.updateUser(userUpdateDto, userId);
            return ResponseEntity.ok(userGetDto);
        } catch (Exception ignored) {
            return ResponseEntity.badRequest().body("Something went wrong");
        }
    }

    @GetMapping(UrlMappings.GET_USER)
    public ResponseEntity<?> getUser(@RequestParam("userId") UUID userId) {

        UserGetDto user;

        try {
            user = userService.getUserById(userId);
        } catch (UserNotFoundException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

        return ResponseEntity.ok(user);
    }

    @GetMapping(UrlMappings.GET_ALL_USERS)
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUser());
    }

    //    @DeleteMapping(UrlMappings.DELETE_USER)
//    @GetMapping(UrlMappings.DELETE_USER)
    @RequestMapping(
            value = UrlMappings.DELETE_USER,
            method = {RequestMethod.DELETE})
    public ResponseEntity<?> deleteUser(@RequestParam("userId") UUID userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

    @PostMapping(UrlMappings.CREATE_DEVICE)
    public ResponseEntity<DeviceGetDto> createDevice(
            @Valid @RequestBody DeviceCreateDto deviceCreateDto) {

        DeviceGetDto deviceGetDto = deviceService.createDevice(deviceCreateDto);
        return ResponseEntity.ok(deviceGetDto);
    }

    @RequestMapping(
            value = UrlMappings.CREATE_EMPTY_DEVICE,
            produces = "application/json",
            method = {RequestMethod.POST})
    public ResponseEntity<DeviceGetDto> createEmptyDevice() {
        DeviceGetDto deviceGetDto = deviceService.createEmptyDevice();
        return ResponseEntity.ok(deviceGetDto);
    }

    @RequestMapping(
            value = UrlMappings.UPDATE_DEVICE,
            produces = "application/json",
            method = {RequestMethod.PUT})
    public ResponseEntity<?> updateDevice(
            @Valid @RequestBody DeviceUpdateDto deviceUpdateDto,
            @RequestParam("deviceId") UUID deviceId) {

        DeviceGetDto deviceGetDto;

        try {
            deviceGetDto = deviceService.updateDevice(deviceUpdateDto, deviceId);
            return ResponseEntity.ok(deviceGetDto);
        } catch (Exception ignored) {
            return ResponseEntity.badRequest().body("Something went wrong");
        }
    }

    @GetMapping(UrlMappings.GET_DEVICE)
    public ResponseEntity<?> getDeviceById(@RequestParam("deviceId") UUID deviceId) {

        DeviceGetDto device;

        try {
            device = deviceService.getDeviceById(deviceId);
        } catch (DeviceNotFoundException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

        return ResponseEntity.ok(device);
    }

    @GetMapping(UrlMappings.GET_ALL_DEVICES)
    public ResponseEntity<?> getAllDevices() {
        return ResponseEntity.ok(deviceService.getAllDevices());
    }


    @RequestMapping(
            value = UrlMappings.DELETE_DEVICE,
            method = {RequestMethod.DELETE})
    public ResponseEntity<?> deleteDevice(@RequestParam("deviceId") UUID deviceId) {
        deviceService.deleteDevice(deviceId);
        return null;
    }

    @PutMapping(UrlMappings.ADD_DEVICE_TO_USER)
    public ResponseEntity<?> addDeviceToUser(
            @RequestParam("deviceId") UUID deviceId,
            @RequestParam("userId") UUID userId) {

        try {
            userService.addDeviceToUser(deviceId, userId);
        } catch (UserNotFoundException | DeviceNotFoundException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

        return ResponseEntity.ok().build();
    }
}
