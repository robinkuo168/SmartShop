package tw.com.pershing.repository;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import tw.com.pershing.domain.Customer;
import tw.com.pershing.domain.VerificationToken;

@Repository
public class VerificationTokenRepository {
	@PersistenceUnit
	private EntityManagerFactory emf;
	
	public VerificationToken findByToken(String token){
		TypedQuery<VerificationToken> query = emf.createEntityManager()
				.createQuery("SELECT u FROM VerificationToken u where token=:token", VerificationToken.class)
				.setParameter("token", token);
		return query.getResultList().get(0);
	}
	
	public VerificationToken findByCustomer(Customer customer){
		TypedQuery<VerificationToken> query = emf.createEntityManager()
				.createQuery("SELECT u FROM VerificationToken u where u.customer.customer_id=:id", VerificationToken.class)
				.setParameter("id", customer.getCustomer_id());
		return query.getResultList().get(0);
	}
	
	public VerificationToken save(VerificationToken token) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		if (!em.contains(token)) {
			em.persist(token);
		} else {
			em.merge(token);
		}
		et.commit();
		em.close();
		return token;
	}
	
	public void deleteAllExpiredSince(Date now) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.createQuery("DELETE FROM VerificationToken t where t.expiryDate <= :date")
				.setParameter("date", now).executeUpdate();
		et.commit();
		em.close();
	}
}

