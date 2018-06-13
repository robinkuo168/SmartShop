package tw.com.pershing.repository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import tw.com.pershing.domain.Product;

@Repository
public class ProductRepo {
	@PersistenceUnit
	private EntityManagerFactory emf;
	
	public Product findById(Integer id){
		TypedQuery<Product> query = emf.createEntityManager()
				.createQuery("SELECT p FROM Product p where productId=:id", Product.class)
				.setParameter("id", id);
		return query.getResultList().get(0);
	}
}
