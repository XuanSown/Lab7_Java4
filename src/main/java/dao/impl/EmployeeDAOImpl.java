package dao.impl;

import java.util.List;

import dao.EmployeeDAO;
import entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import util.XJPA;

public class EmployeeDAOImpl implements EmployeeDAO {

	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		EntityManager em = XJPA.getEntityManager();
		try {
			String jpql = "select e from Employee e";
			TypedQuery<Employee> query = em.createQuery(jpql, Employee.class);
			return query.getResultList();
		} finally {
			// TODO: handle finally clause
			em.close();
		}
	}

	@Override
	public Employee findById(String id) {
		// TODO Auto-generated method stub
		EntityManager em = XJPA.getEntityManager();
		try {
			return em.find(Employee.class, id);
		} finally {
			// TODO: handle finally clause
			em.close();
		}
	}

	@Override
	public void create(Employee employee) {
		// TODO Auto-generated method stub
		EntityManager em = XJPA.getEntityManager();
		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();
			em.persist(employee);
			trans.commit();
		} catch (Exception e) {
			// TODO: handle exception
			trans.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}

	}

	@Override
	public void update(Employee employee) {
		// TODO Auto-generated method stub
		EntityManager em = XJPA.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.merge(employee);
			trans.commit();
		} catch (Exception e) {
			// TODO: handle exception
			trans.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		EntityManager em = XJPA.getEntityManager();
		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();
			Employee employee = em.find(Employee.class, id);
			if (employee != null) {
				em.remove(employee);
			}
			trans.commit();
		} catch (Exception e) {
			// TODO: handle exception
			trans.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

}
