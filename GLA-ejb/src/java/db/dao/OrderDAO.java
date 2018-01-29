/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dao;

import entity.Order;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author alexis
 */
@Stateless
@LocalBean
public class OrderDAO extends AbstractDAO<Order> {
    
    @PersistenceContext(unitName = "glaPU")
    private EntityManager em;

    public OrderDAO() {
        super(Order.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    } 

    public List<Order> findOrderByUserId(Long userId) {
        TypedQuery<Order> query = getEntityManager().createNamedQuery("Order.findOrderByUserId", Order.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }
    
    public List<Order> findAll() {
        return executeNamedQuery("Order.findAll");
    }
    
}
