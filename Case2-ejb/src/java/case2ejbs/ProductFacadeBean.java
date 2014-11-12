/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package case2ejbs;

import dtos.ProductEJBDTO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import models.ProductsModel;
import models.VendorsModel;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

/**
 *
 * @author Craig
 */
@Stateless
@LocalBean
public class ProductFacadeBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    private EntityManager em;
    
    public int addProduct(ProductEJBDTO dto) {
        
        ProductsModel pm;
        int retVal = -1;
        byte[] qrcodebin;
        try{
            pm = new ProductsModel(dto.getProductcode(), 
                    dto.getVendorsku(), 
                    dto.getProductname(), 
                    new BigDecimal(dto.getCostprice()), 
                    new BigDecimal(dto.getMsrp()), 
                    dto.getRop(), 
                    dto.getEoq(), 
                    dto.getQoh(), 
                    dto.getQoo());
            pm.setVendorno(new VendorsModel(dto.getVendorno()));
            qrcodebin = QRCode.from(dto.getQrcodetext()).to(ImageType.PNG).stream().toByteArray();
            pm.setQrcode(qrcodebin);
            pm.setQrcodetxt(dto.getQrcodetext());
            em.persist(pm);
            em.flush();
            retVal = 1;
        } catch (ConstraintViolationException v) { 
            Set<ConstraintViolation<?>> coll = v.getConstraintViolations(); 
            for (ConstraintViolation s : coll) {
                System.out.println(s.getPropertyPath() + " " + s.getMessage());
            }
        } catch (Exception e) {        
            System.out.println(e.getMessage());
        }
        return retVal;
    }
    
    public int updateProduct(ProductEJBDTO dto) {
        int rowsUpdated = -1;
        ProductsModel pm;
        byte[] qrbin;
        try{
            pm = em.find(ProductsModel.class, dto.getProductcode());
            pm.setCostprice(new BigDecimal(dto.getCostprice()));
            pm.setEoq(dto.getEoq());
            pm.setMsrp(new BigDecimal(dto.getMsrp()));
            pm.setProductname(dto.getProductname());
            pm.setQoh(dto.getQoh());
            pm.setQoo(dto.getQoo());
            pm.setQrcodetxt(dto.getQrcodetext());
            qrbin = QRCode.from(dto.getQrcodetext()).to(ImageType.PNG).stream().toByteArray();
            pm.setQrcode(qrbin);
            pm.setRop(dto.getRop());
            pm.setVendorsku(dto.getVendorsku());
            pm.setVendorno(new VendorsModel(dto.getVendorno()));
            em.flush();
            rowsUpdated = 1;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return rowsUpdated;
    }
    
    public int deleteProduct(String productcode) {
        int rowsDeleted = -1;
        ProductsModel pm;
        try {
            pm = em.find(ProductsModel.class, productcode);
            em.remove(pm);
            em.flush();
            rowsDeleted = 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return rowsDeleted;
    }
    
    public ArrayList<ProductEJBDTO> getProducts(){
        List<ProductsModel> products;
        ArrayList<ProductEJBDTO> productsDTO = new ArrayList();
        try {
            Query qry = em.createNamedQuery("ProductsModel.findAll");
            products = qry.getResultList();
            
            
            for(ProductsModel p : products) {
                ProductEJBDTO dto = new ProductEJBDTO();
                dto.setCostprice(p.getCostprice().doubleValue());
                dto.setEoq(p.getEoq());
                dto.setMsrp(p.getMsrp().doubleValue());
                dto.setProductcode(p.getProductcode());
                dto.setProductname(p.getProductname());
                dto.setQoh(p.getQoh());
                dto.setQoo(p.getQoo());
                dto.setQrcode((byte[])p.getQrcode());
                dto.setQrcodetext(p.getQrcodetxt());
                dto.setRop(p.getRop());
                dto.setVendorno(p.getVendorno().getVendorno());
                dto.setVendorsku(p.getVendorsku());
                productsDTO.add(dto);
            }
        } catch (Exception e) {
            System.out.println("other issue - " + e.getMessage());
        }
        return productsDTO;
    }
    
    public ArrayList<ProductEJBDTO> getAllProductsForVendor(int vendorNo){
        List<ProductsModel> products;
        ArrayList<ProductEJBDTO> productsDTO = new ArrayList();
        try {
            Query qry = em.createNamedQuery("ProductsModel.findByVendorno").setParameter("vendorno", new VendorsModel(vendorNo));
            products = qry.getResultList();
            
            
            for(ProductsModel p : products) {
                ProductEJBDTO dto = new ProductEJBDTO();
                dto.setCostprice(p.getCostprice().doubleValue());
                dto.setEoq(p.getEoq());
                dto.setMsrp(p.getMsrp().doubleValue());
                dto.setProductcode(p.getProductcode());
                dto.setProductname(p.getProductname());
                dto.setQoh(p.getQoh());
                dto.setQoo(p.getQoo());
                dto.setQrcode((byte[])p.getQrcode());
                dto.setQrcodetext(p.getQrcodetxt());
                dto.setRop(p.getRop());
                dto.setVendorno(p.getVendorno().getVendorno());
                dto.setVendorsku(p.getVendorsku());
                productsDTO.add(dto);
            }
        } catch (Exception e) {
            System.out.println("other issue - " + e.getMessage());
        }
        return productsDTO;
    }
    
    public ProductEJBDTO getProduct(String productcode){
        ProductsModel p;
        ProductEJBDTO dto = new ProductEJBDTO();
        try{
            Query qry = em.createNamedQuery("ProductsModel.findByProductcode").setParameter("productcode", productcode);
            p = (ProductsModel)qry.getSingleResult();
            dto.setCostprice(p.getCostprice().doubleValue());
            dto.setEoq(p.getEoq());
            dto.setMsrp(p.getMsrp().doubleValue());
            dto.setProductcode(p.getProductcode());
            dto.setProductname(p.getProductname());
            dto.setQoh(p.getQoh());
            dto.setQoo(p.getQoo());
            dto.setQrcode((byte[])p.getQrcode());
            dto.setQrcodetext(p.getQrcodetxt());
            dto.setRop(p.getRop());
            dto.setVendorno(p.getVendorno().getVendorno());
            dto.setVendorsku(p.getVendorsku());
        }catch (Exception e) {
            System.out.println("other issue - " + e.getMessage());
        }
        return dto;
    }
}
