package tw.com.pershing.repository;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import tw.com.pershing.domain.Supply;

@Repository
public class SupplyRepo {
	@PersistenceUnit
	private EntityManagerFactory emf;
	
	public List<Supply> findSupplyById(String supplyId) {
		TypedQuery<Supply> query = emf.createEntityManager()
				.createQuery("SELECT u FROM Supply u where id=:supplyId", Supply.class)
				.setParameter("supplyId", supplyId);
		List<Supply> supplyList = query.getResultList();
		System.out.println("findSupplyById length:" + supplyList.size());
		return supplyList;
	}
}
