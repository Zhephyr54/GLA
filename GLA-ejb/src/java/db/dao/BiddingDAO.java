/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dao;

import entity.Bidding;
import entity.Item;
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
public class BiddingDAO extends AbstractDAO<Bidding> {
    
    @PersistenceContext(unitName = "glaPU")
    private EntityManager em;

    public BiddingDAO() {
        super(Bidding.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    } 
    
    public List<Bidding> getUserBiddings(Long userId) {
        TypedQuery<Bidding> query = getEntityManager().createNamedQuery("Bidding.getUserBiddings", Bidding.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

}
