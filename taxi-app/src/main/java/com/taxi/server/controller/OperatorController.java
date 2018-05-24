package com.taxi.server.controller;

import com.taxi.server.dao.OperatorDAO;
import com.taxi.server.model.Driver;
import com.taxi.server.model.Operator;
import com.taxi.server.utils.Mapper;
import com.taxi.shared.dto.DriverDto;
import com.taxi.shared.dto.OperatorDto;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/operator")
public class OperatorController {
    private OperatorDAO operatorDao = new OperatorDAO();

    private final Mapper<Operator, OperatorDto> operatorMapper = new Mapper<>(Operator.class, OperatorDto.class);

    @Path("/create")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createOperator(OperatorDto operator) {
        operatorDao.save(operatorMapper.createEntity(operator));
    }

    @Path("/update")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateOperator(OperatorDto operator) {
        operatorDao.update(operatorMapper.createEntity(operator));
    }

    @Path("/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public OperatorDto getOperator(@PathParam(value="id") int id) {
        Operator operator = operatorDao.getOperator(id);
        return operatorMapper.createDto(operator);
    }

    @Path("/get/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<OperatorDto> getAllOperators() {
        List<Operator> allOperators = operatorDao.getAllOperators();
        return operatorMapper.createDtoList(allOperators);
    }

    @Path("/login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void login(OperatorDto operatorDto) {
        operatorDao.save(operatorMapper.createEntity(operatorDto));
    }

    @Path("/delete/{id}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public void delete(@PathParam(value="id") int id) {
        Operator operator = operatorDao.getOperator(id);
        operatorDao.delete(operator);
    }
}
