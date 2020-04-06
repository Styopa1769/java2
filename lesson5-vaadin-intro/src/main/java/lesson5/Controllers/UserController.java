package lesson5.Controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lesson5.Exceptions.ResourceNotFoundException;
import lesson5.Model.User;
import lesson5.Repositories.UserRepository;
import lesson5.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Api(value = "lesson5", description = "some manipulations with users")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    @ApiOperation("get all users")
    public RestResponse getUsers() {
        try {
            return RestResponse.createSuccessResponse(userRepository.getAll());
        } catch (ResourceNotFoundException e)
        {
            return RestResponse.createFailureResponse(e.getMessage(), 400);
        }
    }

    @PostMapping("/users")
    @ApiOperation("create a user")
    public RestResponse addUser(@Valid User user) {
        try {
            return RestResponse.createSuccessResponse(userRepository.save(user));
        }
        catch (ResourceNotFoundException e) {
            return RestResponse.createFailureResponse(e.getMessage(), 400);
        }
    }
}
