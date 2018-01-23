/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dao;

import entity.CreditCard;
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
public class CreditCardDAO extends AbstractDAO<CreditCard> {
    
    @PersistenceContext(unitName = "glaPU")
    private EntityManager em;

    public CreditCardDAO() {
        super(CreditCard.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    } 

    public List<CreditCard> getUserCB(Long userId) {
        TypedQuery<CreditCard> query = getEntityManager().createNamedQuery("CreditCard.getUserCB", CreditCard.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    } 

}
