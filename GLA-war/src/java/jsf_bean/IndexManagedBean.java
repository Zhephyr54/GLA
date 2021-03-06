package jsf_bean;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import db.dao.ItemDAO;
import db.dao.OfferDAO;
import entity.Item;
import entity.Offer;
import entity.Order;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author alexis
 */
@Named(value = "indexBean")
@RequestScoped
public class IndexManagedBean {

    @EJB
    ItemDAO itemDAO;
    
    @EJB
    OfferDAO offer;   
        
    /**
     * Creates a new instance of IndexManagedBean
     */
    public IndexManagedBean() {
    }
    
    public List<Item> getItems() {
        return itemDAO.findAllNotOver();
    }    
        
    public List<Offer> getOfferOfDay(){
        return offer.getOffer();
    } 
    
    public String formatOrderDate(Offer offer, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return offer.getEndOfferDate().format(formatter);
    }
}
