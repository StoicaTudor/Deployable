package ro.tuc.ds2020.dto.user;

import ro.tuc.ds2020.dto.device.DeviceGetDto;
import ro.tuc.ds2020.entity.UserRole;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class UserGetDto {

    @NotNull
    private UUID id;

    @NotNull
    @Size(min = 3, max = 20)
    private String name;

    @NotEmpty
    private Set<UserRole> roles;
    private List<DeviceGetDto> devices;

    public UUID getId() {
        return id;
    }

    public UserGetDto setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserGetDto setName(String name) {
        this.name = name;
        return this;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public UserGetDto setRoles(Set<UserRole> roles) {
        this.roles = roles;
        return this;
    }

    public List<DeviceGetDto> getDevices() {
        return devices;
    }

    public UserGetDto setDevices(List<DeviceGetDto> devices) {
        this.devices = devices;
        return this;
    }
}
