package Shopee.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Shopee.main.entity.orderStatus;
@Repository
public interface orderStatusRepository extends JpaRepository<orderStatus, Integer >{

}
