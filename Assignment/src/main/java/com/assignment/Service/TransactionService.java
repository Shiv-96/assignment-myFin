package com.assignment.Service;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.Entity.Account;
import com.assignment.Entity.Transaction;
import com.assignment.Entity.TransactionType;
import com.assignment.Repositories.AccountRepository;
import com.assignment.Repositories.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    public Transaction createTransaction (Integer accountId, BigDecimal amount, TransactionType type) throws Exception {

        Account account = accountRepository.findById(accountId).orElseThrow();

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAccount(account);
        transaction.setType(type);
        transaction.setTransactionId(UUID.randomUUID().toString());

        if(type == TransactionType.WITHDRAWL && account.getBalance().compareTo(amount) < 0) {
            throw new Exception("Insufficient Balance");
        }
        else if (type == TransactionType.WITHDRAWL) {
            account.setBalance(account.getBalance().subtract(amount));
        }
        else if (type == TransactionType.DEPOSIT) {
            account.setBalance(account.getBalance().add(amount));
        }

        accountRepository.save(account);
        return transactionRepository.save(transaction);
        
    }

}
