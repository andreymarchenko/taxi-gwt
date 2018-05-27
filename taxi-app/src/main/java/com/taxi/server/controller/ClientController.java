package com.taxi.server.controller;

import com.taxi.server.dao.ClientDAO;
import com.taxi.server.model.Client;
import com.taxi.server.utils.Mapper;
import com.taxi.shared.dto.ClientDto;
import com.taxi.shared.dto.LoginDto;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/client")
public class ClientController{

    private ClientDAO clientDAO = new ClientDAO();

    private final Mapper<Client, ClientDto> clientMapper = new Mapper<>(Client.class, ClientDto.class);

    @Path("/login")
    @POST
    public void login(LoginDto loginDto) {
        System.out.println("ЛОГИН");
    }

    @Path("/create")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public ClientDto createClient(ClientDto client) {
        clientDAO.save(clientMapper.createEntity(client));
        return client;
    }

    @Path("/update")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateClient(ClientDto client) {
        clientDAO.update(clientMapper.createEntity(client));
    }

    @Path("/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ClientDto getClient(@PathParam(value="id") int id) {
        Client client = clientDAO.getClient(id);
        return clientMapper.createDto(client);
    }

    @Path("/get/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ClientDto> getAllClient() {
        List<Client> allClient = clientDAO.getAllClients();
        return clientMapper.createDtoList(allClient);
    }

    @Path("/delete/{id}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public void delete(@PathParam(value="id") int id) {
        Client client = clientDAO.getClient(id);
        clientDAO.delete(client);
    }
}
