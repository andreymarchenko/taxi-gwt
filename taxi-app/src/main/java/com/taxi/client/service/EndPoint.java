package com.taxi.client.service;

import com.taxi.shared.dto.ClientDto;
import com.taxi.shared.dto.LoginDto;
import com.taxi.shared.dto.OrderDto;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/api")
public interface EndPoint extends RestService {

    @POST
    @Path("/client/login")
    void login(LoginDto loginDto,
               MethodCallback<ClientDto> callback);

    @POST
    @Path("/client/create")
    void createClient(ClientDto ClientDto,
               MethodCallback<ClientDto> callback);

    @POST
    @Path("/order/create")
    void createOrder(OrderDto OrderDto,
                     MethodCallback<OrderDto> callback);

}
