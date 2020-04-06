package lesson5.UI;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import lesson5.Dto.UserDto;
import lesson5.Model.User;
import lesson5.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * Использование формы ввода данных пользователя для регистрации, т.е. записи в БД
 */
@SpringUI(path = "/signin")
public class SignIn extends UI {

    @Autowired
    UserRepository userRepository;

    //по-хорошему это сделать бином, как репозиторий
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    protected void init(VaadinRequest request) {
        UserForm editor = new UserForm();

        editor.setEntity(new UserDto());

        editor.setSavedHandler(userDto -> {
            User user = new User();

            user.setName(userDto.getName());
            user.setPassword_hash(passwordEncoder.encode(userDto.getPassword()));

            userRepository.save(user);

            editor.setEntity(new UserDto());
        });

        setContent(new MVerticalLayout(editor));
    }
}
