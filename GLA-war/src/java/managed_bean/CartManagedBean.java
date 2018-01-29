/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed_bean;

import db.dao.AddressDAO;
import db.dao.BiddingDAO;
import db.dao.CreditCardDAO;
import db.dao.ItemDAO;
import db.dao.OfferDAO;
import db.dao.OrderDAO;
import entity.Address;
import entity.CreditCard;
import entity.Item;
import entity.Offer;
import entity.Order;
import entity.User;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.Destination;
import javax.jms.JMSContext;

/**
 *
 * @author alexis
 */
@Named(value = "cartBean")
@SessionScoped
public class CartManagedBean implements Serializable {
    
    @Inject
    private JMSContext context;

    @Resource(lookup = "jms/glaRequest")
    Destination orderQueue;

    private String address;
    private Long cvv;
    private Long number;
    private String name;
    private Long addressId;
    private Long address2Id;
    private Long cbID;

    @EJB
    ItemDAO itemDAO;

    @EJB
    BiddingDAO biddingDAO;

    @EJB
    OfferDAO offerDAO;

    @EJB
    OrderDAO orderDAO;

    @EJB
    AddressDAO adrDAO;

    @EJB
    CreditCardDAO cbDAO;

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

    private BigDecimal percentage(BigDecimal base, BigDecimal pct) {
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

    public String validateCommand() {
        User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessionUtilisateur");
        Address a = adrDAO.findById(addressId);
        Address a2 = adrDAO.findById(address2Id);
        CreditCard cb = cbDAO.findById(cbID);
        Order order = new Order(user, a, a2, cb, listItems, calculateTotalPrice());
        orderDAO.edit(order);
        listItems.clear();
        this.sendGlaRequest(order);
        return "account.xhtml";
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String Address) {
        this.address = Address;
    }

    public Long getCvv() {
        return cvv;
    }

    public void setCvv(Long cvv) {
        this.cvv = cvv;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addAdr() {
        Address a = new Address(address);
        User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessionUtilisateur");
        a.setUser(user);
        adrDAO.create(a);
        address = null;
    }

    public void addCB() {
        CreditCard cb = new CreditCard(number, cvv, name);
        User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessionUtilisateur");
        cb.setUser(user);
        cbDAO.create(cb);
        number = null;
        cvv = null;
        name = null;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Long getCbID() {
        return cbID;
    }

    public void setCbID(Long cbID) {
        this.cbID = cbID;
    }

    public List<Address> getAddress(long userId) {
        return adrDAO.getUserAddress(userId);
    }

    public List<CreditCard> getCB(long userId) {
        return cbDAO.getUserCB(userId);
    }

    public Long getAddress2Id() {
        return address2Id;
    }

    public void setAddress2Id(Long address2Id) {
        this.address2Id = address2Id;
    }

    @Asynchronous
    private void sendGlaRequest(Order order) {
        System.out.println("+++++++++++++++++++++");
        System.out.println(order.getUser().getFirstname());
        context.createProducer().send(orderQueue, order);
    }
}
