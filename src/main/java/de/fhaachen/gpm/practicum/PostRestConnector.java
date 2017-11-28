package de.fhaachen.gpm.practicum;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.camunda.bpm.engine.delegate.DelegateExecution;

public class PostRestConnector extends RestConnector {
    @Override
    protected ClientResponse execMethod(WebResource aResource) {
        return aResource.accept("application/json")
                .post(ClientResponse.class);
    }

    @Override
    protected String getUrl() {
        boolean theApprovedFlag = (Boolean) getDelegateExecution().getVariable("BachelorarbeitBestanden");

        if (theApprovedFlag) {
            return String.format("http://localhost:8000/approveThesis?id=%s",
                    getDelegateExecution().getVariable("ThesisId"));
        }

        return String.format("http://localhost:8000/rejectThesis?id=%s",
                getDelegateExecution().getVariable("ThesisId"));
    }
}
