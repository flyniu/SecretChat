package cn.monitor4all.springbootwebsocketdemo.service;

import cn.monitor4all.springbootwebsocketdemo.enums.SecretGroup;
import cn.monitor4all.springbootwebsocketdemo.model.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatService.class);

    @Autowired
    private SimpMessageSendingOperations simpMessageSendingOperations;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Value("${redis.channel.msgToAll}")
    private String msgToAll;

    public void sendMsg(@Payload ChatMessage chatMessage) {
        LOGGER.info("Send msg by simpMessageSendingOperations:" + chatMessage.toString());
        simpMessageSendingOperations.convertAndSend("/topic/public", chatMessage);
    }

    public void alertUserStatus(@Payload ChatMessage chatMessage) {
        LOGGER.info("Alert user online by simpMessageSendingOperations:" + chatMessage.toString());
        simpMessageSendingOperations.convertAndSend("/topic/public", chatMessage);
    }

    public String canStartChat(String name) {
        if (SecretGroup.getAllNames().contains(name)) {
            return "true";
        }
        return "false";
    }

    public String get(String name) {
        if (SecretGroup.getAllNames().contains(name)) {
            return "true";
        }
        return "false";
    }
    public List<String> getChatRecords() {
        List<String> list= redisTemplate.opsForList().range(msgToAll,0,10000);
        return list;
    }
}
