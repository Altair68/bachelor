package de.fhaachen.gpm.practicum;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.json.JSONArray;
import org.json.JSONObject;

public class StrawpollGetConnector extends RestConnectorBase {
    @Override
    protected ClientResponse execMethod(WebResource aResource) {
        ClientResponse theResponse = aResource.accept("application/json")
                .get(ClientResponse.class);

        final JSONObject theContent = new JSONObject(theResponse.getEntity(String.class));
        JSONArray theId = theContent.getJSONArray("votes");

        int theYesVotes = theId.getInt(0);
        int theNoVotes = theId.getInt(1);

        String theResult = null;
        if (theYesVotes > 0) {
            theResult = "yes";
        } else if (theNoVotes > 0) {
            theResult = "no";
        } else {
            theResult = "none";
        }

        getDelegateExecution().setVariable("strawpollVoted", theResult);
        return theResponse;
    }

    @Override
    protected String getUrl() {
        int theId = (Integer) getDelegateExecution().getVariable("strawpollId");
        return "http://localhost:4500/api/v2/polls" + "/" + theId;
    }
}
