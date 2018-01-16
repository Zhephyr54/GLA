/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import db.dao.UserDAO;
import entity.Address;
import entity.User;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author yasar
 */
@Named(value = "register")
@RequestScoped
public class Register {

    @Inject
    UserDAO ud;

    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private String street;
    private String city;
    private Long code;

    public Register() {
    }

    public UserDAO getUd() {
        return ud;
    }

    public void setUd(UserDAO ud) {
        this.ud = ud;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }
    
    public void registerUser() {
        User u = new User(email, password, firstname, lastname);
        Address a = new Address(street,city,code);            
        u.addAdr(a);
        ud.create(u);
    }

}
