package se.kwikstrom.brisket.crm.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;

import se.kwikstrom.brisket.crm.domain.Customer;

public class CustomerRepositoryImpl implements CustomerRepositoryCustom {
	private EntityManager em;

	public CustomerRepositoryImpl() {
		System.out.println("SKAPAD!!!");
	}

	@Autowired
	public CustomerRepositoryImpl(JpaContext ctx) {
		this.em = ctx.getEntityManagerByManagedType(Customer.class);
	}

	@Override
	public List<Customer> findAllByNameLike(String name, int offset, int limit) {
		String queryStr = "SELECT c FROM Customer c WHERE upper(c.name) LIKE upper(concat('%',?1,'%'))";
		TypedQuery<Customer> query = em.createQuery(queryStr, Customer.class);
		query.setParameter(1, name).setFirstResult(offset).setMaxResults(limit);
		return query.getResultList();
	}

	@Override
	public List<Customer> findAll(int offset, int limit) {
		return em.createQuery("SELECT c FROM Customer c", Customer.class).setFirstResult(offset).setMaxResults(limit)
		    .getResultList();
	}

}
