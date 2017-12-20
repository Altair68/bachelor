package de.fhaachen.gpm.practicum;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class PostRestConnector extends RestConnectorBase {
    @Override
    protected ClientResponse execMethod(WebResource aResource) {
        LOGGER.info("Change Thesis");
        return aResource.accept("application/json")
                .post(ClientResponse.class);
    }

    @Override
    protected String getUrl() {
        boolean theApprovedFlag = Boolean.parseBoolean((String) getDelegateExecution().getVariable("ZulassungOk"));

        if (theApprovedFlag) {
            return String.format("http://localhost:8000/approveThesis?id=%s",
                    getDelegateExecution().getVariable("ThesisId"));
        }

        return String.format("http://localhost:8000/rejectThesis?id=%s",
                getDelegateExecution().getVariable("ThesisId"));
    }
}
