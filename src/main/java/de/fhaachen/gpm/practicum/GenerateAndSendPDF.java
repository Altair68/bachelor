package de.fhaachen.gpm.practicum;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.io.FileOutputStream;
import java.util.logging.Logger;

public class GenerateAndSendPDF implements JavaDelegate {
    protected final Logger LOGGER = Logger.getLogger(RestConnectorBase.class.getName());

    public void execute(DelegateExecution execution) throws Exception {
        LOGGER.info("Generate PDF");
        String filename = "/home/gpm17/auszeichnung.pdf";

        String name = (String)execution.getVariable("StudentName");
        String note = execution.getVariable("FinalGrade").toString();

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filename));
        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        //Chunk chunk = new Chunk(content, font);
        //document.add(chunk);
        Paragraph pdfContent = new Paragraph();
        pdfContent.add(new Paragraph("Auszeichnung Bachelor Informatik", font));
        pdfContent.add(new Paragraph(" "));
        pdfContent.add(new Paragraph("Der Student", font));
        pdfContent.add(new Paragraph(" "));
        pdfContent.add(new Paragraph(name, font));
        pdfContent.add(new Paragraph(" "));
        pdfContent.add(new Paragraph("hat mit der Gesamtnote: " + note, font));
        pdfContent.add(new Paragraph(" "));
        pdfContent.add(new Paragraph("mit Auszeichnung bestanden.", font));
        document.add(pdfContent);

        document.close();

        execution.setVariable("Attachement", filename);
        MailDelegate sendMail = new MailDelegate();
        sendMail.execute(execution);
    }
}
