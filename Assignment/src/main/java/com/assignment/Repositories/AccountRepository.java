package com.assignment.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.Entity.Account;
import com.assignment.Entity.User;

import java.util.List;


@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    List<Account> findByUser(User user);

}
