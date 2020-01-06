package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class UserController {
	@Autowired
	private UserClient userClient;
    @Autowired
	private AccountClient accountClient;
	
	@RequestMapping(value="/userRegister", method=RequestMethod.POST)
	public List userRegister(@RequestParam(value = "account", required = true) String account, @RequestParam(value = "password", required = true) String password) {
		return userClient.userRegister(account, password);
	}
	
	@RequestMapping(value="/userLogin", method=RequestMethod.POST)
	public List userLogin(@RequestParam(value = "account", required = true) String account, @RequestParam(value = "password", required = true) String password) {
		return userClient.userLogin(account, password);
	}
	
	@RequestMapping(value="/userIdentify", method=RequestMethod.POST)
	public List userIdentify(@RequestParam(value = "name", required = true) String name,
                             @RequestParam(value = "id", required = true) String id,
                             @RequestParam(value = "account", required = true) String account) {
		return userClient.userIdentify(name, id, account);
	}
	
	@RequestMapping(value="/queryUserInfo", method=RequestMethod.POST)
	public List queryUserInfo(@RequestParam(value="account") String account){

		return userClient.queryUserInfo(account);
	}

	@RequestMapping(value="/queryUserIdentify", method=RequestMethod.POST)
	public List queryUserIdentify(){
		return userClient.queryUserIdentify();
	}

	@RequestMapping(value="/userBindBank", method=RequestMethod.POST)
	public List userBindBank(@RequestParam(value = "num") String num,
							 @RequestParam(value = "bankname") String bankname,
							 @RequestParam(value = "type") String type,
							 @RequestParam(value = "code") String code,
							 @RequestParam(value = "userid") String userid){
		return userClient.userBindBank(num, bankname, type, code, userid);
	}
    @RequestMapping(value="/userBindBank2", method=RequestMethod.POST)
    public List userBindBank2(
            @RequestParam(value = "num", required = true) String num,
            @RequestParam(value = "tel", required = true) String tel,
            @RequestParam(value = "id", required = true) String id,
            @RequestParam(value = "code", required = true) String code,
             @RequestParam(value = "userid") String userid){
        List<Map<String,Object>> list =  accountClient.queryBank(num, tel, id, code);
        List<Map<String, Object>> relist = new ArrayList<Map<String, Object>>();

        if(!list.isEmpty()){
            for(Map<String,Object>map: list ){
                System.out.println(map.get("code"));
                int respcode = (int) map.get("code");
                if(respcode==200){
                    Map<String,Object>datamap = (Map<String, Object>) map.get("data");
                    System.out.println(map.get("data"));
                    String bankname = (String) datamap.get("bank_name");
                    String type = (String) datamap.get("type");
                    System.out.println(datamap);
                    return userClient.userBindBank(num, bankname, type, code, userid);
                }else{
                    return list;
                }
            }
        }else{
            Map<String, Object> remap = new HashMap<String, Object>();
            remap.put("code", 400);
            remap.put("msg", "绑定失败！");
            relist.add(remap);
            return relist;
        }
        return relist;
    }
	@RequestMapping(value="/queryBankBindCard", method=RequestMethod.POST)
	public List queryBankBindCard(@RequestParam(value = "userid") String userid){
		return userClient.queryBankBindCard(userid);
	}



}
