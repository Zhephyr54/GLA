/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dao;

import entity.Address;
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
public class AddressDAO extends AbstractDAO<Address> {
    
    @PersistenceContext(unitName = "glaPU")
    private EntityManager em;

    public AddressDAO() {
        super(Address.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    } 
    
    public List<Address> getUserAddress(Long userId) {
        TypedQuery<Address> query = getEntityManager().createNamedQuery("Address.getUserAddress", Address.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }  

}
