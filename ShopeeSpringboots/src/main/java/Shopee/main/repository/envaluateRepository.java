package Shopee.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import Shopee.main.entity.envaluate;

public interface envaluateRepository extends JpaRepository<envaluate, Integer > {
	@Query(value="select * from evaluate c where c.pId=?1",nativeQuery = true)
	List<envaluate> findAllBypId(int pId);
}
