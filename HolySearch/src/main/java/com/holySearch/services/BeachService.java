package com.holySearch.services;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.holySearch.bean.Beach;
import com.holySearch.dao.BeachBeanDAO;
import com.holySearch.mapper.MapperUtils;
import com.holySearch.transfert.object.BeachBeanTO;

@Component
public class BeachService {

	@Autowired
	BeachBeanDAO mBeachBeanDAO;
	
	@Autowired
	private MapperUtils mMapperUtils;
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public BeachBeanTO getBeachByNom(String nom) throws UnsupportedEncodingException {
		
		BeachBeanTO vBeachBeanTO = null;
		
		if(nom != null){
			Beach vBeach = mBeachBeanDAO.getBeachBeanByNom(nom);
			
			if(vBeach != null){
				//Mapping de Beach vers BeanBeanTO
				vBeachBeanTO = mMapperUtils.mapBeachToBeachBeanTO(vBeach);
			}
		}
		return vBeachBeanTO;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void insertBeaches(String url) throws UnsupportedEncodingException {
		if (!url.isEmpty()) {
			mBeachBeanDAO.newBeachInDatabase(url);
		}
	}
}
