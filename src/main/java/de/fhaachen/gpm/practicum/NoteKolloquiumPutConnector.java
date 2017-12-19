package de.fhaachen.gpm.practicum;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class NoteKolloquiumPutConnector extends RestConnectorBase {

    @Override
    protected ClientResponse execMethod(WebResource aResource) {
        ClientResponse theResponse = aResource.accept("application/json")
                .put(ClientResponse.class);

        return theResponse;
    }

    @Override
    protected String getUrl() {
        return String.format("http://localhost:8000/insertColl?student_id=%s&grade=%s",
                getDelegateExecution().getVariable("StudentId"),
                getDelegateExecution().getVariable("NoteKolloquium"));
    }
}
