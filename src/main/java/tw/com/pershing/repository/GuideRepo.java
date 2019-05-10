package tw.com.pershing.repository;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import tw.com.pershing.domain.Guide;

@Repository
public class GuideRepo {

	@PersistenceUnit
	private EntityManagerFactory emf;

	public List<Guide> getGuide() {
		TypedQuery<Guide> query = emf.createEntityManager()
				.createQuery("SELECT u FROM Guide u", Guide.class);
		List<Guide> list = query.getResultList();
		System.out.println("getGuide length:" + list.size());
		return list;
	}

	public List<Guide> findGuideById(Integer guideId) {
		TypedQuery<Guide> query = emf.createEntityManager()
				.createQuery("SELECT u FROM Guide u where id=:guideId", Guide.class)
				.setParameter("guideId", guideId);
		List<Guide> list = query.getResultList();
		System.out.println("findGuideById length:" + list.size());
		return list;
	}

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("helloworld");
		GuideRepo cm = new GuideRepo();
		cm.emf = emf;

		cm.findGuideById(1);
		cm.getGuide();
	}
}
