package com.chat_bot.ValeraBot.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@Component
@Data
@EnableScheduling
public class BotConfig {
    @Value("${bot.name}")
    private String botName;
    @Value("${bot.key}")
    private String botToken;
    @Value("${bot.owner}")
    private Long botOwner;
}
