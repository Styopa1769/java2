package Repositories;

import DataBase.HibernateManager;
import Entities.Agent;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AgentRepository {
    private HibernateManager db;

    public AgentRepository() {
        this.db = new HibernateManager();
    }

    public Agent getAgentByCode(String code){
        Session session = db.getSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        Agent agent = (Agent) session.get(Agent.class, code);
        transaction.commit();
        session.close();
        return agent;
    }
}
