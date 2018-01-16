/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dao;

import entity.Offer;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author alexis
 */
@Stateless
@LocalBean
public class OfferDAO extends AbstractDAO<Offer> {
    
    @PersistenceContext(unitName = "glaPU")
    private EntityManager em;

    public OfferDAO() {
        super(Offer.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    } 

}
