package lesson7.UI;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;

@SpringUI(path = "/greeting")
public class Greeting extends UI {
    @Override
    protected void init(VaadinRequest vaadinRequest) {

        Label text = new Label("Hello!");

        setContent(text);
    }
}
