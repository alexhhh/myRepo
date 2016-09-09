package com.ikon.alexx;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ikon.alexx.entity.Mester;
import com.ikon.alexx.model.MesterSearchCriteria;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MesterBootJpaApplication.class)
@WebAppConfiguration
public class MesterBootJpaApplicationTests {

	@Autowired
	EntityManager em;

	@Test
	public void testSimpleQuery() {

		TypedQuery<Mester> query = em.createQuery(createQuery(), Mester.class); // .setFirstResult(10).setMaxResults(10);
		List<Mester> mesterList = query.getResultList();

		Pageable pageable = createPageRequest();
		Long total = (long) 12;
		PageImpl<Mester> page = new PageImpl<>(mesterList, pageable, total);

		assertNotNull(mesterList);

		System.out.println(mesterList.size());

		for (Mester mester : mesterList) {
			System.out.println(mester.getId());
		}
		System.out.println(page);
	}

	private Pageable createPageRequest() {
		MesterSearchCriteria searchCriteria = new MesterSearchCriteria();
		searchCriteria.setPageSize(10);
		searchCriteria.setPageNumber(1);
		return new PageRequest(searchCriteria.getPageNumber(), searchCriteria.getPageSize());
	}

	private String createQuery() {
		return "SELECT m FROM Mester m join m.user u WHERE u.isEnable=1";
	}

}
