package tw.com.pershing.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import tw.com.pershing.domain.Customer;

@Repository
public class CustomerRepo {

	@PersistenceUnit
	private EntityManagerFactory emf;

	public List<Customer> findCustomerByUsername(String user) {
		TypedQuery<Customer> query = emf.createEntityManager()
				.createQuery("SELECT u FROM Customer u where username=:user", Customer.class)
				.setParameter("user", user);
		List<Customer> userList = query.getResultList();
		System.out.println("findUserByUsername length:" + userList.size());
		return userList;
	}
	
	public List<Customer> findCustomerByEmail(String email) {
		TypedQuery<Customer> query = emf.createEntityManager()
				.createQuery("SELECT u FROM Customer u where email=:email", Customer.class)
				.setParameter("email", email);
		List<Customer> customerList = query.getResultList();
		return customerList;
	}
	
	public Customer findCustomerWithPassword(Customer customer) {
		TypedQuery<Customer> query = emf.createEntityManager()
				.createQuery("SELECT u FROM Customer u where email=:email", Customer.class)
				.setParameter("email", customer.getEmail());
		Customer resultCustomer = query.getSingleResult();
		return resultCustomer;
	}

	public Customer saveUser(Customer customer) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		if (!em.contains(customer)) {
			em.persist(customer);
		} else {
			em.merge(customer);
		}
		et.commit();
		em.close();
		return customer;
	}

	public Customer deleteUser(Customer customer) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.createQuery("DELETE FROM Customer u where username=:user", Customer.class)
				.setParameter("user", customer.getUsername()).executeUpdate();
		et.commit();
		em.close();
		return customer;
	}

	public Customer getCustomerByUsername(String username) {
		return emf.createEntityManager().find(Customer.class, username);
	}

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("helloworld");
		CustomerRepo cm = new CustomerRepo();
		cm.emf = emf;

		Customer customer = new Customer();
		customer.setEmail("a@a.com");
		customer.setMobile("0912345678");
		customer.setUsername("Melvin");
		customer.setPassword("000000");
		cm.saveUser(customer);

		cm.findCustomerByUsername("Melvin");

		cm.deleteUser(customer);
	}
}
