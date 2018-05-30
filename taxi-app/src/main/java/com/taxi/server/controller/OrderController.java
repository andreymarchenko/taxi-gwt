package com.taxi.server.controller;

import com.google.gwt.core.client.GWT;
import com.taxi.server.dao.OrderDAO;
import com.taxi.server.model.Order;
import com.taxi.server.utils.Mapper;
import com.taxi.shared.dto.OrderDto;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/order")
public class OrderController {

    private OrderDAO orderDao = new OrderDAO();

    private final Mapper<Order, OrderDto> operatorMapper = new Mapper<>(Order.class, OrderDto.class);

    @Path("/create")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public OrderDto createOrder(OrderDto order) {
        orderDao.save(operatorMapper.createEntity(order));
        GWT.log("ПРИШЕЛ");
        return order;
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public OrderDto getOrder(@PathParam(value="id") int id) {
        Order order = orderDao.getOrder(id);
        return operatorMapper.createDto(order);
    }

    @Path("/client/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrderDto> getOrdersByClient(@PathParam(value="id") int id) {
        List<Order> orders = orderDao.getOrdersByClient(id);
        return operatorMapper.createDtoList(orders);
    }

    @Path("/driver/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrderDto> getOrdersByDriver(@PathParam(value="id") int id) {
        List<Order> orders = orderDao.getOrdersByDriver(id);
        return operatorMapper.createDtoList(orders);
    }

    @Path("/free")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrderDto> getFreeOrders() {
        List<Order> orders = orderDao.getFreeOrders();
        return operatorMapper.createDtoList(orders);
    }

    @Path("/start")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public OrderDto startOrder(OrderDto orderDto) {
        if (orderDto == null || orderDto.getDriver() == null) {
            return null;
        }
        orderDao.save(operatorMapper.createEntity(orderDto));
        Order order = orderDao.getOrder(orderDto.getId());
        return operatorMapper.createDto(order);
    }

    @Path("/delete/{id}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public void delete(@PathParam(value="id") int id) {
        Order order = orderDao.getOrder(id);
        orderDao.delete(order);
    }
}
