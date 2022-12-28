package Shopee.main.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Shopee.main.Service.iStatsService;
import Shopee.main.repository.statsRepository;


@Service
public class statsServiceImpl implements iStatsService {
	@Autowired
	statsRepository statsRepository;

	@Override
	public List<Object[]> countProductBycategory() {
		// TODO Auto-generated method stub
		return statsRepository.countProductBycategory();
	}

	@Override
	public List<Object[]> statictisProduct(String productName, String fromDate, String toDate) {
		// TODO Auto-generated method stub
		if(productName!=null && fromDate!=null && toDate!=null)
		{
			return statsRepository.statictisProduct(productName, fromDate, toDate);
		}
		return null;
	}

	@Override
	public List<Object[]> statictisProductByMonth(String fromDate, String toDate) {
		// TODO Auto-generated method stub
		
		return statsRepository.statictisProductByMonth(fromDate, toDate);
		
	}

	@Override
	public long countRevenue() {
		// TODO Auto-generated method stub
		return statsRepository.countRevenue();
	}

	@Override
	public List<Object[]> countProductByShop() {
		// TODO Auto-generated method stub
		return statsRepository.countProductByShop();
	}

	
	
	
	
	
	
	
	
}
