package lesson7.UI;

import com.vaadin.annotations.Push;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

@Push(PushMode.MANUAL)
@SpringUI(path = "/chat")
public class ChatUI extends UI {

    // адрес сервера
    private static final String SERVER_HOST = "localhost";
    // порт
    private static final int SERVER_PORT = 3443;
    // клиентский сокет
    private Socket clientSocket;
    // входящее сообщение
    private Scanner inMessage;
    // исходящее сообщение
    private PrintWriter outMessage;
    // следующие поля отвечают за элементы формы
    private final TextField message = new TextField();
    private final TextField name = new TextField();
    private final TextArea textArea = new TextArea();
    private final Button button = new Button("Send message");

    public ChatUI() {
        try {
            // подключаемся к серверу
            clientSocket = new Socket(SERVER_HOST, SERVER_PORT);
            inMessage = new Scanner(clientSocket.getInputStream());
            outMessage = new PrintWriter(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init(VaadinRequest vaadinRequest) {
        MVerticalLayout layout = new MVerticalLayout();

        button.addClickListener(event -> {
            sendMsg();
        });

        UI current = getUI();

        // в отдельном потоке начинаем работу с сервером
        new Thread(() -> {
            try {
                // бесконечный цикл
                while (true) {
                    // если есть входящее сообщение
                    if (inMessage.hasNext()) {
                        // считываем его
                        String msg = inMessage.nextLine();
                        String text = textArea.getValue();
                        current.access(new Runnable() {
                            @Override
                            public void run() {
                                textArea.setValue(String.format("%s%s\n", text, msg));
                                current.push();
                            }
                        });
                    }
                }
                }
            catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        MHorizontalLayout mHorizontalLayout = new MHorizontalLayout();
        mHorizontalLayout.add(message,button);

        layout.add(name, textArea, mHorizontalLayout);

        textArea.setSizeFull();
        layout.setSizeFull();

        setContent(layout);
    }

    // отправка сообщения
    private void sendMsg() {
        // формируем сообщение для отправки на сервер
        String messageStr = name.getValue() + ": " + message.getValue();
        // отправляем сообщение
        outMessage.println(messageStr);
        outMessage.flush();
        message.setValue("");
    }
}
