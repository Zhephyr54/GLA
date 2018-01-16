/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entity.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author yasar
 */
@Stateless
public class GlaBean implements GlaBeanLocal {

@PersistenceContext(unitName = "glaPU")
private EntityManager em;

@Override
public User addUser(User p){
   em.persist(p);
   return p;
}

}
