package com.bjsxt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjsxt.mapper.ElementMapper;
import com.bjsxt.pojo.Element;
import com.bjsxt.service.ElementService;
@Service
public class ElementServiceImpl implements ElementService {
	@Resource
	private ElementMapper elementMapper;
	@Override
	public List<Element> selByRid(int rid) {
		return elementMapper.selByRid(rid);
	}

}
