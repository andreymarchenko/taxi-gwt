package test.server.controller;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.taxi.shared.dto.ClientDto;

public class ClientControllerTest {

    public static int parseClient(String str, int startPos, ClientDto client) {

        String subStr = str.substring(startPos);
        int pos = 0;
        ++pos; // skip '['

        {
            String value = "";
            while (subStr.charAt(pos) != ',') {
                value = value + subStr.charAt(pos);
                ++pos;
            }
            ++pos; // skip ','
            client.setId(Integer.parseInt(value));
        }

        {
            String value = "";
            while (subStr.charAt(pos) != ',') {
                value = value + subStr.charAt(pos);
                ++pos;
            }
            ++pos; // skip ','
            String subValue = value.substring(1, value.length() - 1);
            client.setName(subValue);
        }

        {
            String value = "";
            while (subStr.charAt(pos) != ',') {
                value = value + subStr.charAt(pos);
                ++pos;
            }
            ++pos; // skip ','
            String subValue = value.substring(1, value.length() - 1);
            client.setPassword(subValue);
        }

        {
            String value = "";
            while (subStr.charAt(pos) != ']') {
                value = value + subStr.charAt(pos);
                ++pos;
            }
            ++pos; // skip ']'
            String subValue = value.substring(1, value.length() - 1);
            client.setDescription(subValue);
        }

        return pos;
    }

    public static List<ClientDto> getAllClients() throws ClientProtocolException, IOException
    {
        HttpUriRequest request = new HttpGet(
                "http://127.0.0.1:8888/api/client/get/all" );
        HttpResponse response =
                HttpClientBuilder.create().build().execute(request);
        HttpEntity entity = response.getEntity();
        String entityString = EntityUtils.toString(entity);
        entityString = entityString.substring(1, entityString.length() - 1);
        List<ClientDto> clients = new ArrayList<ClientDto>();
        int i = 0;
        while (i < entityString.length())
        {
            ClientDto client = new ClientDto();
            i += parseClient(entityString, i, client);
            clients.add(client);
            ++i; // skip ','
        }

        return clients;
    }

    public void createNewClient() throws ClientProtocolException, IOException
    {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost request = new HttpPost("http://127.0.0.1:8888/api/client/create");

        String params = "{\"name\":\"cl1\",\"password\":\"cl1\",\"description\":\"abc\"}";
        StringEntity entity = new StringEntity(params);
        request.setHeader("Accept", "application/json");
        request.setHeader("Content-type", "application/json");
        request.setEntity(entity);

        CloseableHttpResponse response = httpClient.execute(request);
        org.junit.Assert.assertEquals(response.getStatusLine().getStatusCode(),
                HttpStatus.SC_OK);
        httpClient.close();
    }

    public void deleteClient(int ID) throws ClientProtocolException, IOException
    {
        HttpUriRequest request = new HttpGet(
                "http://127.0.0.1:8888/api/client/delete/" +
                        Integer.toString(ID));
        HttpResponse response =
                HttpClientBuilder.create().build().execute(request);
    }

    @Test
    public void getAllClient() throws ClientProtocolException, IOException {
        org.junit.Assert.assertNotEquals(null, getAllClients());
    }

    @Test
    public void getClient() throws ClientProtocolException, IOException {
        HttpUriRequest request = new HttpGet("http://127.0.0.1:8888/api/client/get/1");

        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        String clientStr = EntityUtils.toString(response.getEntity());

        org.junit.Assert.assertNotEquals(null, clientStr);
    }

    @Test
    public void createClient() throws ClientProtocolException, IOException {
        createNewClient();

        // Check of creating new client
        List<ClientDto> clients = getAllClients();
        boolean isOk = false;
        int ID;
        for (ClientDto client : clients) {
            if((client.getName().equals("cl1")) &&
               (client.getPassword().equals("cl1")) &&
               (client.getDescription().equals("abc")))
            {
                isOk = true;
                ID = client.getId();

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
    public void updateClient() throws ClientProtocolException, IOException {
        createNewClient();

        // Find new client
        int ID = 0;
        {
            List<ClientDto> clients = getAllClients();
            for (ClientDto client : clients) {
                if ((client.getName().equals("cl1")) &&
                        (client.getPassword().equals("cl1")) &&
                        (client.getDescription().equals("abc"))) {
                    ID = client.getId();
                    break;
                }
            }
        }

        // Update client
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost request = new HttpPost("http://127.0.0.1:8888/api/client/update");

        String params = "{\"id\":\"" + Integer.toString(ID) + "\"," + "\"name\":\"cl1\",\"password\":\"cl1\",\"description\":\"cba\"}";
        StringEntity entity = new StringEntity(params);
        request.setHeader("Accept", "application/json");
        request.setHeader("Content-type", "application/json");
        request.setEntity(entity);

        CloseableHttpResponse response = httpClient.execute(request);
        org.junit.Assert.assertEquals(response.getStatusLine().getStatusCode(),
                HttpStatus.SC_NO_CONTENT);
        httpClient.close();

        // Check of updating new client
        List<ClientDto> clients = getAllClients();
        boolean isOk = false;
        for (ClientDto client : clients) {
            if((client.getName().equals("cl1")) &&
               (client.getDescription().equals("cba")))
            {
                isOk = true;

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
    public void login() throws ClientProtocolException, IOException {
        createNewClient();

        // Get id of new client
        int ID = 0;
        {
            List<ClientDto> clients = getAllClients();
            for (ClientDto client : clients) {
                if ((client.getName().equals("cl1")) &&
                        (client.getPassword().equals("cl1")) &&
                        (client.getDescription().equals("abc"))) {
                    ID = client.getId();
                    break;
                }
            }
        }

        // Login
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost request = new HttpPost("http://127.0.0.1:8888/api/client/login");

        String params = "{\"username\":\"cl1\",\"password\":\"cl1\"}";
        StringEntity entity = new StringEntity(params);
        request.setHeader("Accept", "application/json");
        request.setHeader("Content-type", "application/json");
        request.setEntity(entity);

        CloseableHttpResponse response = httpClient.execute(request);
        org.junit.Assert.assertEquals(response.getStatusLine().getStatusCode(),
                HttpStatus.SC_NO_CONTENT);
        httpClient.close();

        // Delete new client
        deleteClient(ID);
    }

    @Test
    public void delete() throws ClientProtocolException, IOException {
        createNewClient();

        // Get id of new client
        int ID = 0;
        {
            List<ClientDto> clients = getAllClients();
            for (ClientDto client : clients) {
                if ((client.getName().equals("cl1")) &&
                    (client.getPassword().equals("cl1")) &&
                    (client.getDescription().equals("abc"))) {
                    ID = client.getId();
                    break;
                }
            }
        }

        // Deleting of new client
        deleteClient(ID);

        // Check of deleting new client
        {
            List<ClientDto> clients = getAllClients();
            boolean isOk = true;
            for (ClientDto client : clients) {
                if ((client.getName().equals("cl1")) &&
                    (client.getPassword().equals("cl1")) &&
                    (client.getDescription().equals("abc"))) {
                    isOk = false;
                    break;
                }
            }

            if (!isOk) {
                org.junit.Assert.assertTrue(false);
            }
        }
    }
}
