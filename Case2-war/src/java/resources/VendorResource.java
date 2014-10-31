// Author: Craig Treulieb 0606138
// Date: Oct 16 2014
// File: VendorResource.java
// Purpose: Vendor Resource for calling add/update/delete/retrieve from the VendorFacadeBean.
package resources;

import case2ejbs.VendorFacadeBean;
import dtos.VendorEJBDTO;
import java.net.URI;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("vendor")
public class VendorResource {
    

    @Context
    private UriInfo context;
    
    @EJB
    private VendorFacadeBean vfb;
    
    
    public VendorResource() {
    }

    /**
     * createVendorFromJson
     * @param vendor - VendorDTO containing new vendor info
     * @return Response from model
     */
    @POST
    @Consumes("application/json")
    public Response createVendorFromJson(VendorEJBDTO vendor) {
        int vendorNo = vfb.addVendor(vendor);
        URI uri = context.getAbsolutePath();
        return Response.created(uri).entity(vendorNo).build();
    }
    
    /**
     * getVendorsJson
     * @return all vendor info in Json Format
     */
    @GET
    @Path("vendors")
    @Produces("application/json")
    public List<VendorEJBDTO> getVendorsJson() {
        return vfb.getVendors();
    }
    
    /**
     * updateVendorFromJson
     * @param vendor - VendorDTO containing updated vendor info
     * @return response from model
     */
    @PUT
    @Consumes("application/json")
    public Response updateVendorFromJson(VendorEJBDTO vendor) {
        
        int numOfRowsUpdated = vfb.updateVendor(vendor);
        URI uri = context.getAbsolutePath();
        return Response.created(uri).entity(numOfRowsUpdated).build();
    }
    
    /**
     * deleteVendorFromJson
     * @param vendorno - Vendor to be deleted
     * @return response from model
     */
    @DELETE
    @Path("/{vendorno}")
    @Consumes("application/json")
    public Response deleteVendorFromJson(@PathParam("vendorno")int vendorno) {
        int numRowsDeleted = vfb.deleteVendor(vendorno);
        URI uri = context.getAbsolutePath();
        System.out.println("number of rows deleted " + numRowsDeleted);
        return Response.created(uri).entity(numRowsDeleted).build();
    }
}
