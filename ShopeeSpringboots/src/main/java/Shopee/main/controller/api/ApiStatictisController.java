package Shopee.main.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import Shopee.main.Service.iStatsService;
@CrossOrigin
@RestController
public class ApiStatictisController {
	@Autowired
	iStatsService statsService;
	
	@GetMapping("/api/statictis/category")
	public ResponseEntity<List<Object[]>> listCategory()
	{
		List<Object[]> list=statsService.countProductBycategory();
		
		return new ResponseEntity<List<Object[]>>(list, HttpStatus.OK);
	}
}
