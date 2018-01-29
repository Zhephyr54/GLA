/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author Nihad
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/glaResponse"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class OrderMDB implements MessageListener {
    
    public OrderMDB() {
    }
    
    @Override
    public void onMessage(Message message) {
        System.out.println("mdb.OrderMDB.onMessage()");
        System.out.println(message.toString());
    }
    
}
