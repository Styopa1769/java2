package lesson7.Repositories;

import lesson7.Model.Account;
import lesson7.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAllByOwner(User user);
}
