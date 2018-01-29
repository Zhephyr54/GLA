/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdb;

import entity.Order;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import managed_bean.BillingManagedBean;

/**
 *
 * @author Nihad
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/glaRequestB"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class BillingMDB implements MessageListener {
    
    private BillingManagedBean billingManagedBean;
    
    public BillingMDB() {
    }
    
    @Override
    public void onMessage(Message message) {
        try {
            billingManagedBean.setOrder(message.getBody(Order.class));
        } catch (JMSException ex) {}
    }
    
}
