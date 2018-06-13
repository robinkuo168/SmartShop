package tw.com.pershing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.pershing.domain.Product;
import tw.com.pershing.repository.ProductRepo;

@Service
public class ProductService {
	
	@Autowired
	ProductRepo repository;
	
	public Product getProductById(String id){
		final Product product = repository.findById(Integer.valueOf(id));
		if (product != null) {
			return product;
		}
		return null;
	}
}
