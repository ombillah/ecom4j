package com.ombillah.ecom4j.dao.hibernate;


import org.springframework.stereotype.Repository;

import com.ombillah.ecom4j.dao.CustomerDAO;
import com.ombillah.ecom4j.domain.Customer;

/**
 * DAO for Customer Related queries.
 * extends common CRUD operations from BaseDAOHibernate
 * @author Oussama M Billah
 *
 */
@Repository
public class CustomerDAOHibernate extends BaseDAOHibernate<Customer> implements CustomerDAO {

}
