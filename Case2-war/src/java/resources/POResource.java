// Author: Craig Treulieb 0606138
// Date: Oct 16 2014
// File: POResource.java
// Purpose: Purchase Order Resource for calling add/update/delete/retrieve from the model.
package resources;

import case2ejbs.POFacadeBean;
import dtos.PurchaseOrderEJBDTO;
import java.net.URI;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.sql.DataSource;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("po")
public class POResource {
    
    @Context
    private UriInfo context;
    
    @Resource(lookup = "jdbc/Info5059db")
    DataSource ds;
    
    @EJB
    private POFacadeBean pfb;
    
    public POResource() {
    }
    
    /**
     * createPo - Creates Purchase order out of provided PurchaseOrderDTO
     * @param po - PurchaseOrderDTO containing all info about Purchase Order to be added
     * @return - Response Object from model
     */
    @POST
    @Consumes("application/json")
    public Response createPO(PurchaseOrderEJBDTO po) {
        int pono = pfb.addPO(po);
        URI uri = context.getAbsolutePath();
        return Response.created(uri).entity(pono).build();
    }
    
}