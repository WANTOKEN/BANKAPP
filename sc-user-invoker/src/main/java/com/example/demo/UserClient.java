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
@FeignClient(value = "userService",fallback = UserClient.UserClientFallback.class)//声明调用的服务名称
public interface UserClient {
	//注册
	@RequestMapping(method=RequestMethod.POST, value="/userRegister")
	List userRegister(@RequestParam(value = "account") String account, @RequestParam(value = "password") String password);
	
	
	//登录
	@RequestMapping(method=RequestMethod.POST, value="/userLogin")
	List userLogin(@RequestParam(value = "account") String account, @RequestParam(value = "password") String password);
	
	
	//实名认证
	@RequestMapping(method=RequestMethod.POST, value="/userIdentify")
	List userIdentify(@RequestParam(value = "name", required = true) String name,
                      @RequestParam(value = "id", required = true) String id,
                      @RequestParam(value = "account", required = true) String account);
	
	//查询用户表所有信息
	@RequestMapping(method=RequestMethod.POST,value="/queryUserInfo")
	List queryUserInfo(@RequestParam(value = "account") String account);

    //查询用户表所有信息
    @RequestMapping(value = "/queryUserIdentify")
    List queryUserIdentify();

    //用户绑定银行卡
    @RequestMapping(method=RequestMethod.POST, value="/userBindBank")
    List userBindBank(@RequestParam(value = "num", required = true) String num,
                      @RequestParam(value = "bankname", required = true) String bankname,
                      @RequestParam(value = "type", required = true) String type,
                      @RequestParam(value = "code", required = true) String code,
                      @RequestParam(value = "userid", required = true) String userid);
    //用户绑定银行卡
    @RequestMapping(method=RequestMethod.POST, value="/queryBankBindCard")
    List queryBankBindCard(@RequestParam(value = "userid", required = true) String userid);

	/**\
	 List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	 Map<String, Object> map = new HashMap<String, Object>();
	 map.put("code", 444);
	 map.put("msg", "注册超时！");
	 list.add(map);
	 return list;
	 */
	 @Component
    static class UserClientFallback implements UserClient{

        @Override
        public List userRegister(String account, String password) {
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("code", 444);
            map.put("msg", "注册超时！");
            list.add(map);
            return list;
        }

        @Override
        public List userLogin(String account, String password) {
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("code", 444);
            map.put("msg", "服务超时！");
            list.add(map);
            return list;
        }

        @Override
        public List userIdentify(String name, String id, String account) {
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("code", 444);
            map.put("msg", "服务超时！");
            list.add(map);
            return list;
        }

        @Override
        public List queryUserInfo(String account) {
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("code", 444);
            map.put("msg", "服务超时！");
            list.add(map);
            return list;
        }

        @Override
        public List queryUserIdentify() {
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("code", 444);
            map.put("msg", "服务超时！");
            list.add(map);
            return list;
        }

        @Override
        public List userBindBank(String num, String bankname, String type, String code, String userid) {
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("code", 444);
            map.put("msg", "服务超时！");
            list.add(map);
            return list;
        }

        @Override
        public List queryBankBindCard(String userid) {
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("code", 444);
            map.put("msg", "服务超时！");
            list.add(map);
            return list;
        }
    }


}
