package com.taxi.server.dao;

import com.taxi.server.model.Operator;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class OperatorDAO  extends AbstractGenericDAO<Operator, Integer> {
    public List<Operator> getAllOperators() {
        Session currentSession = getCurrentSession();
        Transaction tr = currentSession.getTransaction();
        List<Operator> resultList = null;
        try {
            tr.begin();
            Query query = currentSession.createNativeQuery("select * from operators");
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

    public Operator getOperator(Integer id) {
        return find(id);
    }
}
