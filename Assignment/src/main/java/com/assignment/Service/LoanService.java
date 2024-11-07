package com.assignment.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.Entity.Loan;
import com.assignment.Repositories.LoanRepository;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    public Loan applyForLoan(Loan loan) {
        loan.setApproved(false);
        loan.setEmi(calculateEMI(loan.getLoanAmount(), loan.getInterestRate(), loan.getTenureMonths()));
        return loanRepository.save(loan);
    }

    public BigDecimal calculateEMI(BigDecimal principal, BigDecimal rate, Integer months) {
        BigDecimal monthlyRate = rate.divide(BigDecimal.valueOf(12 * 100), RoundingMode.HALF_UP);
        BigDecimal onePlusRatePowerN = BigDecimal.ONE.add(monthlyRate).pow(months);


        return principal.multiply(monthlyRate)
            .multiply(onePlusRatePowerN)
            .divide(onePlusRatePowerN.subtract(BigDecimal.ONE), RoundingMode.HALF_UP);
    }
}
