package ro.tuc.ds2020.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ro.tuc.ds2020.dto.device.DeviceGetDto;
import ro.tuc.ds2020.service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(UrlMappings.CLIENT)
//@CrossOrigin(origins = "*")
public class ClientController {

    private final UserService userService;

    public ClientController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(UrlMappings.GET_USER_DEVICES)
    public ResponseEntity<?> getUserDevices(@RequestParam("userId") UUID userId) {

        List<DeviceGetDto> devices;

        try {
            devices = userService.getDevices(userId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No user found");
        }

        return ResponseEntity.ok(devices);
    }
}
