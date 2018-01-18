/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dao;

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
public class ItemDAO extends AbstractDAO<Item> {
    
    @PersistenceContext(unitName = "glaPU")
    private EntityManager em;

    public ItemDAO() {
        super(Item.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    } 

    public List<Item> findAll() {
        return executeNamedQuery("Item.findAll");
    }
    
    /**
     * Return the number of biggings for this item using
     * a count query.
     * @param itemId the item id
     * @return The number of biddings for this item
     */
    public Long getNumberOfBiddingsById(Long itemId) {
        TypedQuery<Long> query = getEntityManager().createNamedQuery("Item.getNumberOfBiddings", Long.class);
        query.setParameter("itemId", itemId);
        return query.getSingleResult();
    }
    
}
