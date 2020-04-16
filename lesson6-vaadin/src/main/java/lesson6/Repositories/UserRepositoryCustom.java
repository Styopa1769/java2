package lesson6.Repositories;

import lesson6.Dto.UserDto;

import java.util.List;

public interface UserRepositoryCustom {
    List<UserDto> getAll();
    List<UserDto> getAllByName(String name);
}
