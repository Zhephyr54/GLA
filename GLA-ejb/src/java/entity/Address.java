/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author yasar
 */
@Entity
@Table(name = "Addresses")
@NamedQueries({
    @NamedQuery(
            name = "Address.getUserAddress", 
            query = "SELECT a FROM Address a WHERE a.user.id = :userId ")
})
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "address")
    private String adr;
    
    @ManyToOne
    private User user;

    @OneToMany(mappedBy="address")
    private List<Order> orders;
    
    @OneToMany(mappedBy="billingAddress")
    private List<Order> orders2;
    
    
    public Address() {
    }

    public Address(String adr) {
        this.adr = adr;
    }

    public String getAdr() {
        return adr;
    }

    public void setAdr(String adr) {
        this.adr = adr;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        order.setAddress(this);
        this.orders.add(order);
    }

    public List<Order> getOrders2() {
        return orders2;
    }

    public void addOrder2(Order order2) {
        order2.setBillingAddress(this);
        this.orders2.add(order2);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        if (!(object instanceof Address)) {
            return false;
        }
        Address other = (Address) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Address{" + "id=" + id + ", address=" + adr + ", user=" + user + ", orders=" + orders + '}';
    }    
}
