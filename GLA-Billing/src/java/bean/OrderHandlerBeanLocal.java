/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entity.Order;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author alexis
 */
@Stateless
public class OrderHandlerBeanLocal implements OrderHandlerBean {

    private final List<Order> orders;

    public OrderHandlerBeanLocal() {
        orders = new ArrayList<>();
    }
    
    @Override
    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public void addOrder(Order order) {
        orders.add(order);
        System.out.println(order);
    } 

    @Override
    public void removerOrder(Order order) {
        orders.remove(order);
    }
    
}
