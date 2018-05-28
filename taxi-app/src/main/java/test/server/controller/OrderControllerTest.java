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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import test.server.controller.DriverControllerTest;
import test.server.controller.ClientControllerTest;
import org.junit.Test;

import com.taxi.shared.dto.OrderDto;
import com.taxi.shared.dto.DriverDto;
import com.taxi.shared.dto.ClientDto;
import com.taxi.shared.dto.StatusDto;
import java.util.Date;
import java.util.Locale;

public class OrderControllerTest {

    public static int parseOrder(String str, int startPos, OrderDto order) throws ParseException {
        String subStr = str.substring(startPos);
        int pos = 0;
        ++pos; // skip '['

        DriverDto driver = new DriverDto();
        pos += DriverControllerTest.parseDriver(subStr, pos, driver);
        order.setDriver(driver);
        ++pos; // skip ','

        ClientDto client = new ClientDto();
        pos += ClientControllerTest.parseClient(subStr, pos, client);
        order.setClient(client);
        ++pos; // skip ','

        {
            String value = "";
            while (subStr.charAt(pos) != ',') {
                value = value + subStr.charAt(pos);
                ++pos;
            }
            ++pos; // skip ','
            String subValue = value.substring(1, value.length() - 1);
            order.setStart(subValue);
        }

        {
            String value = "";
            while (subStr.charAt(pos) != ',') {
                value = value + subStr.charAt(pos);
                ++pos;
            }
            ++pos; // skip ','
            String subValue = value.substring(1, value.length() - 1);
            order.setFinish(subValue);
        }

        {
            String value = "";
            while (subStr.charAt(pos) != ',') {
                value = value + subStr.charAt(pos);
                ++pos;
            }
            ++pos; // skip ','
            String subValue = value.substring(1, value.length() - 1);
            DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
            Date date = format.parse(subValue);
            order.setTimestamp(date);
        }

        {
            String value = "";
            while (subStr.charAt(pos) != ',') {
                value = value + subStr.charAt(pos);
                ++pos;
            }
            ++pos; // skip ','
            String subValue = value.substring(1, value.length() - 1);
            order.setPrice(Double.parseDouble(subValue));
        }

        {
            String value = "";
            while (subStr.charAt(pos) != ']') {
                value = value + subStr.charAt(pos);
                ++pos;
            }
            ++pos; // skip ']'
            String subValue = value.substring(1, value.length() - 1);
            order.setPaymentType(subValue);
        }

        return pos;
    }

    public static List<OrderDto> getOrdersByUser(String userType, int userID) throws ClientProtocolException, IOException, ParseException {
        // userType values: "driver", "client"
        HttpUriRequest request = new HttpGet(
                "http://127.0.0.1:8888/api/order/" + userType + "/" + Integer.toString(userID));
        HttpResponse response =
                HttpClientBuilder.create().build().execute(request);
        HttpEntity entity = response.getEntity();
        String entityString = EntityUtils.toString(entity);
        entityString = entityString.substring(1, entityString.length() - 1);
        List<OrderDto> orders = new ArrayList<OrderDto>();
        int i = 0;
        while (i < entityString.length())
        {
            OrderDto order = new OrderDto();
            i += parseOrder(entityString, i, order);
            orders.add(order);
            ++i; // skip ','
        }

        return orders;
    }

    public String convertDriverDtoToJSONString(DriverDto driver)
    {
        String driverStr = "";

        driverStr += "{";
        driverStr += "\"id\":\"" + driver.getId() + "\",";
        driverStr += "\"name\":\"" + driver.getName() + "\",";
        driverStr += "\"password\":\"" + driver.getPassword() + "\",";
        driverStr += "\"description\":\"" + driver.getDescription() + "\",";
        driverStr += "\"car\":\"" + driver.getCar() + "\",";
        driverStr += "\"status\":\"" + driver.getStatus() + "\"";
        driverStr += "}";

        return driverStr;
    }

    public String convertClientDtoToJSONString(ClientDto client)
    {
        String clientStr = "";

        clientStr += "{";
        clientStr += "\"id\":\"" + client.getId() + "\",";
        clientStr += "\"name\":\"" + client.getName() + "\",";
        clientStr += "\"password\":\"" + client.getPassword() + "\",";
        clientStr += "\"description\":\"" + client.getDescription() + "\"";
        clientStr += "}";

        return clientStr;
    }

    public String convertOrderDtoContentToJSONString(OrderDto order)
    {
        String orderStr = "";

        orderStr += "{";
        orderStr += "\"id\":\"" + order.getId() + "\",";
        orderStr += "\"driver\":" + convertDriverDtoToJSONString(order.getDriver()) + ",";
        orderStr += "\"client\":" + convertClientDtoToJSONString(order.getClient()) + ",";
        orderStr += "\"start\":\"" + order.getStart() + "\",";
        orderStr += "\"finish\":\"" + order.getFinish() + "\",";
        orderStr += "\"timestamp\":\"" + order.getTimestamp().toString() + "\",";
        orderStr += "\"price\":\"" + Double.toString(order.getPrice()) + "\",";
        orderStr += "\"paymentType\":\"" + order.getPaymentType() + "\"";
        orderStr += "}";

        return orderStr;
    }

    public int createFakeDriver(DriverDto driver) throws ClientProtocolException, IOException
    {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost request = new HttpPost("http://127.0.0.1:8888/api/driver/create");

        String params = convertDriverDtoToJSONString(driver);
        StringEntity entity = new StringEntity(params);
        request.setHeader("Accept", "application/json");
        request.setHeader("Content-type", "application/json");
        request.setEntity(entity);

        CloseableHttpResponse response = httpClient.execute(request);
        org.junit.Assert.assertEquals(response.getStatusLine().getStatusCode(),
                HttpStatus.SC_NO_CONTENT);
        httpClient.close();

        // Find id of new driver
        int ID = 0;
        {
            List<DriverDto> drivers = DriverControllerTest.getAllDrivers();
            for (DriverDto driverEl : drivers) {
                if (driverEl.getName().equals(driver.getName())) {
                    ID = driverEl.getId();
                    break;
                }
            }
        }

        return ID;
    }

    public int createFakeClient(ClientDto client) throws ClientProtocolException, IOException
    {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost request = new HttpPost("http://127.0.0.1:8888/api/client/create");

        String params = convertClientDtoToJSONString(client);
        StringEntity entity = new StringEntity(params);
        request.setHeader("Accept", "application/json");
        request.setHeader("Content-type", "application/json");
        request.setEntity(entity);

        CloseableHttpResponse response = httpClient.execute(request);
        org.junit.Assert.assertEquals(response.getStatusLine().getStatusCode(),
                HttpStatus.SC_OK);
        httpClient.close();

        // Find id of new client
        int ID = 0;
        {
            List<ClientDto> clients = ClientControllerTest.getAllClients();
            for (ClientDto clientEl : clients) {
                if (clientEl.getName().equals(client.getName())) {
                    ID = clientEl.getId();
                    break;
                }
            }
        }

        return ID;
    }

    public int createFakeOrder(OrderDto order) throws ClientProtocolException, IOException, ParseException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost request = new HttpPost("http://127.0.0.1:8888/api/order/create");

        String params = convertOrderDtoContentToJSONString(order);
        StringEntity entity = new StringEntity(params);
        request.setHeader("Accept", "application/json");
        request.setHeader("Content-type", "application/json");
        request.setEntity(entity);

        CloseableHttpResponse response = httpClient.execute(request);
        org.junit.Assert.assertEquals(response.getStatusLine().getStatusCode(),
                HttpStatus.SC_NO_CONTENT);
        httpClient.close();

        // Find id of new order
        int ID = 0;
        {
            List<OrderDto> orders = getOrdersByUser("client", order.getClient().getId());
            for (OrderDto orderEl : orders) {
                if ((orderEl.getDriver().getId() == order.getDriver().getId()) &&
                        (orderEl.getClient().getId() == order.getClient().getId()) &&
                        ((orderEl.getStart().equals(order.getStart())) &&
                        (orderEl.getFinish().equals(order.getFinish())))) {
                    ID = orderEl.getId();
                    break;
                }
            }
        }

        return ID;
    }

    public void deleteFakeOrder(OrderDto order) throws IOException
    {
        {
            HttpUriRequest request = new HttpGet(
                    "http://127.0.0.1:8888/api/order/delete/" +
                            Integer.toString(order.getId()));
            HttpResponse response =
                    HttpClientBuilder.create().build().execute(request);
            org.junit.Assert.assertEquals(response.getStatusLine().getStatusCode(),
                    HttpStatus.SC_NO_CONTENT);
        }

        {
            HttpUriRequest request = new HttpGet(
                    "http://127.0.0.1:8888/api/client/delete/" +
                            Integer.toString(order.getClient().getId()));
            HttpResponse response =
                    HttpClientBuilder.create().build().execute(request);
            org.junit.Assert.assertEquals(response.getStatusLine().getStatusCode(),
                    HttpStatus.SC_NO_CONTENT);
        }

        {
            HttpUriRequest request = new HttpGet(
                    "http://127.0.0.1:8888/api/driver/delete/" +
                            Integer.toString(order.getDriver().getId()));
            HttpResponse response =
                    HttpClientBuilder.create().build().execute(request);
            org.junit.Assert.assertEquals(response.getStatusLine().getStatusCode(),
                    HttpStatus.SC_NO_CONTENT);
        }
    }

    public void createTestOrder(OrderDto order) throws IOException, ParseException {
        ClientDto client = new ClientDto();
        client.setName("cl1");
        client.setPassword("cl1");
        client.setDescription("abc");
        int clientID = createFakeClient(client);
        client.setId(clientID);

        DriverDto driver = new DriverDto();
        driver.setName("dr1");
        driver.setPassword("dr1");
        driver.setDescription("abc");
        driver.setCar("mycar");
        driver.setStatus(StatusDto.FREE);
        int driverID = createFakeDriver(driver);
        driver.setId(driverID);

        order.setDriver(driver);
        order.setClient(client);
        order.setStart("teststart");
        order.setFinish("testfinish");
        order.setTimestamp(new Date());
        order.setPrice(100.5);
        order.setPaymentType("CASH");
        int orderID = createFakeOrder(order);
        order.setID(orderID);
    }

    @Test
    public void createOrder() throws IOException, ParseException {
        OrderDto order = new OrderDto();

        createTestOrder(order);

        deleteFakeOrder(order);
    }

    @Test
    public void getOrder() throws IOException, ParseException {
        OrderDto order = new OrderDto();

        createTestOrder(order);




    }

    @Test
    public void getOrdersByClient() throws IOException {
    }

    @Test
    public void getOrdersByDriver() throws IOException {
    }

    @Test
    public void getFreeOrders() throws IOException {
    }

    @Test
    public void startOrder() throws IOException {
    }
}