package tw.com.pershing.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.com.pershing.domain.Product;
import tw.com.pershing.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductService productService;

	@RequestMapping(value = "/product")
	public Product getProduct(@RequestParam(value = "id") String id){
		return productService.getProductById(id);
	}
}
