package com.holySearch.mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.holySearch.bean.Beach;
import com.holySearch.forms.ResultatForm;
import com.holySearch.forms.UserForm;
import com.holySearch.transfert.object.BeachBeanTO;
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

	public BeachBeanTO mapBeachToBeachBeanTO(Beach beach) {
		BeachBeanTO vBeachBeanTO = null;

		if (beach != null) {
			vBeachBeanTO = new BeachBeanTO();

			vBeachBeanTO.setBeachId(beach.getBeachId());
			vBeachBeanTO.setBeachName(beach.getBeachName());
			vBeachBeanTO.setLatitude(beach.getLatitude());
			vBeachBeanTO.setLongitude(beach.getLongitude());

		}

		return vBeachBeanTO;
	}

	public List<BeachBeanTO> mapListBeachToListBeachBeanTO(List<Beach> beach) {
		List<BeachBeanTO> vBeachBeanTO = null;

		if (beach != null) {
			vBeachBeanTO = new ArrayList<BeachBeanTO>();
			for (Beach vBeach : beach) {
				vBeachBeanTO.add(mapBeachToBeachBeanTO(vBeach));
			}
		}

		return vBeachBeanTO;
	}

	public ResultatForm mapBeachBeanTOToResultatForm(BeachBeanTO pBeachBeanTO) {
		ResultatForm vResultatForm = null;

		if (pBeachBeanTO != null) {
			vResultatForm = new ResultatForm();
			vResultatForm.setBeachName(pBeachBeanTO.getBeachName());
			vResultatForm.setLatitude(pBeachBeanTO.getLatitude());
			vResultatForm.setLongitude(pBeachBeanTO.getLongitude());

		}

		return vResultatForm;
	}

}
