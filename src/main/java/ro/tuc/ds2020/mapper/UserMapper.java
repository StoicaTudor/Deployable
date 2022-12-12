package ro.tuc.ds2020.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ro.tuc.ds2020.dto.user.UserCreateDto;
import ro.tuc.ds2020.dto.user.UserGetDto;
import ro.tuc.ds2020.dto.user.UserUpdateDto;
import ro.tuc.ds2020.entity.User;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {

    User userCreateDtoToUser(UserCreateDto userCreateDto);
    UserGetDto userToUserGetDto(User user);
    User userUpdateDtoToUser(UserUpdateDto userUpdateDto);
}
