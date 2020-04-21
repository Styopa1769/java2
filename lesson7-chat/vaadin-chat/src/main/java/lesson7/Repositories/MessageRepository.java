package lesson7.Repositories;

import lesson7.Model.Account;
import lesson7.Model.Message;
import lesson7.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByOwner(User user);
    List<Message> findAll();
}
