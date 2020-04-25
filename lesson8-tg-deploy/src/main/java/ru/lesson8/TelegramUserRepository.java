package ru.lesson8;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TelegramUserRepository extends JpaRepository<TelegramUser, Long> {
    TelegramUser findByChatId(Long chatId);
}
