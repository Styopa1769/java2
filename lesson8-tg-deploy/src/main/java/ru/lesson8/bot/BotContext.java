package ru.lesson8.bot;


import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import ru.lesson8.TelegramUser;
import ru.lesson8.User;

public class BotContext {
    private TelegramUser user;
    private String input;
    private TelegramBot bot;
    private String username;
    private String password;

    public static BotContext of(TelegramUser user, String input, TelegramBot bot){
        return new BotContext(user, input, bot);
    }

    public BotContext(TelegramUser user, String input, TelegramBot bot) {
        this.user = user;
        this.input = input;
        this.bot = bot;
    }

    public TelegramUser getUser() {return user;}
    public String getInput() {return input;}
    public TelegramBot getBot() {return bot;}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
