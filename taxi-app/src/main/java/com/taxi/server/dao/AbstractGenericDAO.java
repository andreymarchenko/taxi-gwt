package com.taxi.server.dao;

import com.taxi.server.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AbstractGenericDAO<T, PK extends Serializable> {
    private Class<T> type;

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private final EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();

    public AbstractGenericDAO() {
        Type type = getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) type;
        this.type = (Class<T>) parameterizedType.getActualTypeArguments()[0];
    }

    public void save(T object) {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            em.persist(object);

            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
    }

    public T find(PK id) {
        T object = null;
        try (Session session = getCurrentSession()) {
            Transaction transaction = session.getTransaction();
            try {
                transaction.begin();
                object = session.get(this.type, id);
                transaction.commit();
            } catch (HibernateException ex) {
                ex.printStackTrace();
                transaction.rollback();
            }
        }
        return object;
    }

    public void update(T object) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(object);
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    public void delete(T object) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.delete(object);
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    public List<T> getAll() {
        Session currentSession = getCurrentSession();
        Transaction tr = currentSession.getTransaction();
        List<T> resultList = null;
        try {
            tr.begin();

            Query query = currentSession.createQuery("from " + type.getName());
            resultList = query.getResultList();

            tr.commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            tr.rollback();
        } finally {
            currentSession.close();
        }
        return resultList;
    }

    public void saveOrUpdate(T object) {
        try (final Session currentSession = getCurrentSession()) {
            currentSession.saveOrUpdate(object);
        }
    }

    public List<T> filterAndSort(Map<String, Object> filterMap, String sort) {
        List<T> result = null;
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(type);
            Root<T> root = criteriaQuery.from(type);
            List<Predicate> predicates = new ArrayList<>();
            for (String attr : filterMap.keySet()) {
                if (filterMap.get(attr) != null) {
                    Predicate p = criteriaBuilder.equal(root.get(attr), filterMap.get(attr));
                    predicates.add(p);
                }
            }
            criteriaQuery.where(criteriaBuilder.and(predicates.stream().toArray(Predicate[]::new)));

            if (sort != null && !"".equals(sort)) {
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get(sort)));
            }

            TypedQuery<T> typedQuery = entityManager.createQuery(criteriaQuery);
            result = typedQuery.getResultList();

            transaction.commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            transaction.rollback();
        } finally {
            entityManager.close();
        }

        return result;
    }

    protected Session getCurrentSession() {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException he) {
            System.out.println("getCurrentSession error");
            System.out.println("open new session");
            session = sessionFactory.openSession();
        }
        return session;
    }

    protected EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
