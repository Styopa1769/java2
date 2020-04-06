package lesson5.UI;

import com.vaadin.ui.Component;
import lesson5.Dto.UserDto;
import lesson5.Model.User;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * Форма для создания нового пользователя
 */
public class UserForm extends AbstractForm<UserDto> {

    private final MTextField name = new MTextField("Введите имя:");
    private final MTextField password = new MTextField("Введите пароль:");
    private final MButton saveButton = new MButton("Сохранить");

    public UserForm() {
        super(UserDto.class);
    }

    @Override
    protected Component createContent() {
        setSaveButton(saveButton);
        return new MVerticalLayout(name, password, saveButton);
    }
}
