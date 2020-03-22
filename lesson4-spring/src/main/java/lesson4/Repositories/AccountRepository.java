package lesson4.Repositories;

import lesson4.Model.Account;
import lesson4.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAllByOwner(User user);
}
