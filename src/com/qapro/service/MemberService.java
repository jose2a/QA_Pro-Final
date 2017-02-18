package com.qapro.service;

import java.util.ArrayList;

import com.qapro.dao.EmailValidationDao;
import com.qapro.dao.MemberDao;
import com.qapro.entity.EmailValidation;
import com.qapro.entity.MemberInfo;
import com.qapro.exceptions.ExistingUserException;
import com.qapro.util.ApplicationUtil;

public class MemberService {

	MemberDao memberDao = new MemberDao();
	EmailValidationDao emailValidationDao = new EmailValidationDao();

	public Long addMember(MemberInfo member) throws Exception {

		if (null == memberDao.getMemberByUserName(member.getUserName())) {

			Long memberId = memberDao.insertMember(member);

			System.out.println("Member registered");

			EmailValidation validation = new EmailValidation();
			validation.setUserId(memberId);
			validation.setValidationCode(ApplicationUtil.generateEmailValidationCode(10));
			validation.setEmail(member.getEmail());
			
			emailValidationDao.insertValidation(validation);

			System.out.println("Validation code inserted");
			
			return memberId;
		} else {
			throw new ExistingUserException("The username " + member.getUserName() + ", has already been taken.");
		}
	}
	
	public MemberInfo verifyUserCredentials(String email, String password) throws Exception {
		return memberDao.verifyUserCredentials(email, password);
	}
	
	public void addPermissionsToUser(MemberInfo memberInfo) throws Exception {
		ArrayList<String> permissions = memberDao.getRolePermissions(memberInfo.getMemberRole());
		
		memberInfo.setPrivileges(permissions);
	}

	public static void main(String args[]) throws Exception {

		MemberService memberService = new MemberService();

		MemberInfo member = new MemberInfo();
		member.setEmail("haruki@murakami.com");
		member.setName("haruki");
		member.setSurname("murakami");
		member.setUserName("harukimurakami");

		memberService.addMember(member);

	}

}
