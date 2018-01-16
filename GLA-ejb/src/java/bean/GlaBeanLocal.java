/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entity.User;
import javax.ejb.Local;

/**
 *
 * @author yasar
 */
@Local
public interface GlaBeanLocal {
    public User addUser(User p);
}
