package tw.com.pershing.repository;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import tw.com.pershing.domain.Parameter;
import tw.com.pershing.domain.Supply;

@Repository
public class ParameterRepo {
	@PersistenceUnit
	private EntityManagerFactory emf;
	
	public List<Parameter> findParameterByKind(String kind) {
		TypedQuery<Parameter> query = emf.createEntityManager()
				.createQuery("SELECT p FROM Parameter p where kind=:kind order by p.code", Parameter.class)
				.setParameter("kind", kind);
		List<Parameter> parameterList = query.getResultList();
		System.out.println("findParameterByKind length:" + parameterList.size());
		return parameterList;
	}
}
