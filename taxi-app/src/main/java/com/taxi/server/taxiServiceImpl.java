package com.taxi.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.taxi.client.taxiService;

public class taxiServiceImpl extends RemoteServiceServlet implements taxiService {
    // Implementation of sample interface method
    public String getMessage(String msg) {
        return "Client said: \"" + msg + "\"<br>Server answered: \"Hi!\"";
    }
}