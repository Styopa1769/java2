package lesson6.UI;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * Пример макета в макете
 */
@SpringUI(path = "/complex")
public class ComplexLayout extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        VerticalLayout layout = new VerticalLayout();

        HorizontalLayout horizontalLayout = new HorizontalLayout();

        Label greeting = new Label("Hello");
        Label name = new Label("Stepan Shcherbakov");

        horizontalLayout.addComponents(greeting, name);

        Label description = new Label("This is complex layout page");

        layout.addComponents(horizontalLayout, description);

        setContent(layout);
    }
}
