package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.ProductsModel;
import models.PurchaseordersModel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-11-11T20:16:35")
@StaticMetamodel(VendorsModel.class)
public class VendorsModel_ { 

    public static volatile SingularAttribute<VendorsModel, String> postalcode;
    public static volatile SingularAttribute<VendorsModel, String> phone;
    public static volatile CollectionAttribute<VendorsModel, ProductsModel> productsModelCollection;
    public static volatile SingularAttribute<VendorsModel, String> email;
    public static volatile SingularAttribute<VendorsModel, String> name;
    public static volatile SingularAttribute<VendorsModel, String> province;
    public static volatile SingularAttribute<VendorsModel, String> address1;
    public static volatile SingularAttribute<VendorsModel, Integer> vendorno;
    public static volatile SingularAttribute<VendorsModel, String> vendortype;
    public static volatile CollectionAttribute<VendorsModel, PurchaseordersModel> purchaseordersModelCollection;
    public static volatile SingularAttribute<VendorsModel, String> city;

}