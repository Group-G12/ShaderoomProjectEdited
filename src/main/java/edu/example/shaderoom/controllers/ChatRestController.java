package edu.example.shaderoom.controllers;


import edu.example.shaderoom.models.Chats;
import edu.example.shaderoom.models.RestChats;
import edu.example.shaderoom.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/chats")
public class ChatRestController {


    public ChatService chatService;

    @Autowired
    public ChatRestController(ChatService chat)
    {
        this.chatService = chatService;
    }

    @GetMapping("/{id}")
    public Chats getChatById(@PathVariable(name="id") String id) throws ExecutionException, InterruptedException {
        return (Chats) chatService.getChatsComments(id);
    }

    @GetMapping(path = "/")
    public String createChat(@RequestBody RestChats chats) throws ExecutionException, InterruptedException {
        return chatService.createPost(chats);
    }
}
