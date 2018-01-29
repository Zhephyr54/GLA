/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author NIhad
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/glaResponseB"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class BillingMDB implements MessageListener {
    
    public BillingMDB() {
    }
    
    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {  
            TextMessage tm = (TextMessage) message;  
            try {  
                String text = tm.getText();  
                System.out.println("Received new message :" + text);  
            } catch (JMSException e) {}  
        }  
    }
    
}
