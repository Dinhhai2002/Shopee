package Shopee.main;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import Shopee.main.Service.IStorageService;
import Shopee.main.config.StorageProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)

public class ShopeeSpringbootsApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ShopeeSpringbootsApplication.class, args);
	}

	@Bean
	CommandLineRunner init(IStorageService storageService) {
		return (args -> {
			storageService.init();
		});
	}

	
	
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("UTF-8");
		return resolver;
	}

	@Bean
	BCryptPasswordEncoder getBCryptPasswordEncoder() {

		return new BCryptPasswordEncoder();

	}

	@Bean
	public Cloudinary cloudinary() {
		Cloudinary c = new Cloudinary(ObjectUtils.asMap("cloud_name", "dypxafh7q", "api_key", "391462545914422",
				"api_secret", "MJN1FIDjCQOk70u9NY987L4MUrs", "secure", true));
		return c;
	}
}
