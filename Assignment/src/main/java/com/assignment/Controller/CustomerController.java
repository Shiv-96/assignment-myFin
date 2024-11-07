package com.assignment.Controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.Entity.Loan;
import com.assignment.Entity.Message;
import com.assignment.Entity.Role;
import com.assignment.Entity.Transaction;
import com.assignment.Entity.TransactionType;
import com.assignment.Entity.User;
import com.assignment.Service.LoanService;
import com.assignment.Service.MessageService;
import com.assignment.Service.TransactionService;
import com.assignment.Service.UserService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private UserService userService;

    @Autowired
    private LoanService loanService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/register")
    public User registerCustomer(@RequestBody User user) {
        user.setRole(Role.CUSTOMER);
        return userService.registerUser(user);
    }

    @PostMapping("/deposit")
    public Transaction deposit(@RequestParam Integer accountId, @RequestParam BigDecimal amount) throws Exception {
        return transactionService.createTransaction(accountId, amount, TransactionType.DEPOSIT);
    }

    @PostMapping("/withdraw")
    public Transaction withdraw(@RequestParam Integer accountId, @RequestParam BigDecimal amount) throws Exception {
        return transactionService.createTransaction(accountId, amount, TransactionType.WITHDRAWL);
    }

    @PostMapping("/transfer")
    public Transaction transferFunds(@RequestParam Integer fromAccountId, @RequestParam Integer toAccountId, @RequestParam BigDecimal amount) throws Exception {
        Transaction withdrawal = transactionService.createTransaction(fromAccountId, amount, TransactionType.WITHDRAWL);
        transactionService.createTransaction(toAccountId, amount, TransactionType.DEPOSIT);

        return withdrawal; 
    }

    @PostMapping("/apply-loan")
    public Loan applyLoan(@RequestBody Loan loan) {
        return loanService.applyForLoan(loan);
    }

    @GetMapping("/calculateemi")
    public BigDecimal calCulateEMI(@RequestParam BigDecimal principal, @RequestParam BigDecimal annualRate, @RequestParam Integer months) {
        return loanService.calculateEMI(principal, annualRate, months);
    }


    @PostMapping("/chat/send")
    public Message sendMessageToAdmin(@RequestParam Integer customerId, @RequestParam Integer adminId, @RequestParam String messageText) {
        return messageService.sendMessage(customerId, adminId, messageText);
    }

    @GetMapping("/chat/history")
    public List<Message> getChatHistoryWithAdmin(@RequestParam Integer customerId, @RequestParam Integer adminId) {
        return messageService.getChatHistory(customerId, adminId);
    }


}
