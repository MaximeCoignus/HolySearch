package com.holySearch.mapper;

import org.springframework.stereotype.Component;

import com.holySearch.forms.UserForm;
import com.holySearch.transfert.object.UserBeanTO;

@Component
public class MapperUtils {

	public UserBeanTO mapUserFormToUserBeanTO(UserForm pUserForm) {
		
		UserBeanTO vUserBeanTO = null;
		if(pUserForm != null){
			vUserBeanTO = new UserBeanTO();
			vUserBeanTO.setUserNom(pUserForm.getUserNom());
			vUserBeanTO.setUserEmail(pUserForm.getUserEmail());
			vUserBeanTO.setUserLogin(pUserForm.getUserLogin());
			vUserBeanTO.setUserPassword(pUserForm.getUserPassword());
			if(pUserForm.getUserBirthday() != null){
				vUserBeanTO.setUserBirthday(pUserForm.getUserBirthday());
			}
			if(pUserForm.getUserPrenom() != null){
				vUserBeanTO.setUserPrenom(pUserForm.getUserPrenom());
			}
		}
		return vUserBeanTO;
	}

}
