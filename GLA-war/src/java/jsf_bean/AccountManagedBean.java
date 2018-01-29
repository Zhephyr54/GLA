/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf_bean;

import db.dao.BiddingDAO;
import db.dao.ItemDAO;
import db.dao.OrderDAO;
import db.dao.UserDAO;
import entity.Bidding;
import entity.Item;
import entity.Order;
import entity.User;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import static servlets.SignIn.ATT_SESSION_USER;

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
    OrderDAO orderDAO;
    
    @EJB
    UserDAO userDAO;
    
    private boolean pair = false;
    
    private final int MAX_CANCELLED_BIDS = User.MAX_CANCELLED_BIDS;
    
    /**
     * Creates a new instance of AccountManagedBean
     */
    public AccountManagedBean() {
    }

    public int getMAX_CANCELLED_BIDS() {
        return MAX_CANCELLED_BIDS;
    }

    public void removeUserItem(long itemId) {
        // remove all biddings for this item
        for (Bidding bidding : biddingDAO.getItemBiddings(itemId)) {
            biddingDAO.removeById(bidding.getId());
        }
        itemDAO.removeById(itemId);
    }

    public boolean canRestart(Item i) {
        return i.getBiddings().isEmpty() && i.getEndBidDate().isBefore(LocalDateTime.now())
                && i.getOrder() == null;
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

    public List<Bidding> getUserBiddings(long userId) {
        return biddingDAO.getUserBiddings(userId);
    }

    public void removeUserBidding(Bidding bidding) {
        // if the user won this item biddings
        if (winner(bidding)) {
            // increment user cancelled bids counter
            User user = bidding.getUser();
            user.setCancelledBids(bidding.getUser().getCancelledBids()+1);
            userDAO.edit(user);
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            session.setAttribute(ATT_SESSION_USER, user);
        }
        biddingDAO.removeById(bidding.getId());
    }
    
    public boolean getPair(){
        this.pair = !this.pair;
        return this.pair;
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
    
    public boolean isOrderWaitingPayment(Order order) {
        return Order.OrderState.WAITING_PAYMENT.equals(order.getOrderState());
    }
    
    public boolean isOrderInProcess(Order order) {
        return Order.OrderState.IN_PROCESS.equals(order.getOrderState());
    }
   
}
