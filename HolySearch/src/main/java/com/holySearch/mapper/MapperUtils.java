package com.holySearch.mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.holySearch.bean.Avatar;
import com.holySearch.bean.Destination;
import com.holySearch.bean.User;
import com.holySearch.forms.ResultatForm;
import com.holySearch.forms.UserForm;
import com.holySearch.transfert.object.AvatarTO;
import com.holySearch.transfert.object.DestinationTO;
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
				if (!pUserForm.getUserBirthday().toString().isEmpty()) {
					vUserBeanTO.setUserBirthday(format.parse(pUserForm.getUserBirthday()));
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			vUserBeanTO.setUserPrenom(pUserForm.getUserPrenom());
		}
		return vUserBeanTO;
	}

	public UserForm mapUserBeanTOToUserForm(UserBeanTO pUserBeanTO) {

		UserForm vUserForm = null;
		if (pUserBeanTO != null) {
			vUserForm = new UserForm();
			vUserForm.setUserNom(pUserBeanTO.getUserNom());
			vUserForm.setUserEmail(pUserBeanTO.getUserEmail());
			vUserForm.setUserLogin(pUserBeanTO.getUserLogin());
			vUserForm.setUserPassword(pUserBeanTO.getUserPassword());
			SimpleDateFormat format = new SimpleDateFormat("DD-MM-YYYY");
			if (pUserBeanTO.getUserBirthday() != null) {
				vUserForm.setUserBirthday(format.format(pUserBeanTO.getUserBirthday()));
			}
			vUserForm.setUserPrenom(pUserBeanTO.getUserPrenom());
		}
		return vUserForm;
	}

	public UserBeanTO mapUserToUserBeanTO(User pUser) {

		UserBeanTO vUserBeanTO = null;
		if (pUser != null) {
			vUserBeanTO = new UserBeanTO();
			vUserBeanTO.setUserNom(pUser.getUserNom());
			vUserBeanTO.setUserEmail(pUser.getUserEmail());
			vUserBeanTO.setUserLogin(pUser.getUserLogin());
			vUserBeanTO.setUserPassword(pUser.getUserPassword());
			vUserBeanTO.setUserBirthday(pUser.getUserBirthday());
			vUserBeanTO.setUserPrenom(pUser.getUserPrenom());
		}
		return vUserBeanTO;
	}

	public AvatarTO mapAvatarBeanToAvatarTO(Avatar pAvatar) {
		AvatarTO vAvatarTO = null;
		if (pAvatar != null) {
			vAvatarTO = new AvatarTO();
			vAvatarTO.setAvatarId(pAvatar.getAvatarId());
			vAvatarTO.setAvatarName(pAvatar.getAvatarName());
			vAvatarTO.setPicture(pAvatar.getPicture());
		}
		return vAvatarTO;
	}

	public DestinationTO mapDestinationBeanToDestinationTO(Destination pDestination) {
		DestinationTO vDestinationTO = null;
		if (pDestination != null) {
			vDestinationTO = new DestinationTO();
			vDestinationTO.setDestinationFrenchName(pDestination.getDestinationFrenchName());
			vDestinationTO.setDestinationEnglishName(pDestination.getDestinationEnglishName());
			vDestinationTO.setDestinationLatitude(pDestination.getDestinationLatitude());
			vDestinationTO.setDestinationLongitude(pDestination.getDestinationLongitude());
			vDestinationTO.setDestinationId(pDestination.getDestinationId());
			vDestinationTO.setDestinationType(pDestination.getDestinationType());
			vDestinationTO.setDestinationWikiDescription(pDestination.getDestinationWikiDescription());
			vDestinationTO.setDestinationWikiPicture(pDestination.getDestinationWikiPicture());
		}
		return vDestinationTO;
	}

	public List<DestinationTO> mapListDestinationBeanToDestinationTO(List<Destination> pDestinationList) {
		List<DestinationTO> listeDestinationTO = null;
		if (pDestinationList != null && !pDestinationList.isEmpty()) {
			listeDestinationTO = new ArrayList<DestinationTO>();
			for (Destination pDestination : pDestinationList) {
				listeDestinationTO.add(mapDestinationBeanToDestinationTO(pDestination));
			}
		}
		return listeDestinationTO;
	}

	public ResultatForm mapDestinationTOToResultatForm(DestinationTO vDestinationBeanTO) {
		ResultatForm vResultatForm = null;
		if (vDestinationBeanTO != null) {
			vResultatForm = new ResultatForm();
			vResultatForm.setDestinationFrenchName(vDestinationBeanTO.getDestinationFrenchName());
			vResultatForm.setDestinationEnglishName(vDestinationBeanTO.getDestinationEnglishName());
			vResultatForm.setDestinationLatitude(vDestinationBeanTO.getDestinationLatitude());
			vResultatForm.setDestinationLongitude(vDestinationBeanTO.getDestinationLongitude());
			vResultatForm.setDestinationId(vDestinationBeanTO.getDestinationId());
			vResultatForm.setDestinationType(vDestinationBeanTO.getDestinationType());
			vResultatForm.setDestinationWikiDescription(vDestinationBeanTO.getDestinationWikiDescription());
			vResultatForm.setDestinationWikiPicture(vDestinationBeanTO.getDestinationWikiPicture());
		}
		return vResultatForm;
	}
}
