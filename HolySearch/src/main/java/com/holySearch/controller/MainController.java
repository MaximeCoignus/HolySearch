package com.holySearch.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.holySearch.bean.Avatar;
import com.holySearch.forms.AvatarForm;
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
import com.holySearch.services.AvatarService;
import com.holySearch.services.BeachService;
import com.holySearch.services.UserService;
import com.holySearch.transfert.object.BeachBeanTO;

@Controller
public class MainController  implements HandlerExceptionResolver {

	private static final Logger log = Logger.getLogger(MainController.class);

	@Resource
	UserService mUserService;

	@Resource
	BeachService mBeachService;

	@Resource
	AvatarService mAvatarService;

	@Autowired
	private MapperUtils mMapperUtils;

	private static final String INDEX_DIR = "src/main/resources/index";

	private static final int DEFAULT_RESULT_SIZE = 100;

	public static final String ATT_SESSION_USER = "sessionUtilisateur";

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String afficher(@ModelAttribute(value = "userForm") final UserForm puserForm, final ModelMap pModel,
			HttpSession session) {
		if (session.getAttribute(ATT_SESSION_USER) != null) {
			return "search";
		} else
			return "index";
	}

	@RequestMapping(value = "mentions-legales", method = RequestMethod.GET)
	public String afficherMentionsLegales(@ModelAttribute(value = "userForm") final UserForm puserForm,
			final ModelMap pModel, HttpSession session) {

		return "mentions-legales";
	}

	@RequestMapping(value = "modifier-photo-profil", method = RequestMethod.GET)
	public String afficherPageModificationParametres(@ModelAttribute(value = "userForm") final UserForm puserForm,
			final ModelMap pModel, HttpSession session) throws UnsupportedEncodingException {
		if (session.getAttribute(ATT_SESSION_USER) != null) {
			// Récupérer l'avatar de l'utilisateur à partir de son nom (variable
			// de session ATT_SESSION_USER)
			byte[] avatar = mAvatarService.getImageAvatar(session.getAttribute(ATT_SESSION_USER).toString());
			pModel.addAttribute("avatarUser", org.apache.commons.codec.binary.Base64.encodeBase64String(avatar));
			return "modifier-photo-profil";
		} else
			return "index";
	}

	@RequestMapping(value = "enregistrer-photo-profil", method = RequestMethod.POST)
	public String enregistrerPhotoProfil(@ModelAttribute(value = "avatarForm") final AvatarForm pAvatarForm,
			final ModelMap pModel, HttpSession session) throws IOException {
		if (session.getAttribute(ATT_SESSION_USER) != null) {
			String redirect = "mon-profil";
			if (pAvatarForm != null) {
				MultipartFile file = pAvatarForm.getUserAvatarFile();
				if (!file.isEmpty()) {
					mAvatarService.updateAvatar(session.getAttribute(ATT_SESSION_USER).toString(),
							pAvatarForm.getUserAvatarFile().getBytes());
				}

			}

			// Récupérer les informations de l'utilisateur à partir de son nom
			// (variable de session ATT_SESSION_USER)
			UserForm vUserForm = mMapperUtils.mapUserBeanTOToUserForm(mMapperUtils
					.mapUserToUserBeanTO(mUserService.getUserByNom(session.getAttribute(ATT_SESSION_USER).toString())));

			// Récupérer l'avatar de l'utilisateur à partir de son nom (variable
			// de session ATT_SESSION_USER)
			byte[] avatar = mAvatarService.getImageAvatar(session.getAttribute(ATT_SESSION_USER).toString());
			pModel.addAttribute("avatarUser", org.apache.commons.codec.binary.Base64.encodeBase64String(avatar));
			pModel.addAttribute("infoUser", vUserForm);

			return redirect;
		} else
			return "index";
	}
	
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception exception)
	{
		Map<String, Object> model = new HashMap<String, Object>();
		if (exception instanceof MaxUploadSizeExceededException)
		{
			model.put("errors", "Fichier trop volumineux, veuillez sélectionner une autre image (< 500 ko)");
		} else 
		{
			model.put("errors", "Unexpected error : " + exception.getMessage());
		}
		model.put("avatar", new Avatar());
		return new ModelAndView("modifier-photo-profil", model);
	}
	
	@RequestMapping(value = "enregistrer-photo-profil", method = RequestMethod.GET)
	public String enregistrerPhotoProfilGet(@ModelAttribute(value = "avatarForm") final AvatarForm pAvatarForm,
			final ModelMap pModel, HttpSession session) throws IOException {
		if (session.getAttribute(ATT_SESSION_USER) != null) {
			String redirect = "mon-profil";
			// Récupérer les informations de l'utilisateur à partir de son nom
			// (variable de session ATT_SESSION_USER)
			UserForm vUserForm = mMapperUtils.mapUserBeanTOToUserForm(mMapperUtils
					.mapUserToUserBeanTO(mUserService.getUserByNom(session.getAttribute(ATT_SESSION_USER).toString())));

			// Récupérer l'avatar de l'utilisateur à partir de son nom (variable
			// de session ATT_SESSION_USER)
			byte[] avatar = mAvatarService.getImageAvatar(session.getAttribute(ATT_SESSION_USER).toString());
			pModel.addAttribute("avatarUser", org.apache.commons.codec.binary.Base64.encodeBase64String(avatar));
			pModel.addAttribute("infoUser", vUserForm);

			return redirect;
		} else
			return "index";
	}
	
	@RequestMapping(value = "annuler-modification-photo-profil", method = RequestMethod.GET)
	public String annulerModificationPhotoProfil(@ModelAttribute(value = "avatarForm") final AvatarForm pAvatarForm,
			final ModelMap pModel, HttpSession session) throws IOException {
		if (session.getAttribute(ATT_SESSION_USER) != null) {
			String redirect = "mon-profil";
			// Récupérer les informations de l'utilisateur à partir de son nom
			// (variable de session ATT_SESSION_USER)
			UserForm vUserForm = mMapperUtils.mapUserBeanTOToUserForm(mMapperUtils
					.mapUserToUserBeanTO(mUserService.getUserByNom(session.getAttribute(ATT_SESSION_USER).toString())));

			// Récupérer l'avatar de l'utilisateur à partir de son nom (variable
			// de session ATT_SESSION_USER)
			byte[] avatar = mAvatarService.getImageAvatar(session.getAttribute(ATT_SESSION_USER).toString());
			pModel.addAttribute("avatarUser", org.apache.commons.codec.binary.Base64.encodeBase64String(avatar));
			pModel.addAttribute("infoUser", vUserForm);

			return redirect;
		} else
			return "index";
	}

	@RequestMapping(value = "mon-profil", method = RequestMethod.GET)
	public String afficherPageProfil(@ModelAttribute(value = "userForm") final UserForm puserForm,
			final ModelMap pModel, HttpSession session) throws UnsupportedEncodingException {
		if (session.getAttribute(ATT_SESSION_USER) != null) {
			// Récupérer les informations de l'utilisateur à partir de son nom
			// (variable de session ATT_SESSION_USER)
			UserForm vUserForm = mMapperUtils.mapUserBeanTOToUserForm(mMapperUtils
					.mapUserToUserBeanTO(mUserService.getUserByNom(session.getAttribute(ATT_SESSION_USER).toString())));

			// Récupérer l'avatar de l'utilisateur à partir de son nom (variable
			// de session ATT_SESSION_USER)
			byte[] avatar = mAvatarService.getImageAvatar(session.getAttribute(ATT_SESSION_USER).toString());
			pModel.addAttribute("avatarUser", org.apache.commons.codec.binary.Base64.encodeBase64String(avatar));
			pModel.addAttribute("infoUser", vUserForm);

			return "mon-profil";
		} else
			return "index";
	}

	@RequestMapping(value = "/a-propos", method = RequestMethod.GET)
	public String afficherAProposConnected(@ModelAttribute(value = "userForm") final UserForm puserForm,
			final ModelMap pModel, HttpSession session) {
		return "a-propos";
	}

	@RequestMapping(value = "/qui-sommes-nous", method = RequestMethod.GET)
	public String afficherQuiSommesNousConnected(@ModelAttribute(value = "userForm") final UserForm puserForm,
			final ModelMap pModel, HttpSession session) {
		return "qui-sommes-nous";
	}

	@RequestMapping(value = "/accueil", method = RequestMethod.GET)
	public String afficherAccueil(@ModelAttribute(value = "userForm") final UserForm puserForm, final ModelMap pModel,
			HttpSession session) {
		if (session.getAttribute(ATT_SESSION_USER) != null) {
			return "search";
		} else
			return "index";
	}

	private void contactezNous(String nom, String prenom, String email, String objet, String message) {
		EnvoiMail vEnvoiMail = new EnvoiMail(nom, prenom, email, objet, message);
		vEnvoiMail.sendMailContactezNous();
	}

	@RequestMapping(value = "/contactez-nous", method = RequestMethod.POST)
	public String contactezNousConnected(@Valid @ModelAttribute(value = "contactForm") final ContactForm pContactForm,
			final BindingResult pBindingResult, final ModelMap pModel, HttpSession session)
			throws UnsupportedEncodingException {
		if (!pContactForm.getNom().isEmpty() && !pContactForm.getPrenom().isEmpty()
				&& !pContactForm.getEmail().isEmpty() && !pContactForm.getObjet().isEmpty()
				&& !pContactForm.getMessage().isEmpty()) {
			contactezNous(pContactForm.getNom(), pContactForm.getPrenom(), pContactForm.getEmail(),
					pContactForm.getObjet(), pContactForm.getMessage());
		}
		if (session.getAttribute(ATT_SESSION_USER) != null) {
			return "search";
		} else
			return "index";
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(@ModelAttribute(value = "userForm") final UserForm puserForm, final ModelMap pModel,
			HttpSession session) {
		if (session.getAttribute(ATT_SESSION_USER) != null) {
			return "search";
		} else
			return "index";
	}

	@RequestMapping(value = "/deconnexion", method = RequestMethod.GET)
	public String deconnecter(@ModelAttribute(value = "userForm") final UserForm puserForm, final ModelMap pModel,
			HttpSession session) {
		session.invalidate();
		return "index";
	}

	@RequestMapping(value = "/createUserAccount", method = RequestMethod.POST)
	public String createUserAccount(@Valid @ModelAttribute(value = "userForm") final UserForm puserForm,
			final BindingResult pBindingResult, final ModelMap pModel, HttpSession session) throws IOException {

		String redirect = "inscription";
		if (puserForm != null) {
			log.trace(puserForm.getUserBirthday());
			MultipartFile file = puserForm.getUserAvatarFile();
			if (puserForm.getUserConfirmPassword().equals(puserForm.getUserPassword())) {
				if (puserForm.getUserLogin() != null && !file.isEmpty()) {
					mAvatarService.createNewAvatar(puserForm.getUserLogin(), puserForm.getUserAvatarFile().getBytes());
				}
				if (mUserService.createNewUser(mMapperUtils.mapUserFormToUserBeanTO(puserForm))) {
					EnvoiMail vEnvoiMail = new EnvoiMail(puserForm.getUserEmail(), puserForm.getUserPassword(),
							puserForm.getUserLogin());
					vEnvoiMail.sendMailCreationCompte();
					pModel.clear();
					session.setAttribute(ATT_SESSION_USER, puserForm.getUserLogin());
					redirect = "search";
				}
			}
		}

		return redirect;

	}

	@RequestMapping(value = "/connexion", method = RequestMethod.POST)
	public String checkExistence(@Valid @ModelAttribute(value = "connexionForm") final ConnexionForm pConnexionForm,
			final BindingResult pBindingResult, final ModelMap pModel, HttpSession session) throws IOException {

		String redirect = "index";
		if (mUserService.userBeanExist(pConnexionForm.getLogin(), pConnexionForm.getPassword())) {
			session.setAttribute(ATT_SESSION_USER, pConnexionForm.getLogin());
			insertIndex();
			redirect = "search";
		}

		return redirect;

	}

	@RequestMapping(value = "/connexion", method = RequestMethod.GET)
	public String connexion(@Valid @ModelAttribute(value = "connexionForm") final ConnexionForm pConnexionForm,
			final BindingResult pBindingResult, final ModelMap pModel, HttpSession session) throws IOException {
		if (session.getAttribute(ATT_SESSION_USER) != null) {
			return "search";
		} else
			return "index";

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
	public String inscription(@ModelAttribute(value = "userForm") final UserForm puserForm, final ModelMap pModel,
			HttpSession session) throws UnsupportedEncodingException {
		if (session.getAttribute(ATT_SESSION_USER) == null) {
			return "inscription";
		} else
			return "index";
	}

	@RequestMapping(value = "/rechercher", method = RequestMethod.GET)
	public String afficher(@Valid @ModelAttribute(value = "searchForm") final SearchForm pSearchForm,
			final ModelMap pModel, HttpSession session) throws Exception {
		if (session.getAttribute(ATT_SESSION_USER) != null) {
			// Traitement avec Lucene
			if (pSearchForm != null && pSearchForm.getObjetSearch() != null
					&& !pSearchForm.getObjetSearch().isEmpty()) {
				BeachSearcher searcher = new BeachSearcher(INDEX_DIR);
				List<BeachIndexItem> result = searcher.findByBeachName(pSearchForm.getObjetSearch(),
						DEFAULT_RESULT_SIZE);

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
		} else
			return "index";
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
