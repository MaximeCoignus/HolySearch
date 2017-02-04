package com.holySearch.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

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
import com.holySearch.forms.ContactForm;
import com.holySearch.forms.InsertBeachesForm;
import com.holySearch.forms.ReinitialiserPasswordForm;
import com.holySearch.forms.ResultatForm;
import com.holySearch.forms.SearchForm;
import com.holySearch.forms.UserForm;
import com.holySearch.lucene.BeachIndexItem;
import com.holySearch.lucene.BeachIndexer;
import com.holySearch.lucene.BeachSearcher;
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

	private static final String INDEX_DIR = "src/main/resources/index";

	private static final int DEFAULT_RESULT_SIZE = 100;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String afficher(@ModelAttribute(value = "userForm") final UserForm puserForm, final ModelMap pModel) {
		return "index";
	}

	@RequestMapping(value = "/a-propos-connected", method = RequestMethod.GET)
	public String afficherAProposConnected(@ModelAttribute(value = "userForm") final UserForm puserForm,
			final ModelMap pModel) {
		pModel.addAttribute("identifiant", "identifiant");
		return "a-propos";
	}

	@RequestMapping(value = "/qui-sommes-nous-connected", method = RequestMethod.GET)
	public String afficherQuiSommesNousConnected(@ModelAttribute(value = "userForm") final UserForm puserForm,
			final ModelMap pModel) {
		pModel.addAttribute("identifiant", "identifiant");
		return "qui-sommes-nous";
	}

	@RequestMapping(value = "/a-propos-disconnected", method = RequestMethod.GET)
	public String afficherAProposDisconnected(@ModelAttribute(value = "userForm") final UserForm puserForm,
			final ModelMap pModel) {
		return "a-propos";
	}

	@RequestMapping(value = "/qui-sommes-nous-disconnected", method = RequestMethod.GET)
	public String afficherQuiSommesNousDisconnected(@ModelAttribute(value = "userForm") final UserForm puserForm,
			final ModelMap pModel) {
		return "qui-sommes-nous";
	}

	@RequestMapping(value = "/accueil", method = RequestMethod.GET)
	public String afficherAccueil(@ModelAttribute(value = "userForm") final UserForm puserForm, final ModelMap pModel) {
		return "index";
	}

	private void contactezNous(String nom, String prenom, String email, String objet, String message) {
		EnvoiMail vEnvoiMail = new EnvoiMail(nom, prenom, email, objet, message);
		vEnvoiMail.sendMailContactezNous();
	}

	@RequestMapping(value = "/contactez-nous-connected", method = RequestMethod.POST)
	public String contactezNousConnected(@Valid @ModelAttribute(value = "contactForm") final ContactForm pContactForm,
			final BindingResult pBindingResult, final ModelMap pModel) throws UnsupportedEncodingException {
		pModel.addAttribute("identifiant", "identifiant");
		if (!pContactForm.getNom().isEmpty() && !pContactForm.getPrenom().isEmpty()
				&& !pContactForm.getEmail().isEmpty() && !pContactForm.getObjet().isEmpty()
				&& !pContactForm.getMessage().isEmpty()) {
			contactezNous(pContactForm.getNom(), pContactForm.getPrenom(), pContactForm.getEmail(),
					pContactForm.getObjet(), pContactForm.getMessage());
		}
		return "search";
	}

	@RequestMapping(value = "/contactez-nous-disconnected", method = RequestMethod.POST)
	public String contactezNousDisconnected(
			@Valid @ModelAttribute(value = "contactForm") final ContactForm pContactForm,
			final BindingResult pBindingResult, final ModelMap pModel) throws UnsupportedEncodingException {
		if (!pContactForm.getNom().isEmpty() && !pContactForm.getPrenom().isEmpty()
				&& !pContactForm.getEmail().isEmpty() && !pContactForm.getObjet().isEmpty()
				&& !pContactForm.getMessage().isEmpty()) {
			contactezNous(pContactForm.getNom(), pContactForm.getPrenom(), pContactForm.getEmail(),
					pContactForm.getObjet(), pContactForm.getMessage());
		}
		return "index";
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(@ModelAttribute(value = "userForm") final UserForm puserForm, final ModelMap pModel) {
		pModel.addAttribute("identifiant", "identifiant");
		return "search";
	}

	@RequestMapping(value = "/deconnexion", method = RequestMethod.GET)
	public String deconnecter(@ModelAttribute(value = "userForm") final UserForm puserForm, final ModelMap pModel) {
		pModel.clear();
		return "index";
	}

	@RequestMapping(value = "/createUserAccount", method = RequestMethod.POST)
	public String createUserAccount(@Valid @ModelAttribute(value = "userForm") final UserForm puserForm,
			final BindingResult pBindingResult, final ModelMap pModel) throws UnsupportedEncodingException {

		String redirect = "inscription";
		log.trace(puserForm.getUserBirthday());
		if (puserForm.getUserConfirmPassword().equals(puserForm.getUserPassword())
				&& mUserService.createNewUser(mMapperUtils.mapUserFormToUserBeanTO(puserForm))) {
			EnvoiMail vEnvoiMail = new EnvoiMail(puserForm.getUserEmail(), puserForm.getUserPassword(),
					puserForm.getUserLogin());
			vEnvoiMail.sendMailCreationCompte();
			pModel.clear();
			pModel.addAttribute("identifiant", puserForm.getUserLogin());
			redirect = "search";
		}

		return redirect;

	}

	@RequestMapping(value = "/connexion", method = RequestMethod.POST)
	public String checkExistence(@Valid @ModelAttribute(value = "connexionForm") final ConnexionForm pConnexionForm,
			final BindingResult pBindingResult, final ModelMap pModel) throws IOException {

		String redirect = "index";
		if (mUserService.userBeanExist(pConnexionForm.getLogin(), pConnexionForm.getPassword())) {
			pModel.addAttribute("identifiant", pConnexionForm.getLogin());
			insertIndex();
			redirect = "search";
		}

		return redirect;

	}

	@RequestMapping(value = "/connexion", method = RequestMethod.GET)
	public String connexion(@Valid @ModelAttribute(value = "connexionForm") final ConnexionForm pConnexionForm,
			final BindingResult pBindingResult, final ModelMap pModel) throws IOException {
		pModel.addAttribute("identifiant", "identifiant");
		String redirect = "search";
		return redirect;

	}

	private void insertIndex() throws IOException {
		// the items to be indexed
		List<BeachBeanTO> vListeBeach = mBeachService.getAllBeaches();
		List<BeachIndexItem> vListeBeachIndexItem = null;
		if (vListeBeach != null) {
			vListeBeachIndexItem = new ArrayList<BeachIndexItem>();
			for (BeachBeanTO vBeachBeanTO : vListeBeach) {
				vListeBeachIndexItem.add(new BeachIndexItem(vBeachBeanTO.getBeachId(), vBeachBeanTO.getBeachName()));
			}
		}

		BeachIndexer indexer = new BeachIndexer(INDEX_DIR);
		for (BeachIndexItem indexItem : vListeBeachIndexItem) {
			indexer.index(indexItem);
		}

		indexer.close();

	}

	@RequestMapping(value = "/reinitialiserPassword", method = RequestMethod.GET)
	public String reinitialiserPassword(
			@ModelAttribute(value = "reinitialiserPasswordForm") final ReinitialiserPasswordForm pReinitialiserPasswordForm,
			final BindingResult pBindingResult, final ModelMap pModel) throws UnsupportedEncodingException {
		String redirect = "reinitialiserPassword";
		if (mUserService.changeUserPassword(pReinitialiserPasswordForm.getEmail(),
				pReinitialiserPasswordForm.getPassword(), pReinitialiserPasswordForm.getConfirmPassword())) {
			EnvoiMail vEnvoiMail = new EnvoiMail(pReinitialiserPasswordForm.getEmail(),
					pReinitialiserPasswordForm.getPassword());
			vEnvoiMail.sendMailReinitialisation();
			pModel.clear();
			redirect = "index";
		}
		return redirect;
	}

	@RequestMapping(value = "/connexionHoly", method = RequestMethod.GET)
	public String connexionHoly(@ModelAttribute(value = "userForm") final UserForm puserForm, final ModelMap pModel)
			throws UnsupportedEncodingException {
		return "connexionHoly";

	}

	@RequestMapping(value = "/inscription", method = RequestMethod.GET)
	public String inscription(@ModelAttribute(value = "userForm") final UserForm puserForm, final ModelMap pModel)
			throws UnsupportedEncodingException {
		return "inscription";

	}

	@RequestMapping(value = "/rechercher", method = RequestMethod.GET)
	public String afficher(@Valid @ModelAttribute(value = "searchForm") final SearchForm pSearchForm,
			final ModelMap pModel) throws Exception {
		// Traitement de la recherche
		pModel.addAttribute("identifiant", "identifiant");

		// Traitement avec Lucene
		if (pSearchForm != null && pSearchForm.getObjetSearch() != null && !pSearchForm.getObjetSearch().isEmpty()) {
			BeachSearcher searcher = new BeachSearcher(INDEX_DIR);
			List<BeachIndexItem> result = searcher.findByBeachName(pSearchForm.getObjetSearch(), DEFAULT_RESULT_SIZE);

			List<ResultatForm> vListeResultatForm = null;
			if (result != null) {
				vListeResultatForm = new ArrayList<ResultatForm>();
				for (BeachIndexItem vBeachIndexItem : result) {
					BeachBeanTO vBeachBeanTO = mBeachService.getBeachByNom(vBeachIndexItem.getBeachName());
					if (vBeachBeanTO != null) {
						vListeResultatForm.add(mMapperUtils.mapBeachBeanTOToResultatForm(vBeachBeanTO));
					}
				}
			}
			pModel.addAttribute("listeResultatForm", vListeResultatForm);
		}

		return "resultat";
	}

	@RequestMapping(value = "/insertBeaches", method = RequestMethod.POST)
	public String insertBeaches(@ModelAttribute(value = "insertBeachesForm") final InsertBeachesForm insertBeachesForm,
			final ModelMap pModel) throws UnsupportedEncodingException {
		if (insertBeachesForm != null && !insertBeachesForm.getUrl().isEmpty()) {
			mBeachService.insertBeaches(insertBeachesForm.getUrl());
		}
		return "insertBeaches";

	}

	@RequestMapping(value = "/insertBeachesForm", method = RequestMethod.GET)
	public String insertBeachesForm(
			@ModelAttribute(value = "insertBeachesForm") final InsertBeachesForm insertBeachesForm,
			final ModelMap pModel) throws UnsupportedEncodingException {
		return "insertBeaches";
	}

}
