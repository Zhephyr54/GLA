/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author yasar
 */
@Entity
@Table(name = "Items")
@NamedQueries({
    @NamedQuery(
            name = "Item.findAll", 
            query = "SELECT i FROM Item i"),
    @NamedQuery(
            name = "Item.findAllNotOver",
            query = "SELECT i FROM Item i WHERE i.endBidDate > :currentDate"),
    @NamedQuery(
            name = "Item.findByTitle",
            query = "SELECT i FROM Item i WHERE LOWER(i.title) LIKE LOWER(:title) "
                    + "AND i.endBidDate > :currentDate"),
    @NamedQuery(
            name = "Item.findByTitleAndCategory",
            query = "SELECT i FROM Item i WHERE LOWER(i.title) LIKE LOWER(:title) AND i.subcategory.category.id = :categoryId "
                    + "AND i.endBidDate > :currentDate"),
    @NamedQuery(
            name = "Item.findByTitleAndSubcategory",
            query = "SELECT i FROM Item i WHERE LOWER(i.title) LIKE LOWER(:title) AND i.subcategory.id = :subcategoryId "
                    + "AND i.endBidDate > :currentDate"),
   /* @NamedQuery(
            name = "Item.getCurrentMaxBid",
            query = "SELECT b FROM Bidding b WHERE b.item.id = :itemId AND b.price = (SELECT MAX(b2.price) "
                    + "FROM Bidding b2 "
                    + "WHERE b2.id = b.id)"),*/
    @NamedQuery(
            name = "Item.getCurrentMaxBid",
            query = "SELECT b FROM Bidding b WHERE b.item.id = :itemId Order BY b.price DESC"),
    @NamedQuery(
            name = "Item.getNumberOfBiddings", 
            query = "SELECT count(b) as nbBiddings FROM Bidding b JOIN b.item i WHERE i.id = :itemId"),
    @NamedQuery(
            name = "Item.getUserItemsInProgress", 
            query = "SELECT i FROM Item i WHERE i.user.id = :userId")
})
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "title")
    private String title;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "starting_bid", precision = 9, scale = 2)
    private BigDecimal startingBid;
    
    @Column(name = "end_bid_date")
    private LocalDateTime endBidDate;
            
    @ManyToOne
    private User user;
    
    @ManyToOne
    private Subcategory subcategory;
    
    @OneToMany(mappedBy="item", cascade = CascadeType.ALL)
    private List<Bidding> biddings = new ArrayList<>(); 

    @ManyToOne
    private Order order;
    
    public Item() {
    }

    public Item(String title, String description, BigDecimal startingBid, LocalDateTime endBidDate) {
        this.title = title;
        this.description = description;
        this.startingBid = startingBid;
        this.endBidDate = endBidDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getStartingBid() {
        return startingBid;
    }

    public void setStartingBid(BigDecimal startingBid) {
        this.startingBid = startingBid;
    }

    public LocalDateTime getEndBidDate() {
        return endBidDate;
    }

    public void setEndBidDate(LocalDateTime endBidDate) {
        this.endBidDate = endBidDate;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public List<Bidding> getBiddings() {
        return biddings;
    }

    public void addBidding(Bidding bidding) {
        bidding.setItem(this);
        this.biddings.add(bidding);
    }

    public Subcategory getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", title=" + title + ", description=" + description + ", startingBid=" + startingBid + ", endBidDate=" + endBidDate + ", user=" + user + ", subcategory=" + subcategory + ", biddings=" + biddings + ", order=" + order + '}';
    }

}
