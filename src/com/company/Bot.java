package com.company;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


public class Bot extends TelegramLongPollingBot {
    protected Bot(DefaultBotOptions options) {
        super(options);
    }
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.hasText()){
                try {
                    switch (message.getText()){
                        case "/help": execute(
                                SendMessage.builder()
                                        .chatId(message.getChatId().toString())
                                        .text("Чем могу помочь?")
                                        .build());
                            break;
                        case "/start":
                            execute(
                                    SendMessage.builder()
                                            .chatId(message.getChatId().toString())
                                            .text("Привет, рады вас видеть.")
                                            .build());
                            break;
                        case "Я люблю Галю":
                            execute(
                                    SendMessage.builder()
                                            .chatId(message.getChatId().toString())
                                            .text("А у вас хороший вкус" + new String(Character.toChars('❤')))
                                            .build());
                            break;
                        case "/settings":
                            execute(
                                    SendMessage.builder()
                                            .chatId(message.getChatId().toString())
                                            .text("Что будем настраивать?")
                                            .build());
                            break;
                        case "Я знаю": execute(
                                SendMessage.builder()
                                        .chatId(message.getChatId().toString())
                                        .text("" + "\uD83D\uDE18")
                                        .build());
                    }

                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "MytelegramJavaRomabot";
    }

    @Override
    public String getBotToken() {
        return "5424528953:AAFLj0VHDIV0ZSgo0Zv-WoED0y0xxazcbBA";
    }

    public static void main(String[] args) throws TelegramApiException {
        Bot bot = new Bot(new DefaultBotOptions());
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(bot);
    }
}
