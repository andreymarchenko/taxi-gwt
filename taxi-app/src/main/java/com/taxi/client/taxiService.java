package com.taxi.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("taxiService")
public interface taxiService extends RemoteService {
    // Sample interface method of remote interface
    String getMessage(String msg);

    public static class App {
        private static taxiServiceAsync ourInstance = GWT.create(taxiService.class);

        public static synchronized taxiServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
