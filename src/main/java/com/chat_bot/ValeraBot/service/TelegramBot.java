package com.chat_bot.ValeraBot.service;

import com.chat_bot.ValeraBot.config.BotConfig;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    final BotConfig config;

    public TelegramBot(BotConfig config) {
        this.config = config;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();


            switch (message){
                case "/start":
                    startCommand(chatId,update.getMessage().getChat().getFirstName());
                    break;
                default:
                    sendMessage(chatId,"Invalid command");
            }

        }
    }

    private void startCommand(Long chatId, String name) {
        String answer = "Hi "+ name + "!";
        sendMessage(chatId,answer);
    }

    private void sendMessage(Long chatId, String message){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);

        try {
            execute(sendMessage);
        }catch (TelegramApiException e){

        }
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getBotToken();
    }
}
