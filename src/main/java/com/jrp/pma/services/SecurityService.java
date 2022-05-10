package com.jrp.pma.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrp.pma.dao.SecurityRepository;
import com.jrp.pma.entities.UserAccount;

@Service
public class SecurityService {

	@Autowired
	SecurityRepository secRepo;
	
	public void save(UserAccount userAccount)
	{
		secRepo.save(userAccount);
	}
}
