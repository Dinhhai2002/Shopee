package Shopee.main.repository;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Shopee.main.entity.user;
@Repository
public interface userRepository extends JpaRepository<user, Integer >  {
	Page<user> findByuNameContaining(String name,Pageable pageable);
	public user getUserByuName(String username);
	
	@Query(value="select * from [user] where uName=?1",nativeQuery = true)
	public user checkLogin(String username);
	
	@Query(value="insert into [user] values(?1,?2,?3,?4,?5,?6,?7,1,0,null,getdate())",nativeQuery = true)
	public void insertAccount(String user,String fullname,String email,String Address,String fullAddress,String password,String phoneNumber);
	
	@Query(value="select * from [user] where  uEmail =?1",nativeQuery = true)
	 public user CheckEmail(String email);
	 
	@Query(value="select * from [user] where  uPhone =?1",nativeQuery = true)
	 public user CheckPhoneNumber(String phoneNumber);
	
	@Query(value="select * from [user] where uName=?1",nativeQuery = true)
	public user checkUsername(String username);
	
	@Query(value="select * from [user] where uName=?1 and uEmail=?2",nativeQuery = true)
	public user checkAccountForgot(String username,String email);
	
	@Query(value="select count(*) from [user] where uName=? and uEmail=?",nativeQuery = true)
	public int numberAccount(String username, String email);
	
	@Query(value="insert into [user] values(?,null,?,null,null,null,null,1,1,?,getdate())",nativeQuery = true)
	 public void insertAcountGoogle(String user,String email,String image);
	
	@Query(value="select * from [user] where [uName]=? and [uFullName]=? and uEmail=? and uAddress=? and uPhone=?",nativeQuery = true)
	 public user CheckAccountUpdate(String user,String fullname,String email,String Address,String phoneNumber);
	
	@Query(value="select * from [user] where uName=?1",nativeQuery = true)
	List<user> findByuName(String username);
	@Query(value="select top 10 * from [user]",nativeQuery = true)
	public List<user> getTop10();
}
