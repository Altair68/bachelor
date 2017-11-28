package de.fhaachen.gpm.practicum;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class PutRestConnector extends RestConnector {
    @Override
    protected ClientResponse execMethod(WebResource aResource) {
        ClientResponse theResponse = aResource.accept("application/json")
                .put(ClientResponse.class);

        final String theContent = theResponse.getEntity(String.class);
        getDelegateExecution().setVariable("ThesisId", theContent);
        return theResponse;
    }

    @Override
    protected String getUrl() {
        return String.format("http://localhost:8000/insertThesis?student_id=%s&title=%s&supervisor=%s",
                getDelegateExecution().getVariable("StudentId"),
                getDelegateExecution().getVariable("Thema"),
                getDelegateExecution().getVariable("Professor"));
    }
}
