package com.taxi.client.service;

import com.taxi.shared.Login;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;

@Path("/api")
public interface EndPoint extends RestService {

    @POST
    @Path("user/login")
    void login(MethodCallback callback);
}
