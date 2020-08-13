package cn.monitor4all.springbootwebsocketdemo.controller;

import cn.monitor4all.springbootwebsocketdemo.enums.SecretGroup;
import cn.monitor4all.springbootwebsocketdemo.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private ChatService chatService;

    @RequestMapping(value = "/startchat",method = RequestMethod.GET)
    @ResponseBody
    public String startChat(String name) {
        return chatService.canStartChat(name);
    }

    @RequestMapping(value = "/chatrecord",method = RequestMethod.GET)
    @ResponseBody
    public List<String> getChatRecords(String name) {
        List<String> list = new ArrayList<>();
        if (SecretGroup.getAllNames().contains(name)) {
            list = chatService.getChatRecords();
        }
        return list;
    }
}
