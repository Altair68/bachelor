package de.fhaachen.gpm.practicum;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public abstract class RestConnectorBase implements JavaDelegate {

    private DelegateExecution delegateExecution;

    @Override
    public void execute(DelegateExecution aDelegateExecution) throws Exception {
        this.delegateExecution = aDelegateExecution;
        try {
            Client client = Client.create();

            String theUrl = getUrl();
            WebResource webResource = client
                    .resource(theUrl);

            ClientResponse response = execMethod(webResource);

            if (response.getStatus() < 200 || response.getStatus() >= 300) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    protected DelegateExecution getDelegateExecution() {
        return delegateExecution;
    }

    protected abstract ClientResponse execMethod(WebResource aResource);

    protected abstract String getUrl();
}
