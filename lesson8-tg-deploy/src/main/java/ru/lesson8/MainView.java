package ru.lesson8;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;


@Route
public class MainView extends VerticalLayout {

    private final UserRepository repo;
    final Grid<User> grid;

    public MainView(UserRepository repo) {
        this.repo = repo;
        TextField placeholderField = new TextField();
        placeholderField.setPlaceholder("Name");
        add(placeholderField);
        Button btn = new Button();
        btn.setText("Add user");
        btn.addClickListener(e -> repo.save(new User(placeholderField.getValue())));
        btn.addClickListener(e -> listCustomers());
        add(btn);
        this.grid = new Grid<>(User.class);
        add(grid);
        listCustomers();
    }

    private void listCustomers() {
        grid.setItems(repo.findAll());
    }

}
