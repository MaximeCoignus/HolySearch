package com.holySearch.controller;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.holySearch.forms.ConnexionForm;
import com.holySearch.forms.ReinitialiserPasswordForm;
import com.holySearch.forms.ResultatForm;
import com.holySearch.forms.SearchForm;
import com.holySearch.forms.UserForm;
import com.holySearch.mapper.MapperUtils;
import com.holySearch.reinitialiserPassword.EnvoiMail;
import com.holySearch.services.BeachService;
import com.holySearch.services.UserService;
import com.holySearch.transfert.object.BeachBeanTO;

@Controller
public class MainController {

	private static final Logger log = Logger.getLogger(MainController.class);

	@Resource
	UserService mUserService;
	
	@Resource
	BeachService mBeachService;

	@Autowired
	private MapperUtils mMapperUtils;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String afficher(@ModelAttribute(value = "userForm") final UserForm puserForm, final ModelMap pModel) {
		pModel.addAttribute("headerValue", "Recherchez toutes vos destinations sur HolySearch");
		return "index";
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(@ModelAttribute(value = "userForm") final UserForm puserForm, final ModelMap pModel) {
		pModel.addAttribute("headerValue", "Recherchez toutes vos destinations sur HolySearch");
		pModel.addAttribute("identifiant", "identifiant");
		return "search";
	}

	@RequestMapping(value = "/deconnexion", method = RequestMethod.GET)
	public String deconnecter(@ModelAttribute(value = "userForm") final UserForm puserForm, final ModelMap pModel) {
		pModel.clear();
		pModel.addAttribute("headerValue", "Recherchez toutes vos destinations sur HolySearch");
		return "index";
	}

	@RequestMapping(value = "/createUserAccount", method = RequestMethod.POST)
	public String createUserAccount(@Valid @ModelAttribute(value = "userForm") final UserForm puserForm,
			final BindingResult pBindingResult, final ModelMap pModel) throws UnsupportedEncodingException {

		String redirect = "inscription";
		pModel.addAttribute("headerValue", "Inscrivez-vous sur HolySearch");
		log.trace(puserForm.getUserBirthday());
		if (puserForm.getUserConfirmPassword().equals(puserForm.getUserPassword())
				&& mUserService.createNewUser(mMapperUtils.mapUserFormToUserBeanTO(puserForm))) {
			EnvoiMail vEnvoiMail = new EnvoiMail(puserForm.getUserEmail(), puserForm.getUserPassword(),
					puserForm.getUserLogin());
			vEnvoiMail.sendMailCreationCompte();
			pModel.clear();
			pModel.addAttribute("identifiant", puserForm.getUserLogin());
			pModel.addAttribute("headerValue", "Recherchez toutes vos destinations sur HolySearch");
			redirect = "search";
		}

		return redirect;

	}

	@RequestMapping(value = "/connexion", method = RequestMethod.POST)
	public String checkExistence(@Valid @ModelAttribute(value = "connexionForm") final ConnexionForm pConnexionForm,
			final BindingResult pBindingResult, final ModelMap pModel) throws UnsupportedEncodingException {

		String redirect = "index";
		pModel.addAttribute("headerValue", "Recherchez toutes vos destinations sur HolySearch");
		if (mUserService.userBeanExist(pConnexionForm.getLogin(), pConnexionForm.getPassword())) {
			pModel.addAttribute("identifiant", pConnexionForm.getLogin());
			redirect = "search";
		}

		return redirect;

	}

	@RequestMapping(value = "/reinitialiserPassword", method = RequestMethod.GET)
	public String reinitialiserPassword(
			@ModelAttribute(value = "reinitialiserPasswordForm") final ReinitialiserPasswordForm pReinitialiserPasswordForm,
			final BindingResult pBindingResult, final ModelMap pModel) throws UnsupportedEncodingException {
		String redirect = "reinitialiserPassword";
		pModel.addAttribute("headerValue", "Reinitialiser votre mot de passe");
		if (mUserService.changeUserPassword(pReinitialiserPasswordForm.getEmail(),
				pReinitialiserPasswordForm.getPassword(), pReinitialiserPasswordForm.getConfirmPassword())) {
			EnvoiMail vEnvoiMail = new EnvoiMail(pReinitialiserPasswordForm.getEmail(),
					pReinitialiserPasswordForm.getPassword());
			vEnvoiMail.sendMailReinitialisation();
			pModel.clear();
			pModel.addAttribute("headerValue", "Recherchez toutes vos destinations sur HolySearch");
			redirect = "index";
		}
		return redirect;
	}

	@RequestMapping(value = "/inscription", method = RequestMethod.GET)
	public String inscription(@ModelAttribute(value = "userForm") final UserForm puserForm, final ModelMap pModel)
			throws UnsupportedEncodingException {
		pModel.addAttribute("headerValue", "Inscrivez-vous sur HolySearch");
		return "inscription";

	}

	@RequestMapping(value = "/rechercher", method = RequestMethod.GET)
	public String afficher(@Valid @ModelAttribute(value = "searchForm") final SearchForm pSearchForm,
			final ModelMap pModel) throws UnsupportedEncodingException {
		// Traitement de la recherche
		pModel.addAttribute("headerValue", "Resultat de votre recherche sur HolySearch");
		
		if(pSearchForm!= null && pSearchForm.getObjetSearch() != null && !pSearchForm.getObjetSearch().isEmpty()){
			BeachBeanTO vBeachBeanTO = mBeachService.getBeachByNom(pSearchForm.getObjetSearch());
			
			ResultatForm vResultatForm = mMapperUtils.mapBeachBeanTOToResultatForm(vBeachBeanTO);
			
			pModel.addAttribute("resultatForm", vResultatForm);
		}
		
		return "resultat";
	}

}
