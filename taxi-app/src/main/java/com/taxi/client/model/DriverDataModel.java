package com.taxi.client.model;

import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.taxi.server.model.Driver;

import javax.inject.Inject;
import java.util.List;

public class DriverDataModel {
    /*private EventBus eventBus;
    List<Driver> allDrivers;
    Driver activeDriver;

    public DriverDataModel() {}

    //@Inject
    public DriverDataModel(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public List<Driver> getAllDrivers() {
        return this.allDrivers;
    }

    public void setAllDrivers(List<Driver> drivers) {
        *//*if (this.allDrivers != null && drivers != null) {
            if (this.allDrivers.size() == drivers.size()) return;
            else {
                this.allDrivers = drivers;
                eventBus.fireEvent(LoadLightDataEvent.create());
            }
        } else if (this.allDrivers == null && drivers != null) {
            this.allDrivers = drivers;
            eventBus.fireEvent(LoadLightDataEvent.create());
        } else if (drivers == null) {
            return;
        }*//*
    }

    public void setActiveDriver(Driver driver) {
*//*        if (driver == null) return;
        else {
            this.activeDriver = driver;
            eventBus.fireEvent(ChangeDataEvent.create());
        }*//*
    }

    public Driver getActiveDriver() {
        return this.activeDriver;
    }*/
}
