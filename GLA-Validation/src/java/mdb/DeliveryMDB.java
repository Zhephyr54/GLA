/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdb;

import entity.Order;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import managed_bean.DeliveryManagedBean;

/**
 *
 * @author Nihad
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/glaRequest"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class DeliveryMDB implements MessageListener {
    
    private DeliveryManagedBean deliveryManagedBean;
    
    public DeliveryMDB() {
    }
    
    @Override
    public void onMessage(Message message) {
        try {
            deliveryManagedBean.setOrder(message.getBody(Order.class));
            System.out.println("XXXXXXXXXXXXXXXX");
        } catch (JMSException ex) {
            Logger.getLogger(DeliveryMDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}