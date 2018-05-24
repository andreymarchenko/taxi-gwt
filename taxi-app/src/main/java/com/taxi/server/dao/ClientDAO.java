package com.taxi.server.dao;

import com.taxi.server.model.Client;
import com.taxi.shared.dto.ClientDto;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ClientDAO extends AbstractGenericDAO<Client, Integer>{

    public List<Client> getAllClients() {
        Session currentSession = getCurrentSession();
        Transaction tr = currentSession.getTransaction();
        List<Client> resultList = null;
        try {
            tr.begin();
            Query query = currentSession.createNativeQuery("select * from clients");
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

    public Client getClient(Integer id) {
        return find(id);
    }
}
