package models;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.PurchaseordersModel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-11-24T18:51:58")
@StaticMetamodel(PurchaseorderlineitemsModel.class)
public class PurchaseorderlineitemsModel_ { 

    public static volatile SingularAttribute<PurchaseorderlineitemsModel, String> prodcd;
    public static volatile SingularAttribute<PurchaseorderlineitemsModel, BigDecimal> price;
    public static volatile SingularAttribute<PurchaseorderlineitemsModel, Integer> lineid;
    public static volatile SingularAttribute<PurchaseorderlineitemsModel, Integer> qty;
    public static volatile SingularAttribute<PurchaseorderlineitemsModel, PurchaseordersModel> ponumber;

}