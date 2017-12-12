package de.fhaachen.gpm.practicum;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class StudentGetConnector extends RestConnectorBase {
    @Override
    protected ClientResponse execMethod(WebResource aResource) {
        ClientResponse theResponse = aResource.accept("application/json")
                .get(ClientResponse.class);

        final String theContent = theResponse.getEntity(String.class);
        LOGGER.info("Name des Studenten: " + theContent);
        getDelegateExecution().setVariable("StudentName", theContent);
        return theResponse;
    }

    @Override
    protected String getUrl() {
        long theId = (Long) getDelegateExecution().getVariable("StudentId");
        return "http://localhost:8000/studentName?id=" + theId;
    }
}
