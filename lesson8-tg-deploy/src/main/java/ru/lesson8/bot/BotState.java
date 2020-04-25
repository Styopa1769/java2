package ru.lesson8.bot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.lesson8.User;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum BotState {
    Start(false) {
        @Override
        public void enter(BotContext context) {
            sendMessage(context, "Welcome!");
        }

        @Override
        public BotState nextState() {
            return EnterUsernameAndPassword;
        }
    },
    EnterUsernameAndPassword {
        private BotState next;
        @Override
        public void enter(BotContext context) {
            sendMessage(context, "Enter username and password:\n<name> <password>");
        }

        @Override
        public void handleInput(BotContext context) {
            String input = context.getInput();
            List<String> info = Arrays.asList(input.split(" ", 2));
            String name = info.get(0);
            String password = info.get(1);
            User user = context.getBot().getRepository().findByNameAndPassword(name, password);
            if (user != null) {
                context.getUser().setUser_id(user.getId());
                next = Authorized;
            } else {
                sendMessage(context, "Wrong username or password");
                next = EnterUsernameAndPassword;
            }
        }

        @Override
        public BotState nextState() {
            return next;
        }
    },
    Authorized {
        private BotState next;
        @Override
        public void enter(BotContext context) {
            sendMessage(context, "Enter command");
        }

        @Override
        public void handleInput(BotContext context) {
            next = Authorized;
            String cmd = context.getInput();
            switch (cmd) {
                case "/get_money":
                    Optional<User> user = context.getBot().getRepository().findById(context.getUser().getUser_id());
                    if (!user.isPresent()) {
                        sendMessage(context, "User doesn't exist");
                        next = EnterUsernameAndPassword;
                    } else {
                        sendMessage(context, user.get().getMoney().toString());
                    }
                    break;
                case "/help":
                    sendMessage(context, "/get_money, /exit");
                    break;
                case "/exit":
                    next = Start;
                    break;
            }
        }

        @Override
        public BotState nextState() {
            return next;
        }
    };
    private static BotState[] states;
    private final boolean inputNeeded;

    BotState(){
        this.inputNeeded = true;
    }
    BotState(boolean inputNeeded){
        this.inputNeeded = inputNeeded;
    }
    public static BotState getInitialState(){
        return byId(0);
    }
    public static BotState byId(int id) {
        if(states == null) {
            states = BotState.values();
        }
        return states[id];
    }
    protected void sendMessage(BotContext context, String text){
        SendMessage message = new SendMessage().setChatId(context.getUser().getChatId()).setText(text);
        try{
            context.getBot().execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public boolean isInputNeeded() {return inputNeeded;}
    public void handleInput(BotContext context) {

    }
    public abstract void enter(BotContext context);
    public abstract BotState nextState();
}
