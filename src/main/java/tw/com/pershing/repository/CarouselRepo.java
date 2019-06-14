package tw.com.pershing.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import tw.com.pershing.domain.Carousel;
import tw.com.pershing.domain.Position;

@Repository
public class CarouselRepo {

	@PersistenceUnit
	private EntityManagerFactory emf;

	public List<Carousel> getCarousel() {
		TypedQuery<Carousel> query = emf.createEntityManager()
				.createQuery("SELECT u FROM Carousel u order by u.seq asc ", Carousel.class);
		List<Carousel> list = query.getResultList();
		System.out.println("getCarousel length:" + list.size());
		return list;
	}

	public List<Carousel> findCarouselById(Integer carouselId) {
		TypedQuery<Carousel> query = emf.createEntityManager()
				.createQuery("SELECT u FROM Carousel u where id=:carouselId", Carousel.class)
				.setParameter("carouselId", carouselId);
		List<Carousel> list = query.getResultList();
		System.out.println("findCarouselById length:" + list.size());
		return list;
	}
		
	public Carousel saveCarousel(Carousel carousel) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
	//	if (!em.contains(position)) {
	//		em.persist(position);
	//	} else {
			em.merge(carousel);
	//	}
		et.commit();
		em.close();
		return carousel;
	}
	
	public Carousel deleteCarousel(Carousel carousel) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.createQuery("DELETE FROM Carousel u where carouselId=:id")
				.setParameter("id", carousel.getCarouselId()).executeUpdate();
		et.commit();
		em.close();
		return carousel;
	}

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("helloworld");
		CarouselRepo cm = new CarouselRepo();
		cm.emf = emf;

		cm.findCarouselById(1);
		cm.getCarousel();
	}
}
