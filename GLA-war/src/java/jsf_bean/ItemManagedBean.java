/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf_bean;

import db.dao.BiddingDAO;
import db.dao.ItemDAO;
import entity.Bidding;
import entity.Item;
import entity.User;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
//import javax.validation.constraints.Digits;

/**
 *
 * @author alexis
 */
@Named(value = "itemBean")
@ViewScoped
public class ItemManagedBean implements Serializable {

    @EJB
    ItemDAO itemDAO;
    
    @EJB
    BiddingDAO biddingDAO;
    
    private long itemId;
    
    private Item item;
        
    //@Digits(integer = 7, fraction = 2, message = "Prix invalide. Exemple : 12.55")
    private BigDecimal bid;
    
    /**
     * Creates a new instance of ItemManagedBean
     */
    public ItemManagedBean() {
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public Item getItem() {
        return item;
    }

    public BigDecimal getBid() {
        return bid;
    }

    public void setBid(BigDecimal bid) {
        this.bid = bid;
    }
    
    public void onload() {
        this.item = itemDAO.findById(itemId);
    }
    
    /**
     * Form submit for outbid.
     */
    public void outbid() {
        User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessionUtilisateur");
        Bidding bidding = new Bidding(bid, user, item);
        biddingDAO.create(bidding);
        itemDAO.edit(item);
    }
    
}
