package Shopee.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Shopee.main.entity.shop;
import Shopee.main.entity.user;

public interface shopRepository extends JpaRepository<shop, Integer > {
	shop findByUser(user user);
	

}
