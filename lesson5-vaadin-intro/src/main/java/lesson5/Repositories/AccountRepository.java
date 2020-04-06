package lesson5.Repositories;

import lesson5.Model.Account;
import lesson5.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAllByOwner(User user);
}
