package com.skyllx.rental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.skyllx.rental.service.ChatService;

@Controller
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private ChatService chatService;

    @PostMapping("/send")
    public String sendMessage(@RequestParam String sender, @RequestParam String receiver, @RequestParam String text) {
        chatService.sendMessage(sender, receiver, text);
        return "redirect:/chat.jsp";
    }
}

