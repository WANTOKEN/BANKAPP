package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@Configuration
public class AccountController {

	@Autowired
	private AccountClient accountClient;
	
	@RequestMapping(value="/queryBank", method = RequestMethod.POST)
	public List queryBank(@RequestParam(value = "num", required = true) String num,
                          @RequestParam(value = "tel", required = true) String tel,
                          @RequestParam(value = "id", required = true) String id,
                          @RequestParam(value = "code", required = true) String code) {
		List list = accountClient.queryBank(num, tel, id, code);
		return list;
	}
	
	@RequestMapping(value="/transferMoney", method = RequestMethod.POST)
	public List transferMoney(@RequestParam(value = "from_account", required = true) String from_account,
                              @RequestParam(value = "to_account", required = true) String to_account,
                              @RequestParam(value = "money", required = true) Integer money,
                              @RequestParam(value = "code", required = true) String code) {
		List list = accountClient.transferMoney(from_account, to_account, money, code);
		return list;
	}

	@RequestMapping(value="/queryBankDetail", method = RequestMethod.POST)
	public List queryBankDetail(@RequestParam(value = "num", required = true) String num) {
		return accountClient.queryBankDetail(num);
	}
	
	@RequestMapping(value="queryBankBindCard", method = RequestMethod.POST)
	public List queryBankBindCard(@RequestParam(value = "userid", required = true) String userid) {
		return accountClient.queryBankBindCard(userid);
	}
}
