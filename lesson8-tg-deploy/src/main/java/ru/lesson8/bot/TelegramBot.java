package ru.lesson8.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.lesson8.TelegramUser;
import ru.lesson8.TelegramUserRepository;
import ru.lesson8.User;
import ru.lesson8.UserRepository;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    @Autowired
    UserRepository userRepository;

    @Autowired
    TelegramUserRepository telegramUserRepository;

    @Value("${bot.token}")
    private String token;

    @Value("${bot.name}")
    private String username;

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage())
            return;
        final String text = update.getMessage().getText();
        final Long chatId = update.getMessage().getChatId();
        TelegramUser tgUser =  telegramUserRepository.findByChatId(chatId);
        BotContext context;
        BotState state;
        if (tgUser == null) {
            state = BotState.getInitialState();
            tgUser = new TelegramUser(chatId, state.ordinal());
            telegramUserRepository.save(tgUser);
            context = BotContext.of(tgUser, text, this);
            state.enter(context);
        } else {
            context = BotContext.of(tgUser, text, this);
            state = BotState.byId(context.getUser().getStateId());
        }
        state.handleInput(context);
        do {
            state = state.nextState();
            state.enter(context);
        } while (!state.isInputNeeded());
        tgUser.setStateId(state.ordinal());
        telegramUserRepository.save(tgUser);
    }

    public UserRepository getRepository(){
        return userRepository;
    }
}