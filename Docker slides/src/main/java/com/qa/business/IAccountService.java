package com.qa.business;

import java.util.List;
import java.util.Optional;

import com.qa.persistence.domain.Account;

public interface IAccountService {

	List<Account> getAllAccounts();

	Account addAccount(Account account);

	Optional<Account> getAnAccount(Integer id);
	
	void deleteAccount (Integer id);
}
