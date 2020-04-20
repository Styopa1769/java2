package lesson7.UI;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * Пример кнопки со слушателем
 */
@SpringUI(path = "/button")
public class ButtonUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout verticalLayout = new VerticalLayout();

        Button button = new Button("Click");

        Label label = new Label("Text before button pressed");


        button.addClickListener(e -> label.setValue("After click"));

        verticalLayout.addComponents(label,button);
        setContent(verticalLayout);
    }
}
