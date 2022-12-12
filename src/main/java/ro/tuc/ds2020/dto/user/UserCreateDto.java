package ro.tuc.ds2020.dto.user;

import ro.tuc.ds2020.entity.UserRole;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

public class UserCreateDto {

    @NotNull
    @Size(min = 3, max = 20)
    private String name;

    @NotNull
    @Size(min = 3, max = 20)
    private String password;

    @NotEmpty
    private Set<UserRole> roles;

    public String getName() {
        return name;
    }

    public UserCreateDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserCreateDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public UserCreateDto setRoles(Set<UserRole> roles) {
        this.roles = roles;
        return this;
    }
}
