package lesson7.UI;

import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import org.vaadin.chatbox.ChatBox;
import org.vaadin.chatbox.SharedChat;
import org.vaadin.chatbox.client.ChatUser;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.ArrayList;
import java.util.List;

@SpringUI(path = "/chat")
public class ChatUI extends UI {

    private final List<String> messages = new ArrayList<>();

    @Override
    public void init(VaadinRequest vaadinRequest) {
        MVerticalLayout layout = new MVerticalLayout();
        TextArea chat = new TextArea();
        chat.setReadOnly(true);

        TextField editor = new TextField("Введите сообщение: ");
        editor.addShortcutListener(new ShortcutListener("Shortcut Name", ShortcutAction.KeyCode.ENTER, null) {
            @Override
            public void handleAction(Object sender, Object target) {
                messages.add(editor.getValue());
                chat.setValue(printMessages());
            }
        });
        layout.add(chat, editor);
        setContent(layout);
    }

    private String printMessages(){
        StringBuilder sb = new StringBuilder();
        messages.forEach(message -> sb.append(message).append("\n"));
        return sb.toString();
    }
}
