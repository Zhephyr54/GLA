/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dao;

import entity.Offer;
import entity.Subcategory;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author alexis
 */
@Stateless
@LocalBean
public class OfferDAO extends AbstractDAO<Offer> {
    
    @PersistenceContext(unitName = "glaPU")
    private EntityManager em;
    
    @EJB
    SubcategoryDAO s;

    public OfferDAO() {
        super(Offer.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    } 
    
     @Schedule(hour = "*", minute = "*", persistent = false)
     public void getOfferOfDay() {
         List<Subcategory> list = s.findAll();
         int tmp = ThreadLocalRandom.current().nextInt(1, list.size() + 1);
         Subcategory suboffer = list.get(tmp-1);
         System.out.println("Promo sur la categorie !" + suboffer.getTitle());
         Offer o = new Offer(LocalDateTime.now().plusDays(1),suboffer);
         this.create(o);
     }
     
     public List<Offer> getOffer(){
        TypedQuery<Offer> query = getEntityManager().createNamedQuery("Offer.findOffer", Offer.class);
        List<Offer> o = query.setParameter("currentDate", LocalDateTime.now()).getResultList();
        return o;
     }

}
