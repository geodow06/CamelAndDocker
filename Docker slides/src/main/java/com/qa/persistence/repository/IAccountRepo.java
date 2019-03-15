package com.qa.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.qa.persistence.domain.Account;

@Repository
public interface IAccountRepo extends MongoRepository<Account, Integer>{


}
