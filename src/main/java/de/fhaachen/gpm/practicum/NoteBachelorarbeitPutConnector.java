package de.fhaachen.gpm.practicum;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class NoteBachelorarbeitPutConnector extends RestConnectorBase {

    @Override
    protected ClientResponse execMethod(WebResource aResource) {
        LOGGER.info("Save bachelor thesis grade");
        ClientResponse theResponse = aResource.accept("application/json")
                .put(ClientResponse.class);

        return theResponse;
    }

    @Override
    protected String getUrl() {
        return String.format("http://localhost:8000/insertBach?student_id=%s&grade=%s",
                getDelegateExecution().getVariable("StudentId"),
                ((String) getDelegateExecution().getVariable("NoteBachelorarbeit")).substring(1));
    }
}
