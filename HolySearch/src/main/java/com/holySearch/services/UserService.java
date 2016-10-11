package com.holySearch.services;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.holySearch.bean.User;
import com.holySearch.dao.UserBeanDAO;
import com.holySearch.transfert.object.UserBeanTO;

@Component
public class UserService {

	@Autowired
	UserBeanDAO mUserBeanDAO;
	
	/*
	 * Verifier si un bean existe
	 */
	
	public boolean userBeanExist(String login, String password) throws UnsupportedEncodingException {
		if(login != null && password != null){
			User vUserBean = mUserBeanDAO.getUserBeanByNomAndPassword(login, password);
			if(vUserBean != null){
				return true;
			}
		}
		return false;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean createNewUser(UserBeanTO pTestBeanTO) throws UnsupportedEncodingException {
		if(pTestBeanTO != null&& pTestBeanTO.getUserNom() != null){
			mUserBeanDAO.createNewUser(pTestBeanTO.getUserNom(), pTestBeanTO.getUserPrenom(), pTestBeanTO.getUserEmail(), pTestBeanTO.getUserLogin(), pTestBeanTO.getUserPassword(), pTestBeanTO.getUserBirthday());
			return true;
		}
		return false;
	}

	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean changeUserPassword(String email, String password, String confirmPassword) throws UnsupportedEncodingException {
		if(email != null && password != null && confirmPassword != null && password.equals(confirmPassword)){
			User vUserBean = mUserBeanDAO.modifyUserPassword(email, password);
			if(vUserBean != null){
				return true;
			}
		}
		return false;
	}

}
