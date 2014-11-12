package models;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.PurchaseorderlineitemsModel;
import models.VendorsModel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-11-11T20:16:35")
@StaticMetamodel(PurchaseordersModel.class)
public class PurchaseordersModel_ { 

    public static volatile SingularAttribute<PurchaseordersModel, BigDecimal> amount;
    public static volatile SingularAttribute<PurchaseordersModel, Date> podate;
    public static volatile SingularAttribute<PurchaseordersModel, VendorsModel> vendorno;
    public static volatile CollectionAttribute<PurchaseordersModel, PurchaseorderlineitemsModel> purchaseorderlineitemsModelCollection;
    public static volatile SingularAttribute<PurchaseordersModel, Integer> ponumber;

}