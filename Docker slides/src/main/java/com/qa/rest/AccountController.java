package com.qa.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.business.AccountService;
import com.qa.persistence.domain.Account;
import com.qa.utility.JSONImpl;

@RestController
@CrossOrigin
public class AccountController {

	@Autowired
	public AccountService service;

	@Autowired
	public JSONImpl json;

	@RequestMapping("/getAllAccounts")
	public List<Account> getAllAccounts() {

		return service.getAllAccounts();
	}

	@RequestMapping("/getAccount/{id}")
	public Optional<Account> getAnAccount(@PathVariable Integer id) {

		return service.getAnAccount(id);
	}

	@PostMapping("/addAccount")
	public Account addAccount(@RequestBody String accountData) {

		Account account = json.getObjectForJSON(accountData, Account.class);

		return service.addAccount(account);
	}


	@DeleteMapping("/deleteAccount/{id}")
	public void deleteFile(@PathVariable Integer id) {

		service.deleteAccount(id);
		
	}

}
