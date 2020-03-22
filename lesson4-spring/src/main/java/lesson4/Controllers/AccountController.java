package lesson4.Controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lesson4.Exceptions.ResourceNotFoundException;
import lesson4.Repositories.AccountRepository;
import lesson4.Repositories.UserRepository;
import lesson4.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Api("add, get account")
public class AccountController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/accounts/{id}")
    @ApiOperation("get accounts by user id")
    public RestResponse getUserAccounts(@Valid @PathVariable("id") Long id) {
        try {
            return RestResponse.createSuccessResponse(accountRepository.findAllByOwner(userRepository.getOne(id)));
        } catch (ResourceNotFoundException e) {
            return RestResponse.createFailureResponse(e.getMessage(), 400);
        }
    }
}
