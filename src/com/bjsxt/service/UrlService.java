package com.bjsxt.service;

import java.util.List;

import com.bjsxt.pojo.Url;

public interface UrlService {
	List<Url> selByRid(int rid);
	
	List<Url> selAll();
}
