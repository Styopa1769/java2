package lesson6.UI.signin;

import com.vaadin.ui.Component;
import com.vaadin.ui.Window;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * Форма для создания нового пользователя
 */
public class UserForm extends AbstractForm<UserView> {

    private final MTextField name = new MTextField("Введите имя:");
    private final MTextField password = new MTextField("Введите пароль:");
    private final MButton saveButton = new MButton("Сохранить");

    public UserForm() {
        super(UserView.class);
    }

    @Override
    protected Component createContent() {
        setSaveButton(saveButton);
        return new MVerticalLayout(name, password, saveButton);
    }

    @Override
    public Window openInModalPopup() {
        Window w = super.openInModalPopup();
        w.setHeight("95%");
        w.setWidth("70%");
        return w;
    }
}
