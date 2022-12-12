package ro.tuc.ds2020.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    private UUID id;

    @Column(name = "name", nullable = false, length = 50)
    private String name = "";

    @Column(name = "password", nullable = false, length = 50)
    private String password = "";

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Device> devices;

    @ElementCollection
    private Set<UserRole> userRoles;

    public UUID getId() {
        return id;
    }

    public User setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public User setDevices(List<Device> devices) {
        this.devices = devices;
        return this;
    }

    public Set<UserRole> getRoles() {
        return userRoles;
    }

    public User setRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
        return this;
    }
}
