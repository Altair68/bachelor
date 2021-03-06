package de.fhaachen.gpm.practicum;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StrawpollCreateConnector extends RestConnectorBase {
    @Override
    protected ClientResponse execMethod(WebResource aResource) {

        LOGGER.info("Create Strawpoll");
        StringBuilder theInput = new StringBuilder();

        theInput.append("{\"title\": \"");
        SimpleDateFormat theDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        theInput.append(theDateFormat.format((Date) getDelegateExecution().getVariable("pollTitle")));
        theInput.append("\", \"options\": [\"Ja\", \"Nein\"] }");

        LOGGER.info(getDelegateExecution().getVariable("pollTitle").toString());
        LOGGER.info(theInput.toString());

        ClientResponse theResponse = aResource.accept("application/json")
                .type("application/json").post(ClientResponse.class, theInput.toString());

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
