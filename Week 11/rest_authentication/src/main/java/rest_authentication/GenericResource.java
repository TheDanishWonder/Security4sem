/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest_authentication;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author ndupo
 */
@Path("demo")
public class GenericResource{

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }

        /**
     * Retrieves representation of an instance of rest_authentication.GenericResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getMsg() {
        return "{\"msg\":\"Hello there\"}";
    }
    /**
     * Retrieves representation of an instance of rest_authentication.GenericResource
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/admin")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAdmin() {
        return "Hello from Admin";
    }

        /**
     * Retrieves representation of an instance of rest_authentication.GenericResource
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/user")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUser() {
        return "Hello from User:";
    }
    /**
     * PUT method for updating or creating an instance of GenericResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putXml(String content) {
    }
}
