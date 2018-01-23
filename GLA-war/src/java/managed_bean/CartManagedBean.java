/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed_bean;

import db.dao.BiddingDAO;
import db.dao.ItemDAO;
import db.dao.OfferDAO;
import db.dao.OrderDAO;
import entity.Address;
import entity.CreditCard;
import entity.Item;
import entity.Offer;
import entity.Order;
import entity.Subcategory;
import entity.User;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author alexis
 */
@Named(value = "cartBean")
@SessionScoped
public class CartManagedBean implements Serializable {
    
    @EJB
    ItemDAO itemDAO;
    
    @EJB
    BiddingDAO biddingDAO;
    
    @EJB
    OfferDAO offerDAO;
    
    @EJB
    OrderDAO orderDAO;
    
    private List<Item> listItems = new ArrayList<>();
    
    public List<Item> getListItems() {
        return listItems;
    }
    
    public BigDecimal calculateTotalPrice() {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Item item : listItems) {
            totalPrice = totalPrice.add(calculatePriceWithOffer(item));
        }
        return totalPrice;
    }

    public void setListItems(List<Item> listItems) {
        this.listItems = listItems;
    }
    
    public int countItem() {
        return listItems.size();
    }
    
    public void addItem(Item item) {
        // if the user won this item biddings
        if (winner(item)) {
            listItems.add(item);
        }
    }
    
    public void removeItem(Item item) {
        listItems.remove(item);
    }
    
    public boolean isInCart(Item item) {
        return listItems.contains(item);
    }
    
    private BigDecimal percentage(BigDecimal base, BigDecimal pct){
        return base.multiply(pct).divide(new BigDecimal(100), 2, RoundingMode.CEILING);
    }
    
    public int getItemReduction(Item item) {
        if (isItemInReduction(item)) {
            return Offer.PERCENT_REDUCTION;
        }
        return 0;
    }
    
    public BigDecimal calculatePriceWithOffer(Item item) {
        BigDecimal currentPrice = itemDAO.getCurrentPrice(item.getId());
        // if item isn't concerned by the current offers
        if (!isItemInReduction(item)) {
            return currentPrice;
        }
        return percentage(currentPrice, new BigDecimal(100 - getItemReduction(item)));
    }
        
    public boolean isItemInReduction(Item item) {
        return !offerDAO.findOfferBySubcategory(item.getSubcategory().getId()).isEmpty();
    }
    
    public boolean winner(Item item) {
        User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessionUtilisateur");
        // if bidding is over and the user won this item biddings
        return item.getEndBidDate().isBefore(LocalDateTime.now()) && user != null
                && itemDAO.getCurrentMaxBid(item.getId()).getUser().getId().equals(user.getId());
    }
    
    public void validateCommand(Address address, CreditCard creditCard) {
        User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessionUtilisateur");
        if (user == null)
            return;
        
        Order order = new Order(user, address, creditCard, listItems, calculateTotalPrice());
        orderDAO.create(order);
    }
}
