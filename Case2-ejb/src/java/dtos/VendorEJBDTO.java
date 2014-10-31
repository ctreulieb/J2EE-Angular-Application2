package dtos;
/**
 * VendorEJBDTO.java
 *
 *   Created:    9-10-2014 3:03 PM
 *   @author:    Evan
 *   Transfer Object for vendor information traveling 
 *   between War and Jar containers
 *   Revisions:   @version 1.0, 09/10/14:
 *      
 */
import java.io.Serializable;

public class VendorEJBDTO implements Serializable {

    public VendorEJBDTO() {
    }

    private Integer vendorno;
    private String name;
    private String address1;
    private String city;
    private String province;
    private String postalcode;
    private String phone;
    private String vendortype;
    private String email;

    public Integer getVendorno() {
        return vendorno;
    }

    public void setVendorno(Integer vendorno) {
        this.vendorno = vendorno;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVendortype() {
        return vendortype;
    }

    public void setVendortype(String vendortype) {
        this.vendortype = vendortype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}