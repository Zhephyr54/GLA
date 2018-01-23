/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author yasar
 */
@Entity
@Table(name = "Biddings")
@NamedQueries({
    @NamedQuery(
            name = "Bidding.getUserBiddings", 
            query = "SELECT b FROM Bidding b WHERE b.user.id = :userId ORDER BY b.item.id")
})
public class Bidding implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "bidding_date")
    private LocalDateTime biddingDate = LocalDateTime.now();
    
    @Column(name = "price", precision = 9, scale = 2)
    private BigDecimal price;
    
    @ManyToOne
    private User user;
    
    @ManyToOne
    private Item item;
 
    public Bidding() {
    }

    public Bidding(BigDecimal price, User user, Item item) {
        this.price = price;
        this.user = user;
        this.item = item;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getBiddingDate() {
        return biddingDate;
    }

    public void setBiddingDate(LocalDateTime biddingDate) {
        this.biddingDate = biddingDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
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
        if (!(object instanceof Bidding)) {
            return false;
        }
        Bidding other = (Bidding) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Bidding{" + "id=" + id + ", date=" + biddingDate + ", price=" + price + '}';
    }
  
}
