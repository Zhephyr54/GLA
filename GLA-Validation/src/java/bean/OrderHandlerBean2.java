/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entity.Order;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author alexis
 */
@Local
public interface OrderHandlerBean2 {
 
    public void addOrder(Order order);
 
    public void removerOrder(Order order);
    
    public List<Order> getOrders();
 
}
