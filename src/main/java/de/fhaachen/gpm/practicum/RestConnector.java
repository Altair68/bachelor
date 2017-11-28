package de.fhaachen.gpm.practicum;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.net.HttpURLConnection;
import java.net.URL;

public class RestConnector implements JavaDelegate {

    URL url;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        try {
            Client client = Client.create();

            WebResource webResource = client
                    .resource("http://localhost:8000/insertThesis?student_id=1&title=Test&supervisor=Fassbender");

            ClientResponse response = webResource.accept("application/json")
                    .put(ClientResponse.class);

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

            String output = response.getEntity(String.class);

            System.out.println("Output from Server .... \n");
            System.out.println(output);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
}
