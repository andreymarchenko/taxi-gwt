package test.server.controller;

import com.ibm.icu.impl.Assert;
import com.taxi.server.model.Client;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

class ClientControllerTest {

    List<Client> getAllClients() throws ClientProtocolException, IOException
    {
        HttpUriRequest request = new HttpGet(
                "http://127.0.0.1:8888/api/client/get/all" );
        HttpResponse response =
                HttpClientBuilder.create().build().execute(request);
        List<Client> clients = RestTestUtil.retrieveResourceFromResponse(
                response, List.class);

        return clients;
    }

    void deleteClient(int ID) throws ClientProtocolException, IOException
    {
        HttpUriRequest request = new HttpGet(
                "http://127.0.0.1:8888/api/client/delete/" +
                        Integer.toString(ID));
        HttpResponse response =
                HttpClientBuilder.create().build().execute(request);
    }

    @Test
    void getAllClient() throws ClientProtocolException, IOException {
        org.junit.Assert.assertNotEquals(null, getAllClients());
    }

    @Test
    void getClient() throws ClientProtocolException, IOException {
        HttpUriRequest request = new HttpGet("http://127.0.0.1:8888/api/client/get/1");

        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        Client client = RestTestUtil.retrieveResourceFromResponse(
                response, Client.class);

        org.junit.Assert.assertNotEquals(null, client);
    }

    @Test
    void createClient() throws ClientProtocolException, IOException {

        // Creating of client
        HttpUriRequest request = new HttpGet(
                "http://127.0.0.1:8888/api/client/create/cl1/cl1/abc" );
        HttpResponse response =
                HttpClientBuilder.create().build().execute(request);
        org.junit.Assert.assertEquals(response.getStatusLine().getStatusCode(),
                HttpStatus.SC_OK);

        // Check of creating client
        List<Client> clients = getAllClients();
        boolean isOk = false;
        int ID;
        for (Client client : clients) {
            if((client.getName() == "cl1") &&
               (client.getPassword() == "cl1") &&
               (client.getDescription() == "abc"))
            {
                isOk = true;
                ID = client.getID();

                // Deleting of new client
                deleteClient(ID);

                break;
            }
        }

        if(!isOk)
        {
            org.junit.Assert.assertTrue(false);
        }
    }

    @Test
    void updateClient() {
    }

    @Test
    void login() {
    }

    @Test
    void delete() {
    }
}