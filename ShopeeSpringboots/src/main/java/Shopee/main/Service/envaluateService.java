package Shopee.main.Service;

import java.util.List;

import Shopee.main.entity.envaluate;

public interface envaluateService {

	void deleteAll();

	envaluate getById(Integer id);

	long count();

	List<envaluate> findAllById(Iterable<Integer> ids);

	List<envaluate> findAll();

	<S extends envaluate> S save(S entity);
	List<envaluate> findAllBypId(int pId);

	

}
