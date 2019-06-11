package tw.com.pershing.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import tw.com.pershing.domain.IntroYoutube;

@Repository
public class IntroYoutubeRepo {

	@PersistenceUnit
	private EntityManagerFactory emf;

	public List<IntroYoutube> getIntroYoutube() {
		TypedQuery<IntroYoutube> query = emf.createEntityManager()
				.createQuery("SELECT u FROM IntroYoutube u", IntroYoutube.class);
		List<IntroYoutube> list = query.getResultList();
		System.out.println("getIntroYoutube length:" + list.size());
		return list;
	}

	public List<IntroYoutube> findIntroYoutubeById(Integer introId) {
		TypedQuery<IntroYoutube> query = emf.createEntityManager()
				.createQuery("SELECT u FROM IntroYoutube u where id=:introId", IntroYoutube.class)
				.setParameter("introId", introId);
		List<IntroYoutube> list = query.getResultList();
		System.out.println("findIntroYoutubeById length:" + list.size());
		return list;
	}
		
	public IntroYoutube saveIntroYoutube(IntroYoutube introYoutube) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
//		if (!em.contains(position)) {
//			em.persist(position);
//		} else {
			em.merge(introYoutube);
//		}
		et.commit();
		em.close();
		return introYoutube;
	}
	
	public IntroYoutube deleteIntroYoutube(IntroYoutube introYoutube) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.createQuery("DELETE FROM IntroYoutube u where introId=:id")
				.setParameter("id", introYoutube.getIntroId()).executeUpdate();
		et.commit();
		em.close();
		return introYoutube;
	}

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("helloworld");
		IntroYoutubeRepo cm = new IntroYoutubeRepo();
		cm.emf = emf;

		cm.findIntroYoutubeById(1);
		cm.getIntroYoutube();
	}
}
