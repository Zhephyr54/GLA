/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dao;

import entity.Bidding;
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

}
