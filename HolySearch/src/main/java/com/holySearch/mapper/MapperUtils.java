package com.holySearch.mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

import com.holySearch.forms.UserForm;
import com.holySearch.transfert.object.UserBeanTO;

@Component
public class MapperUtils {

	public UserBeanTO mapUserFormToUserBeanTO(UserForm pUserForm) {

		UserBeanTO vUserBeanTO = null;
		if (pUserForm != null) {
			vUserBeanTO = new UserBeanTO();
			vUserBeanTO.setUserNom(pUserForm.getUserNom());
			vUserBeanTO.setUserEmail(pUserForm.getUserEmail());
			vUserBeanTO.setUserLogin(pUserForm.getUserLogin());
			vUserBeanTO.setUserPassword(pUserForm.getUserPassword());
			SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD");
			try {
				vUserBeanTO.setUserBirthday(format.parse(pUserForm.getUserBirthday()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			vUserBeanTO.setUserPrenom(pUserForm.getUserPrenom());
		}
		return vUserBeanTO;
	}

}
