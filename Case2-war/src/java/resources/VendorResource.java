// Author: Craig Treulieb 0606138
// Date: Oct 16 2014
// File: VendorResource.java
// Purpose: Vendor Resource for calling add/update/delete/retrieve from the model.
package resources;

import dtos.VendorDTO;
import java.net.URI;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.sql.DataSource;
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
import models.VendorModel;

@Path("vendor")
public class VendorResource {

    @Context
    private UriInfo context;
    
    @Resource(lookup = "jdbc/Info5059db")
    DataSource ds;

    public VendorResource() {
    }

    /**
     * createVendorFromJson
     * @param vendor - VendorDTO containing new vendor info
     * @return Response from model
     */
    @POST
    @Consumes("application/json")
    public Response createVendorFromJson(VendorDTO vendor) {
        VendorModel model = new VendorModel();
        int vendorNo = model.addVendor(vendor, ds);
        URI uri = context.getAbsolutePath();
        return Response.created(uri).entity(vendorNo).build();
    }
    
    /**
     * getVendorsJson
     * @return all vendor info in Json Format
     */
    @GET
    @Produces("application/json")
    public ArrayList<VendorDTO> getVendorsJson() {
        VendorModel model = new VendorModel();
        return model.getVendors(ds);
    }
    
    /**
     * updateVendorFromJson
     * @param vendor - VendorDTO containing updated vendor info
     * @return response from model
     */
    @PUT
    @Consumes("application/json")
    public Response updateVendorFromJson(VendorDTO vendor) {
        VendorModel model = new VendorModel();
        int numOfRowsUpdated = model.updateVendor(vendor, ds);
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
        VendorModel model = new VendorModel();
        int numRowsDeleted = model.deleteVendor(vendorno, ds);
        URI uri = context.getAbsolutePath();
        System.out.println("number of rows deleted " + numRowsDeleted);
        return Response.created(uri).entity(numRowsDeleted).build();
    }
}
