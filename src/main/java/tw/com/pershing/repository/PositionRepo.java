package tw.com.pershing.repository;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import tw.com.pershing.domain.Position;

@Repository
public class PositionRepo {

	@PersistenceUnit
	private EntityManagerFactory emf;

	public List<Position> getPosition() {
		TypedQuery<Position> query = emf.createEntityManager()
				.createQuery("SELECT u FROM Position u", Position.class);
		List<Position> list = query.getResultList();
		System.out.println("getPosition length:" + list.size());
		return list;
	}

	public List<Position> findPositionById(Integer positionId) {
		TypedQuery<Position> query = emf.createEntityManager()
				.createQuery("SELECT u FROM Position u where id=:positionId", Position.class)
				.setParameter("positionId", positionId);
		List<Position> list = query.getResultList();
		System.out.println("findPositionById length:" + list.size());
		return list;
	}

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("helloworld");
		PositionRepo cm = new PositionRepo();
		cm.emf = emf;

		cm.findPositionById(1);
		cm.getPosition();
	}
}
