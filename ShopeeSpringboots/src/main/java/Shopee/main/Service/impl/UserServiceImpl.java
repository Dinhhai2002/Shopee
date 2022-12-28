package Shopee.main.Service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import Shopee.main.Service.iUserService;

import Shopee.main.entity.user;
import Shopee.main.repository.userRepository;

@Service
public class UserServiceImpl implements iUserService{
	@Autowired
	userRepository userRepository;

	@Override
	public user checkLogin(String username) {
		return userRepository.checkLogin(username);
	}

	@Override
	public <S extends user> S save(S entity) {
		return userRepository.save(entity);
	}

	@Override
	public List<user> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Page<user> findAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@Override
	public Optional<user> findById(Integer id) {
		return userRepository.findById(id);
	}

	@Override

	public long count() {
		return userRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		userRepository.deleteById(id);
	}

	@Override
	public void delete(user entity) {
		userRepository.delete(entity);
	}

	@Override
	public void deleteAll() {
		userRepository.deleteAll();
	}

	@Override
	public user getUserByuName(String username) {
		// TODO Auto-generated method stub
		return userRepository.getUserByuName(username);
	}

	@Override
	public void insertAccount(String user, String fullname, String email, String Address, String fullAddress,
			String password, String phoneNumber) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public user CheckEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.CheckEmail(email);
	}

	@Override
	public user CheckPhoneNumber(String phoneNumber) {
		// TODO Auto-generated method stub
		return userRepository.CheckPhoneNumber(phoneNumber);
	}

	@Override
	public user checkUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.checkUsername(username);
	}

	@Override
	public user checkAccountForgot(String username, String email) {
		// TODO Auto-generated method stub
		return userRepository.checkAccountForgot(username, email);
	}

	@Override
	public int numberAccount(String username, String email) {
		// TODO Auto-generated method stub
		return userRepository.numberAccount(username, email);
	}

	@Override
	public void insertAcountGoogle(String user, String email, String image) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public user CheckAccountUpdate(String user, String fullname, String email, String Address, String phoneNumber) {
		// TODO Auto-generated method stub
		return userRepository.CheckAccountUpdate(user, fullname, email, Address, phoneNumber);
	}

	@Override
	public user getById(Integer id) {
		return userRepository.getById(id);
	}

	@Override
	public List<user> getTop10() {
		// TODO Auto-generated method stub
		return userRepository.getTop10();
	}

	@Override
	public Page<user> findByuNameContaining(String name, Pageable pageable) {
		// TODO Auto-generated method stub
		return userRepository.findByuNameContaining(name, pageable);
	}

	
	
	
	
}
