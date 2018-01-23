/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf_bean;

import db.dao.AddressDAO;
import db.dao.BiddingDAO;
import db.dao.ItemDAO;
import db.dao.OrderDAO;
import entity.Address;
import entity.Bidding;
import entity.Item;
import entity.Order;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    
    @EJB
    AddressDAO addressDAO; 
    
    @EJB
    OrderDAO orderDAO;
    
    private boolean pair = false;

    /**
     * Creates a new instance of AccountManagedBean
     */
    public AccountManagedBean() {
    }

    public void removeUserItem(long itemId) {
        // remove all biddings for this item
        for (Bidding bidding : biddingDAO.getItemBiddings(itemId)) {
            biddingDAO.removeById(bidding.getId());
        }
        itemDAO.removeById(itemId);
    }

    public List<Item> getUserItems(long userId) {
        return itemDAO.getUserItems(userId);
    }

    public boolean winner(Bidding b) {
        if (!b.getItem().getEndBidDate().isBefore(LocalDateTime.now())) {
            return false;
        }
     return b.getPrice() == itemDAO.getCurrentMaxBid(b.getItem().getId()).getPrice();
    }

    public List<Address> getAddress(long userId){
        return addressDAO.getUserAddress(userId);
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

    public boolean isItemOrdered(Item item) {
        return false;   
        //return !orderDAO.findOrderByItemId(item.getId()).isEmpty();
    }
    
    public List<Order> getUserOrders(long userId) {
        return orderDAO.findOrderByUserId(userId);
    }
    
    public String formatOrderDate(Order order, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return order.getOrderDate().format(formatter);
    }
    
    public boolean isOrderSent(Order order) {
        return Order.OrderState.SENT.equals(order.getOrderState());
    }
    
    public boolean isOrderInProcess(Order order) {
        return Order.OrderState.IN_PROCESS.equals(order.getOrderState());
    }
}
