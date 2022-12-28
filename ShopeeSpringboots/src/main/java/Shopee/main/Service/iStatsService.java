package Shopee.main.Service;

import java.util.List;

public interface iStatsService {
	public List<Object[]> countProductBycategory();
	public List<Object[]> statictisProduct(String productName,String fromDate,String toDate);
	public List<Object[]> statictisProductByMonth(String fromDate,String toDate);
	public long countRevenue();
	public List<Object[]> countProductByShop();
}
