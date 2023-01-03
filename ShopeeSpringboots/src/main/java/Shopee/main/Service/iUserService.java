package Shopee.main.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetailsService;

import Shopee.main.entity.Product;
import Shopee.main.entity.user;

public interface iUserService extends UserDetailsService {

	void deleteAll();

	void delete(user entity);

	void deleteById(Integer id);

	long count();

	Optional<user> findById(Integer id);

	Page<user> findAll(Pageable pageable);

	List<user> findAll();

	<S extends user> S save(S entity);

	user checkLogin(String username);

	
	public user getUserByuName(String username);
	public void insertAccount(String user,String fullname,String email,String Address,String fullAddress,String password,String phoneNumber);
	
	public user CheckEmail(String email);
	public user CheckPhoneNumber(String phoneNumber); 
	public user checkUsername(String username);
	public user checkAccountForgot(String username,String email);
	public int numberAccount(String username, String email);
	public void insertAcountGoogle(String user,String email,String image);
	 public user CheckAccountUpdate(String user,String fullname,String email,String Address,String phoneNumber);

	user getById(Integer id);
	public List<user> getTop10();
	Page<user> findByuNameContaining(String name,Pageable pageable);
	List<user> findByuName(String username);

}
