package lesson7.UI;

import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;

/**
 * пример редиректа на другую страницу
 */
@SpringUI(path = "/redirect")
public class RedirectUI extends UI {
    @Override
    protected void init(VaadinRequest request) {
        Button button = new Button("To users page");
        button.addClickListener(e -> Page.getCurrent().setLocation("/layout/users"));
        setContent(button);
    }
}
