package de.fhaachen.gpm.practicum;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.json.JSONArray;
import org.json.JSONObject;

public class FinalGradeGetConnector extends RestConnectorBase {
    @Override
    protected ClientResponse execMethod(WebResource aResource) {
        ClientResponse theResponse = aResource.accept("application/json")
                .get(ClientResponse.class);

        final String theResult = theResponse.getEntity(String.class);

        getDelegateExecution().setVariable("FinalGrade", theResult);
        return theResponse;
    }

    @Override
    protected String getUrl() {
        return String.format("http://localhost:8000/finalGrade?student_id=%s",
                getDelegateExecution().getVariable("StudentId"));
    }
}
