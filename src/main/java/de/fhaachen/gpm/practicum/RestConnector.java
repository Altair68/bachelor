package de.fhaachen.gpm.practicum;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Deprecated
public class RestConnector implements JavaDelegate {


    @Override
    public void execute(DelegateExecution aDelegateExecution) throws Exception {
        String theUrl = (String) aDelegateExecution.getVariable("url");
        Map<String, String> theParameters = (Map<String, String>) aDelegateExecution.getVariable("parameters");
        Map<String, String> theResults = (Map<String, String>) aDelegateExecution.getVariable("results");
        String theMethod = (String) aDelegateExecution.getVariable("method");
        try {
            Client client = Client.create();

            theUrl = createUrlWithParams(theUrl, theParameters);

            WebResource webResource = client
                    .resource(theUrl);

            ClientResponse theResponse = null;

            if ("get".equalsIgnoreCase(theMethod)) {
                theResponse = webResource.accept("application/json")
                        .get(ClientResponse.class);
            } else if ("post".equalsIgnoreCase(theMethod)) {
                theResponse = webResource.accept("application/json")
                        .post(ClientResponse.class);
            } else if ("put".equalsIgnoreCase(theMethod)) {
                theResponse = webResource.accept("application/json")
                        .put(ClientResponse.class);
            } else {
                throw new IllegalStateException("Provided Method " + theMethod + " is not supported (yet).");
            }

            if (theResponse.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + theResponse.getStatus());
            }

            extractAndSetResults(aDelegateExecution, theResults, theResponse);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private void extractAndSetResults(DelegateExecution aDelegateExecution, Map<String, String> someResults, ClientResponse aResponse) {
        if (someResults == null || someResults != null && someResults.isEmpty()) {
            return;
        }

        String theResult = aResponse.getEntity(String.class);

        JSONObject theJsonObject = new JSONObject(theResult);
        Set<String> theKeySet = someResults.keySet();

        for (String eachKey : theKeySet) {
            String theJsonValue = theJsonObject.getString(eachKey);
            if (theJsonValue != null) {
                someResults.put(eachKey, theJsonValue);
            }
        }

        aDelegateExecution.setVariable("results", someResults);
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
