package edu.goncharova.dao;

import edu.goncharova.entities.Department;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import java.util.List;

import static edu.goncharova.connection.HibernateSessionFactoryUtil.getSessionFactory;

public class DepartmentDAO implements DAO<Department, Integer> {

    private static final Logger LOG = LogManager.getLogger(DepartmentDAO.class);

    @Override
    public Department saveOrUpdate(Department department) {
        Transaction transaction = null;
        try (Session session = getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(department);
            transaction.commit();
        } catch (Exception e) {
            LOG.info("\n Exception. Rolling back transaction.\n", e);
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return null;
    }

    @Override
    public List<Department> findAll() {
        List<Department> result;
        final String query = "from Department";
        try (final Session session = getSessionFactory().openSession()) {
            final Query list = session.createQuery(query);
            final Transaction transaction = session.beginTransaction();

            result = list.list();
            transaction.commit();
        }
        return result;
    }

    @Override
    public Department find(Integer departmentId) {
        return getSessionFactory().openSession().get(Department.class, departmentId);
    }

    @Override
    public void delete(Department department) {
        Transaction transaction = null;
        try (Session session = getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(department);
            transaction.commit();
        } catch (Exception e) {
            LOG.info("\n Exception. Rolling back transaction.\n", e);
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Department getByField(String departmentName) {
        Department department;
        try (Session session = getSessionFactory().openSession()) {
            Query query = session.createQuery("from Department d where d.departmentName=:department_name")
                    .setParameter("department_name", departmentName);
            department = (Department) query.uniqueResult();
        } catch (NoResultException e) {
            LOG.info("\n Exception. Rolling back transaction.\n", e);
            department = null;
        }
        return department;
    }
}
