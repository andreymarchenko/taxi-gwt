package com.taxi.server.service;

import com.google.gwt.core.client.GWT;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/server")
public class EndPoint {
    @Path("/login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void login() {
       GWT.log("Login successful");
    }
}