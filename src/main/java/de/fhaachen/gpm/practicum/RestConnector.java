package de.fhaachen.gpm.practicum;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Map;

public class RestConnector implements JavaDelegate {


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String theUrl = (String) delegateExecution.getVariable("url");
        Map<String, String> theParameters = (Map<String, String>) delegateExecution.getVariable("parameters");
        String theMethod = (String) delegateExecution.getVariable("method");
        try {
            Client client = Client.create();

            theUrl = createUrlWithParams(theUrl, theParameters);

            WebResource webResource = client
                    .resource(theUrl);

            ClientResponse response = null;

            if ("get".equalsIgnoreCase(theMethod)) {
                response = webResource.accept("application/json")
                        .get(ClientResponse.class);
            } else if ("post".equalsIgnoreCase(theMethod)) {
                response = webResource.accept("application/json")
                        .post(ClientResponse.class);
            } else if ("put".equalsIgnoreCase(theMethod)) {
                response = webResource.accept("application/json")
                        .put(ClientResponse.class);
            } else {
                throw new IllegalStateException("Provided Method " + theMethod + " is not supported (yet).");
            }


            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private String createUrlWithParams(String anUrl, Map<String, String> someParameters) throws UnsupportedEncodingException {

        if (someParameters == null || someParameters != null && someParameters.isEmpty()) {
            return anUrl;
        }

        StringBuilder theBuilder = new StringBuilder();

        theBuilder.append(anUrl);
        theBuilder.append("?");

        for (Map.Entry<String, String> eachEntry : someParameters.entrySet()) {
            if (!theBuilder.toString().endsWith("?")) {
                theBuilder.append("&");
            }
            theBuilder.append(eachEntry.getKey());
            theBuilder.append(":");
            URLEncoder.encode(eachEntry.getValue(), "utf8");
        }

        return theBuilder.toString();
    }
}
