package com.dangisabin.javachatgptbot.controller;

import com.dangisabin.javachatgptbot.dto.ChatGptRequest;
import com.dangisabin.javachatgptbot.dto.ChatGptResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1")
public class CustomBotController {

    @Value("${openai.api.url}")
    private String apiUrl;

    @Value("${openai.model}")
    private String model;

    @Autowired
    private RestTemplate template;

    @GetMapping({"/","/hello"})
    public String sayHello(){
        return "Hello, from Javachatgpt bot!";
    }

    @GetMapping("/chat")
    public String chat(@RequestParam("prompt") String prompt){
        ChatGptRequest request=new ChatGptRequest(model,prompt);
        ChatGptResponse chatGptResponse=template.postForObject(apiUrl,request, ChatGptResponse.class);
        return chatGptResponse.getChoices().get(0).getMessage().getContent();
    }
}
