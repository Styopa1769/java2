package lesson7.Repositories;

import lesson7.Dto.UserDto;

import java.util.List;

public interface UserRepositoryCustom {
    List<UserDto> getAll();
    List<UserDto> getAllByName(String name);
}
