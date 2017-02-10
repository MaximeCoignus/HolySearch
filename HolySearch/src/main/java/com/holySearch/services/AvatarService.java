package com.holySearch.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.holySearch.bean.Avatar;
import com.holySearch.dao.AvatarBeanDAO;

@Component
public class AvatarService {

	@Autowired
	AvatarBeanDAO mAvatarBeanDAO;

	/*
	 * Verifier si un bean existe
	 */

	public byte[] getImageAvatar(String nom) throws UnsupportedEncodingException {
		if (nom != null) {
			Avatar vAvatarBean = mAvatarBeanDAO.getAvatarBeanByNom(nom);
			if (vAvatarBean != null) {
				return vAvatarBean.getPicture();
			}
		}
		return null;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean createNewAvatar(String nom, byte[] image) throws IOException {
		if (nom != null && image != null) {
			mAvatarBeanDAO.createNewAvatar(nom, image);
			return true;
		}
		return false;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateAvatar(String nom, byte[] image) throws IOException {
		if (nom != null && image != null) {
			mAvatarBeanDAO.updateAvatar(nom, image);
		}
	}
}