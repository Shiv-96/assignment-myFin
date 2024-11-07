package com.assignment.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.Entity.Transaction;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

}
