package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Component
@FeignClient(value = "bankService", fallback = AccountClient.AccountClientFallback.class)
public interface AccountClient {
	//用户查询银行卡
	@RequestMapping(value="/queryBank", method = RequestMethod.POST)
	List queryBank(@RequestParam(value = "num", required = true) String num,
                   @RequestParam(value = "tel", required = true) String tel,
                   @RequestParam(value = "id", required = true) String id,
                   @RequestParam(value = "code", required = true) String code); 
	//转账
	@RequestMapping(value="/transferMoney", method = RequestMethod.POST)
	List transferMoney(@RequestParam(value = "from_account", required = true) String from_account,
                       @RequestParam(value = "to_account", required = true) String to_account,
                       @RequestParam(value = "money", required = true) Integer money,
                       @RequestParam(value = "code", required = true) String code);
	//银行卡查询明细
	@RequestMapping(value="/queryBankDetail", method = RequestMethod.POST)
	List queryBankDetail(@RequestParam(value = "num", required = true) String num);
	//查询某个用户绑定的所有银行卡
	@RequestMapping(value="/queryBankBindCard", method = RequestMethod.POST)
	List queryBankBindCard(@RequestParam(value = "userid", required = true) String userid);

	 @Component
	 static class AccountClientFallback implements AccountClient{

		@Override
		public List queryBank(String num, String tel, String id, String code) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", 444);
			map.put("msg", "查询超时！");
			list.add(map);
			return list;
		}
		@Override
		public List transferMoney(String from_account, String to_account, Integer money, String code) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", 444);
			map.put("msg", "转账超时！请重试！");
			list.add(map);
			return list;
		}
		@Override
		public List queryBankDetail(String num) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", 444);
			map.put("msg", "查询超时！");
			list.add(map);
			return list;
		}
		@Override
		public List queryBankBindCard(String userid) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", 444);
			map.put("msg", "查询超时！");
			list.add(map);
			return list;
		}
	  }

}
