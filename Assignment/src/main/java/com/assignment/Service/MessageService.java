package com.assignment.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.Entity.Message;
import com.assignment.Entity.User;
import com.assignment.Repositories.MessageRepository;
import com.assignment.Repositories.UserRepository;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    public Message sendMessage(Integer senderId, Integer recieverId, String messageText) {
        
        User sender = userRepository.findById(senderId).orElseThrow();

        User reciever = userRepository.findById(recieverId).orElseThrow();

        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(reciever);
        message.setMessageText(messageText);

        return messageRepository.save(message);
    }


    public List<Message> getChatHistory(Integer senderId, Integer recieverId) {

        User sender = userRepository.findById(senderId).orElseThrow();
        User reciever = userRepository.findById(recieverId).orElseThrow();

        return messageRepository.findBySenderAndReceiver(sender, reciever);
        
    }

}
