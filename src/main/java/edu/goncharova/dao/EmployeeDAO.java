package edu.goncharova.dao;

import edu.goncharova.connection.HibernateSessionFactoryUtil;
import edu.goncharova.entities.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import java.util.List;

import static edu.goncharova.connection.HibernateSessionFactoryUtil.getSessionFactory;

public class EmployeeDAO implements DAO<Employee, Integer> {

    private static final Logger LOG = LogManager.getLogger(EmployeeDAO.class);

    @Override
    public Employee saveOrUpdate(Employee employee) {
        Transaction transaction = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(employee);
            session.getTransaction().commit();
        } catch (Exception e) {
            LOG.info(" Exception. Rolling back transaction." + e);
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return null;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> result;
        final String query = "from Employee";
        try (final Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            final Query sessionQuery = session.createQuery(query);
            final Transaction transaction = session.beginTransaction();

            result = sessionQuery.list();
            transaction.commit();
        }
        return result;
    }

    public List<Employee> findAll(Integer departmentId) {
        List<Employee> result;
        final String query = "from Employee where id = ?";
        try (final Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            final Query sessionQuery = session.createQuery(query);
            sessionQuery.setInteger(0, departmentId);
            final Transaction transaction = session.beginTransaction();

            result = sessionQuery.list();
            transaction.commit();
        }
        return result;
    }

    @Override
    public Employee find(Integer employeeId) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().load(Employee.class, employeeId);
    }

    @Override
    public void delete(Employee employee) {
        Transaction transaction = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(employee);
            transaction.commit();
        } catch (Exception e) {
            LOG.info("\n Exception. Rolling back transaction.\n", e);
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Employee getByField(String employeeEmail) {
        Employee employee;
        try (Session session = getSessionFactory().openSession()) {
            Query query = session.createQuery("from Employee e where e.employeeEmail=:employee_email")
                    .setParameter("employee_email", employeeEmail);
            employee = (Employee) query.uniqueResult();
        } catch (NoResultException e) {
            LOG.info("\n Exception. Rolling back transaction.\n", e);
            employee = null;
        }
        return employee;
    }

    public Employee getByNumber(String phoneNumber) {
        Employee employee;
        try (Session session = getSessionFactory().openSession()) {
            Query query = session.createQuery("from Employee e where e.employeePhoneNumber=:employee_phone_number")
                    .setParameter("employee_phone_number", phoneNumber);
            employee = (Employee) query.uniqueResult();
        } catch (NoResultException e) {
            LOG.info("\n Exception. Rolling back transaction.\n", e);
            employee = null;
        }
        return employee;
    }
}