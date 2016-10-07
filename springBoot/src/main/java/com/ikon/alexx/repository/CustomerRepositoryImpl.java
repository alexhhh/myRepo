package com.ikon.alexx.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ikon.alexx.model.Customer;

// numele interfetei (finale sau mari) + Impl
public class CustomerRepositoryImpl implements MyCustomerRepository {

	@Autowired
	EntityManager em;

	private static final Logger log = LoggerFactory.getLogger(CustomerRepositoryImpl.class);

	@Override
	@Transactional(readOnly = true)
	public List<Customer> myFindByLastName(String lastName) {

		log.info(em.toString());
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Customer> query = cb.createQuery(Customer.class);
		Root<Customer> x = query.from(Customer.class);
		query.where(cb.equal(x.get("lastName"), lastName));
		return em.createQuery(query).getResultList();

	}

	@Override
	@Transactional(readOnly = true)
	public List<Customer> myFindByFirstName(String firstName) {
		TypedQuery<Customer> query = em.createQuery("Select c from Customer c where c.firstName= :firstName",
				Customer.class);
		query.setParameter("firstName", firstName);
		return query.getResultList();
	}

	@Override
	public Customer myFindCustomerByid(long id) {
		TypedQuery<Customer> query = em.createNamedQuery(Customer.BYID, Customer.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}

}
