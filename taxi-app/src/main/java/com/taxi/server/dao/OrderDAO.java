package com.taxi.server.dao;

import com.taxi.server.model.Order;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class OrderDAO extends AbstractGenericDAO<Order, Integer>{
    public Order getOrder(Integer id) {
        return find(id);
    }

    public List<Order> getOrdersByClient(Integer id) {
        Session currentSession = getCurrentSession();
        Transaction tr = currentSession.getTransaction();
        List<Order> resultList = null;
        try {
            tr.begin();
            Query query = currentSession.createNativeQuery("select * from orders where client = :client_id");
            query.setParameter("client_id", id);
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

    public List<Order> getOrdersByDriver(Integer id) {
        Session currentSession = getCurrentSession();
        Transaction tr = currentSession.getTransaction();
        List<Order> resultList = null;
        try {
            tr.begin();
            Query query = currentSession.createNativeQuery("select * from orders where driver = :driver_id");
            query.setParameter("driver_id", id);
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

    public List<Order> getFreeOrders() {
        Session currentSession = getCurrentSession();
        Transaction tr = currentSession.getTransaction();
        List<Order> resultList = null;
        try {
            tr.begin();
            Query query = currentSession.createNativeQuery("select * from orders where status = 'FREE'");
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
}
