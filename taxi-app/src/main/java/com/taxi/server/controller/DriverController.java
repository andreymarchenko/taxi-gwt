package com.taxi.server.controller;

import com.taxi.server.dao.DriverDAO;
import com.taxi.server.model.Driver;
import com.taxi.server.utils.Mapper;
import com.taxi.shared.dto.DriverDto;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/driver")
public class DriverController {
    private DriverDAO driverDao = new DriverDAO();

    private final Mapper<Driver, DriverDto> driverMapper = new Mapper<>(Driver.class, DriverDto.class);

    @Path("/create")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createDriver(DriverDto driver) {
        driverDao.save(driverMapper.createEntity(driver));
    }

    @Path("/update")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateDriver(DriverDto driver) {
        driverDao.update(driverMapper.createEntity(driver));
    }

    @Path("/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public DriverDto getDriver(@PathParam(value="id") int id) {
        Driver driver = driverDao.getDriver(id);
        return driverMapper.createDto(driver);
    }

    @Path("/get/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<DriverDto> getAllDrivers() {
        List<Driver> allDrivers = driverDao.getAllDrivers();
        return driverMapper.createDtoList(allDrivers);
    }

    @Path("/login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void login(DriverDto clientDto) {
        driverDao.save(driverMapper.createEntity(clientDto));
    }

    @Path("/delete/{id}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public void delete(@PathParam(value="id") int id) {
        Driver driver = driverDao.getDriver(id);
        driverDao.delete(driver);
    }
}
