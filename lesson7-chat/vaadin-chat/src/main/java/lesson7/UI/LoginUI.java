package lesson7.UI;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.LoginForm;
import com.vaadin.ui.UI;

@SpringUI(path = "/login")
public class LoginUI extends UI {
    @Override
    protected void init(VaadinRequest request) {
        LoginForm loginForm = new LoginForm();

        loginForm.addLoginListener(event -> {
            String name = event.getLoginParameter("username");
            String password = event.getLoginParameter("password");

            //залогиньте пользователя
        });

        setContent(loginForm);
    }
}
