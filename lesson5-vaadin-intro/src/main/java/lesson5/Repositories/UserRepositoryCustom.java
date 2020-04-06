package lesson5.Repositories;

import lesson5.Dto.UserDto;

import java.util.List;

public interface UserRepositoryCustom {
    List<UserDto> getAll();
    List<UserDto> getAllByName(String name);
}
