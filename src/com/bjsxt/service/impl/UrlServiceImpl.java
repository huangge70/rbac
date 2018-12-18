package com.bjsxt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjsxt.mapper.UrlMapper;
import com.bjsxt.pojo.Url;
import com.bjsxt.service.UrlService;

@Service
public class UrlServiceImpl implements UrlService{

	@Resource
	private UrlMapper urlMapper;
	@Override
	public List<Url> selByRid(int rid) {
		return urlMapper.selByRid(rid);
	}
	@Override
	public List<Url> selAll() {
		return urlMapper.selAll();
	}
	
}
