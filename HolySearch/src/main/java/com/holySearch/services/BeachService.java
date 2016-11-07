package com.holySearch.services;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.holySearch.bean.User;
import com.holySearch.dao.BeachBeanDAO;

@Component
public class BeachService {

	@Autowired
	BeachBeanDAO mBeachBeanDAO;
	
	
}
