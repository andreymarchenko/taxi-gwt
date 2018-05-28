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

import com.taxi.shared.dto.DriverDto;
import com.taxi.shared.dto.StatusDto;

public class DriverControllerTest {

    public static int parseDriver(String str, int startPos, DriverDto driver) {
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
            driver.setId(Integer.parseInt(value));
        }

        {
            String value = "";
            while (subStr.charAt(pos) != ',') {
                value = value + subStr.charAt(pos);
                ++pos;
            }
            ++pos; // skip ','
            String subValue = value.substring(1, value.length() - 1);
            driver.setName(subValue);
        }

        {
            String value = "";
            while (subStr.charAt(pos) != ',') {
                value = value + subStr.charAt(pos);
                ++pos;
            }
            ++pos; // skip ','
            String subValue = value.substring(1, value.length() - 1);
            driver.setPassword(subValue);
        }

        {
            String value = "";
            while (subStr.charAt(pos) != ',') {
                value = value + subStr.charAt(pos);
                ++pos;
            }
            ++pos; // skip ','
            String subValue = value.substring(1, value.length() - 1);
            driver.setDescription(subValue);
        }

        {
            String value = "";
            while (subStr.charAt(pos) != ',') {
                value = value + subStr.charAt(pos);
                ++pos;
            }
            ++pos; // skip ','
            String subValue = value.substring(1, value.length() - 1);
            driver.setCar(subValue);
        }

        {
            String value = "";
            while (subStr.charAt(pos) != ']') {
                value = value + subStr.charAt(pos);
                ++pos;
            }
            ++pos; // skip ']'
            String subValue = value.substring(1, value.length() - 1);
            if (subValue == "FREE") {
                driver.setStatus(StatusDto.FREE);
            }
            else {
                driver.setStatus(StatusDto.BUSY);
            }
        }

        return pos;
    }

    public static List<DriverDto> getAllDrivers() throws ClientProtocolException, IOException
    {
        HttpUriRequest request = new HttpGet(
                "http://127.0.0.1:8888/api/driver/get/all" );
        HttpResponse response =
                HttpClientBuilder.create().build().execute(request);
        HttpEntity entity = response.getEntity();
        String entityString = EntityUtils.toString(entity);
        entityString = entityString.substring(1, entityString.length() - 1);
        List<DriverDto> drivers = new ArrayList<DriverDto>();
        int i = 0;
        while (i < entityString.length())
        {
            DriverDto driver = new DriverDto();
            i += parseDriver(entityString, i, driver);
            drivers.add(driver);
            ++i; // skip ','
        }

        return drivers;
    }

    public void createNewDriver() throws ClientProtocolException, IOException
    {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost request = new HttpPost("http://127.0.0.1:8888/api/driver/create");

        String params = "{\"name\":\"dr1\",\"password\":\"dr1\",\"description\":\"abc\",\"car\":\"mycar\",\"status\":\"" + StatusDto.FREE + "\"}";
        StringEntity entity = new StringEntity(params);
        request.setHeader("Accept", "application/json");
        request.setHeader("Content-type", "application/json");
        request.setEntity(entity);

        CloseableHttpResponse response = httpClient.execute(request);
        org.junit.Assert.assertEquals(response.getStatusLine().getStatusCode(),
                HttpStatus.SC_NO_CONTENT);
        httpClient.close();
    }

    public void deleteDriver(int ID) throws ClientProtocolException, IOException
    {
        HttpUriRequest request = new HttpGet(
                "http://127.0.0.1:8888/api/driver/delete/" +
                        Integer.toString(ID));
        HttpResponse response =
                HttpClientBuilder.create().build().execute(request);
        org.junit.Assert.assertEquals(response.getStatusLine().getStatusCode(),
                HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void GetAllDrivers() throws ClientProtocolException, IOException {
        org.junit.Assert.assertNotEquals(null, getAllDrivers());
    }

    @Test
    public void getDriver() throws ClientProtocolException, IOException {
        createNewDriver();

        // Find new driver
        int ID = 0;
        {
            List<DriverDto> drivers = getAllDrivers();
            for (DriverDto driver : drivers) {
                if ((driver.getName().equals("dr1")) &&
                        (driver.getPassword().equals("dr1")) &&
                        (driver.getDescription().equals("abc"))) {
                    ID = driver.getId();
                    break;
                }
            }
        }

        HttpUriRequest request = new HttpGet("http://127.0.0.1:8888/api/driver/get/1");

        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        String clientStr = EntityUtils.toString(response.getEntity());

        org.junit.Assert.assertNotEquals(null, clientStr);

        deleteDriver(ID);
    }

    @Test
    public void createDriver() throws ClientProtocolException, IOException {
        createNewDriver();

        // Check of creating new driver
        List<DriverDto> drivers = getAllDrivers();
        boolean isOk = false;
        int ID;
        for (DriverDto driver : drivers) {
            if((driver.getName().equals("dr1")) &&
                    (driver.getPassword().equals("dr1")) &&
                    (driver.getDescription().equals("abc")))
            {
                isOk = true;
                ID = driver.getId();

                // Deleting of new driver
                deleteDriver(ID);

                break;
            }
        }

        if(!isOk)
        {
            org.junit.Assert.assertTrue(false);
        }
    }

    @Test
    public void updateDriver() throws ClientProtocolException, IOException {
        createNewDriver();

        // Find new driver
        int ID = 0;
        {
            List<DriverDto> drivers = getAllDrivers();
            for (DriverDto driver : drivers) {
                if ((driver.getName().equals("dr1")) &&
                        (driver.getPassword().equals("dr1")) &&
                        (driver.getDescription().equals("abc"))) {
                    ID = driver.getId();
                    break;
                }
            }
        }

        // Update driver
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost request = new HttpPost("http://127.0.0.1:8888/api/driver/update");

        String params = "{\"id\":\"" + Integer.toString(ID) + "\"," + "\"name\":\"dr1\",\"password\":\"dr1\",\"description\":\"cba\",\"car\":\"racym\",\"status\":\"" + StatusDto.BUSY + "\"}";
        StringEntity entity = new StringEntity(params);
        request.setHeader("Accept", "application/json");
        request.setHeader("Content-type", "application/json");
        request.setEntity(entity);

        CloseableHttpResponse response = httpClient.execute(request);
        org.junit.Assert.assertEquals(response.getStatusLine().getStatusCode(),
                HttpStatus.SC_NO_CONTENT);
        httpClient.close();

        // Check of updating new driver
        List<DriverDto> drivers = getAllDrivers();
        boolean isOk = false;
        for (DriverDto driver : drivers) {
            if((driver.getName().equals("dr1")) &&
                    (driver.getDescription().equals("cba")) &&
                    (driver.getCar().equals("racym")) &&
                    (driver.getStatus().equals(StatusDto.BUSY)))
            {
                isOk = true;

                // Deleting of new driver
                deleteDriver(ID);

                break;
            }
        }

        if(!isOk)
        {
            org.junit.Assert.assertTrue(false);
        }
    }

    @Test
    public void delete() throws ClientProtocolException, IOException {
        createNewDriver();

        // Get id of new driver
        int ID = 0;
        {
            List<DriverDto> drivers = getAllDrivers();
            for (DriverDto driver : drivers) {
                if ((driver.getName().equals("dr1")) &&
                        (driver.getPassword().equals("dr1")) &&
                        (driver.getDescription().equals("abc"))) {
                    ID = driver.getId();
                    break;
                }
            }
        }

        // Deleting of new driver
        deleteDriver(ID);

        // Check of deleting new driver
        {
            List<DriverDto> drivers = getAllDrivers();
            boolean isOk = true;
            for (DriverDto driver : drivers) {
                if ((driver.getName().equals("dr1")) &&
                        (driver.getPassword().equals("dr1")) &&
                        (driver.getDescription().equals("abc"))) {
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