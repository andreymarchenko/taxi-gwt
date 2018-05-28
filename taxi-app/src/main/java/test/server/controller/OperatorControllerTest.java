package test.server.controller;

import com.taxi.shared.dto.OperatorDto;
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

import com.taxi.shared.dto.OperatorDto;

public class OperatorControllerTest {

    public int parseContent(String str, int startPos, List<OperatorDto> operators) {
        String subStr = str.substring(startPos);
        int pos = 0;
        ++pos; // skip '['

        OperatorDto operator = new OperatorDto();

        {
            String value = "";
            while (subStr.charAt(pos) != ',') {
                value = value + subStr.charAt(pos);
                ++pos;
            }
            ++pos; // skip ','
            operator.setID(Integer.parseInt(value));
        }

        {
            String value = "";
            while (subStr.charAt(pos) != ',') {
                value = value + subStr.charAt(pos);
                ++pos;
            }
            ++pos; // skip ','
            String subValue = value.substring(1, value.length() - 1);
            operator.setName(subValue);
        }

        {
            String value = "";
            while (subStr.charAt(pos) != ',') {
                value = value + subStr.charAt(pos);
                ++pos;
            }
            ++pos; // skip ','
            String subValue = value.substring(1, value.length() - 1);
            operator.setPassword(subValue);
        }

        {
            String value = "";
            while (subStr.charAt(pos) != ']') {
                value = value + subStr.charAt(pos);
                ++pos;
            }
            ++pos; // skip ']'
            String subValue = value.substring(1, value.length() - 1);
            operator.setDescription(subValue);
        }

        operators.add(operator);

        return pos;
    }

    public List<OperatorDto> getAllOperators() throws ClientProtocolException, IOException
    {
        HttpUriRequest request = new HttpGet(
                "http://127.0.0.1:8888/api/operator/get/all" );
        HttpResponse response =
                HttpClientBuilder.create().build().execute(request);
        HttpEntity entity = response.getEntity();
        String entityString = EntityUtils.toString(entity);
        entityString = entityString.substring(1, entityString.length() - 1);
        List<OperatorDto> operators = new ArrayList<OperatorDto>();
        int i = 0;
        while (i < entityString.length())
        {
            i += parseContent(entityString, i, operators);
            ++i; // skip ','
        }

        return operators;
    }

    public void createNewOperator() throws ClientProtocolException, IOException
    {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost request = new HttpPost("http://127.0.0.1:8888/api/operator/create");

        String params = "{\"name\":\"op1\",\"password\":\"op1\",\"description\":\"abc\"}";
        StringEntity entity = new StringEntity(params);
        request.setHeader("Accept", "application/json");
        request.setHeader("Content-type", "application/json");
        request.setEntity(entity);

        CloseableHttpResponse response = httpClient.execute(request);
        org.junit.Assert.assertEquals(response.getStatusLine().getStatusCode(),
                HttpStatus.SC_NO_CONTENT);
        httpClient.close();
    }

    public void deleteOperator(int ID) throws ClientProtocolException, IOException
    {
        HttpUriRequest request = new HttpGet(
                "http://127.0.0.1:8888/api/operator/delete/" +
                        Integer.toString(ID));
        HttpResponse response =
                HttpClientBuilder.create().build().execute(request);
        org.junit.Assert.assertEquals(response.getStatusLine().getStatusCode(),
                HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void GetAllOperators() throws ClientProtocolException, IOException {
        org.junit.Assert.assertNotEquals(null, getAllOperators());
    }

    @Test
    public void getOperator() throws ClientProtocolException, IOException {
        createNewOperator();
        // Find new operator
        int ID = 0;
        {
            List<OperatorDto> operators = getAllOperators();
            for (OperatorDto operator : operators) {
                if ((operator.getName().equals("op1")) &&
                        (operator.getPassword().equals("op1")) &&
                        (operator.getDescription().equals("abc"))) {
                    ID = operator.getID();
                    break;
                }
            }
        }

        HttpUriRequest request = new HttpGet("http://127.0.0.1:8888/api/operator/get/1");

        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        String clientStr = EntityUtils.toString(response.getEntity());

        org.junit.Assert.assertNotEquals(null, clientStr);

        deleteOperator(ID);
    }

    @Test
    public void createOperator() throws ClientProtocolException, IOException {
        createNewOperator();

        // Check of creating new operator
        List<OperatorDto> operators = getAllOperators();
        boolean isOk = false;
        int ID;
        for (OperatorDto operator : operators) {
            if((operator.getName().equals("op1")) &&
                    (operator.getPassword().equals("op1")) &&
                    (operator.getDescription().equals("abc")))
            {
                isOk = true;
                ID = operator.getID();

                // Deleting of new operator
                deleteOperator(ID);

                break;
            }
        }

        if(!isOk)
        {
            org.junit.Assert.assertTrue(false);
        }
    }

    @Test
    public void updateOperator() throws ClientProtocolException, IOException {
        createNewOperator();

        // Find new operator
        int ID = 0;
        {
            List<OperatorDto> operators = getAllOperators();
            for (OperatorDto operator : operators) {
                if ((operator.getName().equals("op1")) &&
                        (operator.getPassword().equals("op1")) &&
                        (operator.getDescription().equals("abc"))) {
                    ID = operator.getID();
                    break;
                }
            }
        }

        // Update operator
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost request = new HttpPost("http://127.0.0.1:8888/api/operator/update");

        String params = "{\"id\":\"" + Integer.toString(ID) + "\"," + "\"name\":\"op1\",\"password\":\"op1\",\"description\":\"cba\"}";
        StringEntity entity = new StringEntity(params);
        request.setHeader("Accept", "application/json");
        request.setHeader("Content-type", "application/json");
        request.setEntity(entity);

        CloseableHttpResponse response = httpClient.execute(request);
        org.junit.Assert.assertEquals(response.getStatusLine().getStatusCode(),
                HttpStatus.SC_NO_CONTENT);
        httpClient.close();

        // Check of updating new operator
        List<OperatorDto> operators = getAllOperators();
        boolean isOk = false;
        for (OperatorDto operator : operators) {
            if((operator.getName().equals("op1")) &&
                    (operator.getDescription().equals("cba")))
            {
                isOk = true;

                // Deleting of new operator
                deleteOperator(ID);

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
        createNewOperator();

        // Get id of new operator
        int ID = 0;
        {
            List<OperatorDto> operators = getAllOperators();
            for (OperatorDto operator : operators) {
                if ((operator.getName().equals("op1")) &&
                        (operator.getPassword().equals("op1")) &&
                        (operator.getDescription().equals("abc"))) {
                    ID = operator.getID();
                    break;
                }
            }
        }

        // Deleting of new operator
        deleteOperator(ID);

        // Check of deleting new operator
        {
            List<OperatorDto> operators = getAllOperators();
            boolean isOk = true;
            for (OperatorDto operator : operators) {
                if ((operator.getName().equals("op1")) &&
                        (operator.getPassword().equals("op1")) &&
                        (operator.getDescription().equals("abc"))) {
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