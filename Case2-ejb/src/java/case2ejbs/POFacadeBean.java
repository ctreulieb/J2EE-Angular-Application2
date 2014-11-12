/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package case2ejbs;

import dtos.PurchaseOrderEJBDTO;
import dtos.PurchaseOrderLineitemEJBDTO;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import models.ProductsModel;
import models.PurchaseorderlineitemsModel;
import models.PurchaseordersModel;
import models.VendorsModel;

/**
 *
 * @author Craig
 */
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
@Stateless
@LocalBean
public class POFacadeBean {

    @PersistenceContext
    private EntityManager em;
    
    @Resource 
    private EJBContext context;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public int addPO(PurchaseOrderEJBDTO poDTO) {
        PurchaseordersModel pm;
        VendorsModel vm;
        int poNum = -1;
        Date poDate = new java.util.Date();
        try{
            vm = em.find(VendorsModel.class, poDTO.getVendorno());
            pm = new PurchaseordersModel(0, new BigDecimal(Double.toString(poDTO.getTotal())), poDate);
            pm.setVendorno(vm);
            em.persist(pm);
            em.flush();
            poNum = pm.getPonumber();
            for (PurchaseOrderLineitemEJBDTO line : poDTO.getItems()) {
                if(line.getQty() > 0) {
                    int retVal = addPOLine(line, pm);
                    if(retVal < 0){
                        throw new Exception("Error adding PO Line Item!");
                    }
                }
            }
        }catch (ConstraintViolationException v) { 
            Set<ConstraintViolation<?>> coll = v.getConstraintViolations(); 
            for (ConstraintViolation s : coll) {
                System.out.println(s.getPropertyPath() + " " + s.getMessage());
            }
        } catch (Exception e) {        
            System.out.println(e.getMessage());
            context.setRollbackOnly();
        }      
        return poNum;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    private int addPOLine(PurchaseOrderLineitemEJBDTO line, PurchaseordersModel pom) {
        PurchaseorderlineitemsModel polm;
        int polNum = -1;
        try{
            int rowsUpdated = updateInventory(line.getProductcode(), line.getQty());
            if(rowsUpdated < 0)
                throw new Exception("Error updating inventory");
            polm = new PurchaseorderlineitemsModel(0, line.getProductcode(), line.getQty(), new BigDecimal(line.getCostprice()));
            polm.setPonumber(pom);
            em.persist(polm);
            em.flush();
            polNum = polm.getLineid();
        }catch (ConstraintViolationException v) { 
            Set<ConstraintViolation<?>> coll = v.getConstraintViolations(); 
            for (ConstraintViolation s : coll) {
                System.out.println(s.getPropertyPath() + " " + s.getMessage());
            }
        } catch (Exception e) {        
            System.out.println(e.getMessage());
            context.setRollbackOnly();
        } 
        return polNum;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    private int updateInventory(String productCode, int qty){
        ProductsModel pm;
        try{
           pm = em.find(ProductsModel.class, productCode);
            pm.setQoo(qty + pm.getQoo());
            em.flush();
            return 1;  
        } catch(ConstraintViolationException v){
            Set<ConstraintViolation<?>> coll = v.getConstraintViolations(); 
            for (ConstraintViolation s : coll) {
                System.out.println(s.getPropertyPath() + " " + s.getMessage());
            }
        }catch (Exception e) {        
            System.out.println(e.getMessage());
            context.setRollbackOnly();
        } 
        return -1;
    }
}
