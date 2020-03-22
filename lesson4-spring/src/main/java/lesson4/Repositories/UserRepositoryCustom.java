package lesson4.Repositories;

import lesson4.Dto.UserDto;

import java.util.List;

public interface UserRepositoryCustom {
    List<UserDto> getAll();
    List<UserDto> getAllByName(String name);
}
