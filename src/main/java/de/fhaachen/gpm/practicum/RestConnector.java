package de.fhaachen.gpm.practicum;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.net.HttpURLConnection;
import java.net.URL;

public class RestConnector implements JavaDelegate {

    URL url;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        //url = new URL((String) delegateExecution.getVariable("url"));
        url = new URL("http://localhost:8000/insertThesis?student_id=1&title=Test&supervisor=Fassbender");
        //String theMethod = (String) delegateExecution.getVariable("method");
        String theMethod = "PUT";
        HttpURLConnection theConnection = (HttpURLConnection)  url.openConnection();
        theConnection.setRequestMethod(theMethod);
        theConnection.connect();

    }
}
