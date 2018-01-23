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
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author achyle
 */
@Named(value = "accountBean")
@RequestScoped
public class AccountManagedBean {
    
    @EJB
    ItemDAO itemDAO;
    
    @EJB
    BiddingDAO biddingDAO; 
    
    private boolean pair = false;

    /**
     * Creates a new instance of AccountManagedBean
     */
    public AccountManagedBean() {
    }
    
    public void removeUserItemS(long itemId) {
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        //itemDAO.removeById(itemId);
    }
    
    public List<Item> getUserItems(long userId) {
        System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT");
        return itemDAO.getUserItems(userId);
    }
    
    public List<Bidding> getUserBiddings(long userId) {
        return biddingDAO.getUserBiddings(userId);
    }
    
    public void removeUserBidding(long biddingId) {
        biddingDAO.removeById(biddingId);
    }
    
    public boolean getPair(){
        this.pair = !this.pair;
        return this.pair;
    }

}
