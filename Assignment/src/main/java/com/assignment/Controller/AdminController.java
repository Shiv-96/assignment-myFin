package com.assignment.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.Entity.Loan;
import com.assignment.Entity.Message;
import com.assignment.Repositories.LoanRepository;
import com.assignment.Service.MessageService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private MessageService messageService;

    @PutMapping("/approve-loan/{loanId}")
    public Loan approveLoan(@PathVariable Integer loanId) {
        Loan loan = loanRepository.findById(loanId).orElseThrow();
        loan.setApproved(true);
        return loanRepository.save(loan);
    }

    @PostMapping("/chat/send")
    public Message sendMessageToCustomer(@RequestParam Integer adminId, @RequestParam Integer customerId, @RequestParam String messageText) {
        return messageService.sendMessage(adminId, customerId, messageText);
    }

    @GetMapping("/chat/history")
    public List<Message> getChatHistoryWithCustomer(@RequestParam Integer adminId, @RequestParam Integer customerId) {
        return messageService.getChatHistory(adminId, customerId);
    }

}
