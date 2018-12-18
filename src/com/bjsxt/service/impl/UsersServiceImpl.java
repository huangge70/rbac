package com.bjsxt.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjsxt.mapper.UsersMapper;
import com.bjsxt.pojo.Users;
import com.bjsxt.service.ElementService;
import com.bjsxt.service.MenuService;
import com.bjsxt.service.UrlService;
import com.bjsxt.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService {

	@Resource
	private UsersMapper usersMapper;
	@Resource
	private MenuService menuServiceImpl;
	@Resource
	private ElementService elementSreviceImpl;
	@Resource
	private UrlService urlSreviceImpl;
	@Override
//	public Users login(Users users) {
//		Users user = usersMapper.selByUser(users);
//		if(user!=null){
//			user.setMenus(menuServiceImpl.showMenu(user.getRid()));
//			user.setElements(elementSreviceImpl.selByRid(user.getRid()));
//			user.setUrls(urlSreviceImpl.selByRid(user.getRid()));
//		}
//		return user;
//	}
	public Map<String, Object> login(Users users) {
		Map<String, Object> map=new HashMap<String, Object>();
		Users user = usersMapper.selByUser(users);
		if(user!=null){
			user.setMenus(menuServiceImpl.showMenu(user.getRid()));
			user.setElements(elementSreviceImpl.selByRid(user.getRid()));
			user.setUrls(urlSreviceImpl.selByRid(user.getRid()));
			map.put("allurl", urlSreviceImpl.selAll());
		}
		map.put("user", user);
		return map;
	}

}
