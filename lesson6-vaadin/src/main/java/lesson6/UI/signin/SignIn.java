package lesson6.UI.signin;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import lesson6.Dto.UserDto;
import lesson6.Model.User;
import lesson6.Repositories.UserRepository;
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

        editor.setSavedHandler(userView -> {
            User user = new User();

            user.setName(userView.getName());
            user.setPassword_hash(passwordEncoder.encode(userView.getPassword()));

            userRepository.save(user);

            editor.setEntity(new UserView());
        });

        Button button = new Button("Создать пользователя");

        button.addClickListener(click -> {
            editor.setEntity(new UserView());
            editor.openInModalPopup();
        });

        setContent(new MVerticalLayout(button));
    }
}
