package de.fhaachen.gpm.practicum;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class FinalGradeGetConnector extends RestConnectorBase {
    @Override
    protected ClientResponse execMethod(WebResource aResource) {
        LOGGER.info("Load final grade");
        ClientResponse theResponse = aResource.accept("application/json")
                .get(ClientResponse.class);

        final String theResult = theResponse.getEntity(String.class);
        double finalGrade = Double.parseDouble(theResult.replace("\"", "").trim());

        getDelegateExecution().setVariable("FinalGrade", finalGrade);
        return theResponse;
    }

    @Override
    protected String getUrl() {
        return String.format("http://localhost:8000/finalGrade?student_id=%s",
                getDelegateExecution().getVariable("StudentId"));
    }
}
