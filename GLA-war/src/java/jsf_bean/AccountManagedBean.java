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
import java.time.LocalDateTime;
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

    /**
     * Creates a new instance of AccountManagedBean
     */
    public AccountManagedBean() {
    }

    public void removeUserItem(long itemId) {
        itemDAO.removeById(itemId);
    }

    public List<Item> getUserItems(long userId) {
        return itemDAO.getUserItems(userId);
    }

    public boolean winner(Bidding b) {
        if (!b.getItem().getEndBidDate().isBefore(LocalDateTime.now())) {
            return false;
        }
        if(b.getPrice() != itemDAO.getCurrentMaxBid(b.getItem().getId()).getPrice())
            return false;
        Bidding bidding = biddingDAO.myMax(b.getItem().getId(), b.getUser().getId());
        if (bidding == null) {
            return false;
        }
        return itemDAO.getCurrentMaxBid(b.getItem().getId()).getPrice() == bidding.getPrice();
    }

    public List<Bidding> getUserBiddings(long userId) {
        System.out.println(biddingDAO.getUserBiddings(userId).toString());
        return biddingDAO.getUserBiddings(userId);
    }

    public void removeUserBidding(long biddingId) {
        biddingDAO.removeById(biddingId);
    }

}
