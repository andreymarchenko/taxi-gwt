package com.taxi.server.dao;

import com.taxi.server.model.Client;
import com.taxi.server.model.Driver;
import com.taxi.shared.dto.ClientDto;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class DriverDAO extends AbstractGenericDAO<Driver, Integer>{

    public List<Driver> getAllDrivers() {
        Session currentSession = getCurrentSession();
        Transaction tr = currentSession.getTransaction();
        List<Driver> resultList = null;
        try {
            tr.begin();
            Query query = currentSession.createNativeQuery("select * from drivers");
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

    public Driver getDriver(Integer id) {
        return find(id);
    }
}
