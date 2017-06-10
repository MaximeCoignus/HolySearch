package com.holySearch.mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.holySearch.bean.Avatar;
import com.holySearch.bean.Destination;
import com.holySearch.bean.User;
import com.holySearch.forms.ContinentForm;
import com.holySearch.forms.CountryForm;
import com.holySearch.forms.ResultatForm;
import com.holySearch.forms.UserForm;
import com.holySearch.transfert.object.AvatarTO;
import com.holySearch.transfert.object.ContinentItemTO;
import com.holySearch.transfert.object.ContinentTO;
import com.holySearch.transfert.object.CountryItemTO;
import com.holySearch.transfert.object.CountryTO;
import com.holySearch.transfert.object.DestinationItemTO;
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

			if (pDestination.getCountry() != null) {
				CountryTO vCountryTO = new CountryTO();
				vCountryTO.setCountryId(pDestination.getCountry().getCountryId());
				vCountryTO.setCountryFrenchName(pDestination.getCountry().getCountryFrenchName());
				vCountryTO.setCountryEnglishName(pDestination.getCountry().getCountryEnglishName());
				vCountryTO.setCountryCurrency(pDestination.getCountry().getCountryCurrency());
				vCountryTO.setCountryCriminality(pDestination.getCountry().getCountryCriminality());
				vCountryTO.setCountryCriminalityLevel(pDestination.getCountry().getCountryCriminalityLevel());
				vCountryTO.setCountryPopulation(pDestination.getCountry().getCountryPopulation());
				vCountryTO.setCountryLatitude(pDestination.getCountry().getCountryLatitude());
				vCountryTO.setCountryLongitude(pDestination.getCountry().getCountryLongitude());
				vCountryTO.setCountryPrecipitation(pDestination.getCountry().getCountryPrecipitation());
				vCountryTO.setCountryPrecipitationLevel(pDestination.getCountry().getCountryPrecipitationLevel());
				vCountryTO.setCountryTemperature(pDestination.getCountry().getCountryTemperature());
				vCountryTO.setCountryTemperatureLevel(pDestination.getCountry().getCountryTemperatureLevel());
				vCountryTO.setCountryWikiDescription(pDestination.getCountry().getCountryWikiDescription());
				vCountryTO.setCountryWikiPicture(pDestination.getCountry().getCountryWikiPicture());

				if (pDestination.getCountry().getContinent() != null) {
					ContinentTO vContinentTO = new ContinentTO();
					vContinentTO.setContinentId(pDestination.getCountry().getContinent().getContinentId());
					vContinentTO
							.setContinentFrenchName(pDestination.getCountry().getContinent().getContinentFrenchName());
					vContinentTO.setContinentEnglishName(
							pDestination.getCountry().getContinent().getContinentEnglishName());
					vContinentTO.setContinentLatitude(pDestination.getCountry().getContinent().getContinentLatitude());
					vContinentTO
							.setContinentLongitude(pDestination.getCountry().getContinent().getContinentLongitude());
					vContinentTO
							.setContinentPopulation(pDestination.getCountry().getContinent().getContinentPopulation());
					vContinentTO.setContinentSizeKilometer(
							pDestination.getCountry().getContinent().getContinentSizeKilometer());
					vContinentTO.setContinentWikiDescription(
							pDestination.getCountry().getContinent().getContinentWikiDescription());
					vContinentTO.setContinentWikiPicture(
							pDestination.getCountry().getContinent().getContinentWikiPicture());

					vCountryTO.setContinent(vContinentTO);
				}

				vDestinationTO.setCountry(vCountryTO);
			}
		}
		return vDestinationTO;
	}

	public DestinationItemTO mapDestinationBeanToDestinationItemTO(Destination pDestination) {
		DestinationItemTO vDestinationItemTO = null;
		if (pDestination != null) {
			vDestinationItemTO = new DestinationItemTO();
			vDestinationItemTO.setDestinationFrenchName(pDestination.getDestinationFrenchName());
			vDestinationItemTO.setDestinationEnglishName(pDestination.getDestinationEnglishName());
			vDestinationItemTO.setDestinationLatitude(pDestination.getDestinationLatitude());
			vDestinationItemTO.setDestinationLongitude(pDestination.getDestinationLongitude());
			vDestinationItemTO.setDestinationId(pDestination.getDestinationId());
			vDestinationItemTO.setDestinationType(pDestination.getDestinationType());

			if (pDestination.getCountry() != null) {
				CountryItemTO vCountryItemTO = new CountryItemTO();
				vCountryItemTO.setCountryId(pDestination.getCountry().getCountryId());
				vCountryItemTO.setCountryFrenchName(pDestination.getCountry().getCountryFrenchName());
				vCountryItemTO.setCountryEnglishName(pDestination.getCountry().getCountryEnglishName());
				vCountryItemTO.setCountryCurrency(pDestination.getCountry().getCountryCurrency());
				vCountryItemTO.setCountryCriminality(pDestination.getCountry().getCountryCriminality());
				vCountryItemTO.setCountryCriminalityLevel(pDestination.getCountry().getCountryCriminalityLevel());
				vCountryItemTO.setCountryPopulation(pDestination.getCountry().getCountryPopulation());
				vCountryItemTO.setCountryLatitude(pDestination.getCountry().getCountryLatitude());
				vCountryItemTO.setCountryLongitude(pDestination.getCountry().getCountryLongitude());
				vCountryItemTO.setCountryPrecipitation(pDestination.getCountry().getCountryPrecipitation());
				vCountryItemTO.setCountryPrecipitationLevel(pDestination.getCountry().getCountryPrecipitationLevel());
				vCountryItemTO.setCountryTemperature(pDestination.getCountry().getCountryTemperature());
				vCountryItemTO.setCountryTemperatureLevel(pDestination.getCountry().getCountryTemperatureLevel());

				if (pDestination.getCountry().getContinent() != null) {
					ContinentItemTO vContinentItemTO = new ContinentItemTO();
					vContinentItemTO.setContinentId(pDestination.getCountry().getContinent().getContinentId());
					vContinentItemTO
							.setContinentFrenchName(pDestination.getCountry().getContinent().getContinentFrenchName());
					vContinentItemTO.setContinentEnglishName(
							pDestination.getCountry().getContinent().getContinentEnglishName());
					vContinentItemTO
							.setContinentLatitude(pDestination.getCountry().getContinent().getContinentLatitude());
					vContinentItemTO
							.setContinentLongitude(pDestination.getCountry().getContinent().getContinentLongitude());
					vContinentItemTO
							.setContinentPopulation(pDestination.getCountry().getContinent().getContinentPopulation());
					vContinentItemTO.setContinentSizeKilometer(
							pDestination.getCountry().getContinent().getContinentSizeKilometer());

					vCountryItemTO.setContinent(vContinentItemTO);
				}

				vDestinationItemTO.setCountry(vCountryItemTO);
			}
		}
		return vDestinationItemTO;
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

	public List<DestinationItemTO> mapListDestinationBeanToDestinationItemTO(List<Destination> pDestinationList) {
		List<DestinationItemTO> listeDestinationItemTO = null;
		if (pDestinationList != null && !pDestinationList.isEmpty()) {
			listeDestinationItemTO = new ArrayList<DestinationItemTO>();
			for (Destination pDestination : pDestinationList) {
				listeDestinationItemTO.add(mapDestinationBeanToDestinationItemTO(pDestination));
			}
		}
		return listeDestinationItemTO;
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

			if (vDestinationBeanTO.getCountry() != null) {
				CountryForm vCountryForm = new CountryForm();
				vCountryForm.setCountryId(vDestinationBeanTO.getCountry().getCountryId());
				vCountryForm.setCountryFrenchName(vDestinationBeanTO.getCountry().getCountryFrenchName());
				vCountryForm.setCountryEnglishName(vDestinationBeanTO.getCountry().getCountryEnglishName());
				vCountryForm.setCountryCurrency(vDestinationBeanTO.getCountry().getCountryCurrency());
				vCountryForm.setCountryCriminality(vDestinationBeanTO.getCountry().getCountryCriminality());
				vCountryForm.setCountryCriminalityLevel(vDestinationBeanTO.getCountry().getCountryCriminalityLevel());
				vCountryForm.setCountryPopulation(vDestinationBeanTO.getCountry().getCountryPopulation());
				vCountryForm.setCountryLatitude(vDestinationBeanTO.getCountry().getCountryLatitude());
				vCountryForm.setCountryLongitude(vDestinationBeanTO.getCountry().getCountryLongitude());
				vCountryForm.setCountryPrecipitation(vDestinationBeanTO.getCountry().getCountryPrecipitation());
				vCountryForm
						.setCountryPrecipitationLevel(vDestinationBeanTO.getCountry().getCountryPrecipitationLevel());
				vCountryForm.setCountryTemperature(vDestinationBeanTO.getCountry().getCountryTemperature());
				vCountryForm.setCountryTemperatureLevel(vDestinationBeanTO.getCountry().getCountryTemperatureLevel());
				vCountryForm.setCountryWikiDescription(vDestinationBeanTO.getCountry().getCountryWikiDescription());
				vCountryForm.setCountryWikiPicture(vDestinationBeanTO.getCountry().getCountryWikiPicture());

				if (vDestinationBeanTO.getCountry().getContinent() != null) {
					ContinentForm vContinentForm = new ContinentForm();
					vContinentForm.setContinentId(vDestinationBeanTO.getCountry().getContinent().getContinentId());
					vContinentForm.setContinentFrenchName(
							vDestinationBeanTO.getCountry().getContinent().getContinentFrenchName());
					vContinentForm.setContinentEnglishName(
							vDestinationBeanTO.getCountry().getContinent().getContinentEnglishName());
					vContinentForm.setContinentLatitude(
							vDestinationBeanTO.getCountry().getContinent().getContinentLatitude());
					vContinentForm.setContinentLongitude(
							vDestinationBeanTO.getCountry().getContinent().getContinentLongitude());
					vContinentForm.setContinentPopulation(
							vDestinationBeanTO.getCountry().getContinent().getContinentPopulation());
					vContinentForm.setContinentSizeKilometer(
							vDestinationBeanTO.getCountry().getContinent().getContinentSizeKilometer());
					vContinentForm.setContinentWikiDescription(
							vDestinationBeanTO.getCountry().getContinent().getContinentWikiDescription());
					vContinentForm.setContinentWikiPicture(
							vDestinationBeanTO.getCountry().getContinent().getContinentWikiPicture());

					vCountryForm.setContinentForm(vContinentForm);
				}

				vResultatForm.setCountryForm(vCountryForm);
			}
		}
		return vResultatForm;
	}
}
