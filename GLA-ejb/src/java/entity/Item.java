/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author yasar
 */
@Entity
@Table(name = "Items")
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "title")
    private String title;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "starting_bid")
    private double startingBid;
    
    @Column(name = "end_bid_date")
    private LocalDateTime endBidDate ;
        
    @Column(name = "current_max_bid")
    private double currentMaxBid;
    
    @ManyToOne
    private User user;
    
    @OneToMany(mappedBy="item", cascade = CascadeType.ALL)
    private List<Bidding> bidding = new ArrayList<>(); 

    public Item() {
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

    public double getStartingBid() {
        return startingBid;
    }

    public void setStartingBid(double startingBid) {
        this.startingBid = startingBid;
    }

    public LocalDateTime getEndBidDate() {
        return endBidDate;
    }

    public void setEndBidDate(LocalDateTime endBidDate) {
        this.endBidDate = endBidDate;
    }

    public double getCurrentMaxBid() {
        return currentMaxBid;
    }

    public void setCurrentMaxBid(double currentMaxBid) {
        this.currentMaxBid = currentMaxBid;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public List<Bidding> getBidding() {
        return bidding;
    }

    public void addBidding(Bidding bidding) {
        bidding.setItem(this);
        this.bidding.add(bidding);
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
        return "Item{" + "id=" + id + ", title=" + title + ", description=" + description + ", startingBid=" + startingBid + ", endBidDate=" + endBidDate + ", currentMaxBid=" + currentMaxBid + '}';
    } 
}
