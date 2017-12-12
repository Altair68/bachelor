package de.fhaachen.gpm.practicum;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.json.JSONObject;

public class StrawpollCreateConnector extends RestConnectorBase {
    @Override
    protected ClientResponse execMethod(WebResource aResource) {

        StringBuilder theInput = new StringBuilder();

        theInput.append("{\"title\": \"");
        theInput.append((String) getDelegateExecution().getVariable("pollTitle"));
        theInput.append("\", \"options\": [\"Ja\", \"Nein\"] }");

        ClientResponse theResponse = aResource.accept("application/json")
                .post(ClientResponse.class, theInput.toString());

        final JSONObject theContent = new JSONObject(theResponse.getEntity(String.class));
        int theId = theContent.getInt("id");

        getDelegateExecution().setVariable("strawpollId", theId);
        return theResponse;
    }

    @Override
    protected String getUrl() {
        return "http://localhost:4500/api/v2/polls";
    }
}
