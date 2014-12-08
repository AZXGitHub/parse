package com.chipcerio.parse;

import static java.net.HttpURLConnection.HTTP_OK;
import static java.net.HttpURLConnection.HTTP_CREATED;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Request {
    private static final String BASE_URL = "https://api.parse.com";
    private static final String API_VER = "/1";

    private String mResource;

    interface Header {
        String APP_ID  = "X-Parse-Application-Id";
        String REST_ID = "X-Parse-REST-API-Key";
    }

    interface Method {
        String GET    = "GET";
        String POST   = "POST";
        String PUT    = "PUT";
        String DELETE = "DELETE";
    }

    /**
     * should be /classes/Event/objectId
     * @param resource
     */
    public Request(String resource) {
        mResource = resource;
    }

    public Response get() {
        HttpURLConnection connection = null;
        InputStreamReader inputStream = null;
        BufferedReader reader = null;
        Response response = null;
        try {
            URL url = new URL(BASE_URL + API_VER + mResource);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(Method.GET);
            connection.setRequestProperty(Header.APP_ID, Common.AppKey.APP_ID);
            connection.setRequestProperty(Header.REST_ID, Common.AppKey.REST_API_KEY);
            connection.setDoInput(true);
            connection.connect();

            int statusCode = connection.getResponseCode();

            inputStream = new InputStreamReader(connection.getInputStream());
            reader = new BufferedReader(inputStream);
            StringBuffer buffer = new StringBuffer();
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            response = new Response();
            response.setMessageBody(buffer.toString());
            response.setStatusCode(statusCode);

            if (statusCode == HTTP_OK || statusCode == HTTP_CREATED) {
                response.setSuccess(true);
            } else {
                response.setSuccess(false);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) connection.disconnect();
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return response;
    }

}
