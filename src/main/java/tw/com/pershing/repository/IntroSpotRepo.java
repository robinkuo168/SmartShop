package tw.com.pershing.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import tw.com.pershing.domain.IntroSpot;

@Repository
public class IntroSpotRepo {

	@PersistenceUnit
	private EntityManagerFactory emf;

	public List<IntroSpot> getIntroSpot() {
		TypedQuery<IntroSpot> query = emf.createEntityManager()
				.createQuery("SELECT u FROM IntroSpot u", IntroSpot.class);
		List<IntroSpot> list = query.getResultList();
		System.out.println("getIntroYoutube length:" + list.size());
		return list;
	}

	public List<IntroSpot> findIntroSpotById(Integer introSpotId) {
		TypedQuery<IntroSpot> query = emf.createEntityManager()
				.createQuery("SELECT u FROM IntroSpot u where id=:introSpotId", IntroSpot.class)
				.setParameter("introSpotId", introSpotId);
		List<IntroSpot> list = query.getResultList();
		System.out.println("findIntroSpotById length:" + list.size());
		return list;
	}
		
	public IntroSpot saveIntroSpot(IntroSpot introSpot) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
//		if (!em.contains(position)) {
//			em.persist(position);
//		} else {
			em.merge(introSpot);
//		}
		et.commit();
		em.close();
		return introSpot;
	}
	
	public IntroSpot deleteIntroSpot(IntroSpot introSpot) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.createQuery("DELETE FROM IntroSpot u where introSpotId=:id")
				.setParameter("id", introSpot.getIntroSpotId()).executeUpdate();
		et.commit();
		em.close();
		return introSpot;
	}

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("helloworld");
		IntroSpotRepo cm = new IntroSpotRepo();
		cm.emf = emf;

		cm.findIntroSpotById(1);
		cm.getIntroSpot();
	}
}
