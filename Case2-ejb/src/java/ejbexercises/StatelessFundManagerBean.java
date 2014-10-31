/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbexercises;

import java.math.BigDecimal;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Craig
 */
@Stateless
@LocalBean
public class StatelessFundManagerBean {
    
    public BigDecimal addFunds(BigDecimal balance, BigDecimal amount) {
        balance = balance.add(amount);
        return balance;
    }
    
    public BigDecimal withdrawFunds(BigDecimal balance, BigDecimal amount) {
        if(balance.doubleValue() < 0) {
            return new BigDecimal("0.00");
        } else {
            balance = balance.subtract(amount);
            return balance;
        }
    }
}
