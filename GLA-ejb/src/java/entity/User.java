/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author alexis
 */
@Entity
@Table(name = "Users")
@NamedQueries({
    @NamedQuery(
            name = "User.findByEmail", 
            query = "SELECT u FROM User u WHERE u.email = :email "),
     @NamedQuery(
            name = "User.findByEmailAndPassword", 
            query = "SELECT u FROM User u WHERE u.email = :email AND u.password = :password ")
})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "firstname")
    private String firstname;
    
    @Column(name = "lastname")
    private String lastname;
    
    @Column(name = "cancelled_bids")
    private int cancelledBids = 0;
    
    public static final int MAX_CANCELLED_BIDS = 5;
    
    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private List<CreditCard> creditCard = new ArrayList<>(); 
    
    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private List<Item> item = new ArrayList<>(); 
    
    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private List<Address> adr = new ArrayList<>(); 
    
    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private List<Order> order = new ArrayList<>(); 
    
    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private List<Bidding> bidding = new ArrayList<>(); 
       
    public User() {
    }

    public User(String email, String password, String firstname, String lastname) {
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
    }
   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getCancelledBids() {
        return cancelledBids;
    }

    public void setCancelledBids(int cancelledBids) {
        this.cancelledBids = cancelledBids;
    }

    public List<CreditCard> getCreditCard() {
        return creditCard;
    }

    public void addCreditCard(CreditCard c) {
        c.setUser(this);
        this.creditCard.add(c);
    }

    public List<Item> getItem() {
        return item;
    }

    public void addItem(Item i) {
        i.setUser(this);
        this.item.add(i);
    }

    public List<Address> getAdr() {
        return adr;
    }

    public void addAdr(Address adr) {
        adr.setUser(this);
        this.adr.add(adr);
    }

    public List<Order> getOrder() {
        return order;
    }

    public void addOrder(Order order) {
        order.setUser(this);
        this.order.add(order);
    }

    public List<Bidding> getBidding() {
        return bidding;
    }

    public void addBidding(Bidding bidding) {
        bidding.setUser(this);
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", email=" + email + ", password=" + password + ", firstname=" + firstname + ", lastname=" + lastname + ", cancelledBids=" + cancelledBids + '}';
    }
    
}
