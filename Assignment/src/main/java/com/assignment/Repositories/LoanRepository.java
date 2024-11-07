package com.assignment.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.Entity.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {

}
