package com.qa.business;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.persistence.domain.Account;
import com.qa.persistence.repository.IAccountRepo;

@Service
public class AccountService implements IAccountService {

	@Autowired
	private IAccountRepo accountRepo;

	@Override
	public List<Account> getAllAccounts() {
		return accountRepo.findAll();
	}
	
	@Override
	public Optional<Account> getAnAccount(Integer id) {
		return accountRepo.findById(id);
	}
	
	public Account addAccount(Account account) {
		return accountRepo.save(account);
	}

	@Override
	public void deleteAccount(Integer id) {
		 accountRepo.deleteById(id);
	}

	

}
