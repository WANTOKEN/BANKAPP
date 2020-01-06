package com.example.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
@FeignClient(value = "bankService", fallback = AccountClient.AccountClientFallback.class)
public interface AccountClient {
	
	//用户查询银行卡
	@RequestMapping(value="/queryBank", method = RequestMethod.POST)
	List queryBank(@RequestParam(value = "num", required = true) String num,
                   @RequestParam(value = "tel", required = true) String tel,
                   @RequestParam(value = "id", required = true) String id,
                   @RequestParam(value = "code", required = true) String code); 
	

	
	 @Component
	 static class AccountClientFallback implements AccountClient {

		 @Override
		 public List queryBank(String num, String tel, String id, String code) {
			 List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			 Map<String, Object> map = new HashMap<String, Object>();
			 map.put("code", 444);
			 map.put("msg", "服务超时！");
			 list.add(map);
			 return list;
		 }

	 }
}
