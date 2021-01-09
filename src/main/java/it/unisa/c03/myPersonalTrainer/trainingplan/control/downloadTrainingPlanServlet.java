package it.unisa.c03.myPersonalTrainer.trainingplan.control;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import it.unisa.c03.myPersonalTrainer.account.bean.Account;
import it.unisa.c03.myPersonalTrainer.account.dao.AccountDAO;
import it.unisa.c03.myPersonalTrainer.account.dao.AccountDAOImpl;
import it.unisa.c03.myPersonalTrainer.account.service.AccountService;
import it.unisa.c03.myPersonalTrainer.account.service.AccountServiceImpl;
import it.unisa.c03.myPersonalTrainer.trainingplan.bean.TrainingPlan;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * this class enable to download a training plan in pdf.
 */
@WebServlet(name = "DownloadTrainingPlanServlet",
        value = "/download-training-plan")
public class DownloadTrainingPlanServlet extends
        HttpServlet {
    private static final
    long serialVersionUID = 1L;

    Document doc;

    public DownloadTrainingPlanServlet(Document document) {
        this.doc = document;
    }

    public DownloadTrainingPlanServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException, ServletException {
        String date = request.getParameter("date");

        String emailCliente = (String) request.getSession().getAttribute("clienteMail");

        String exercises = request.getParameter("exercises");


        try {
            doc = new Document();
            ByteArrayOutputStream baos =
                    new ByteArrayOutputStream();
            PdfWriter.getInstance(doc, baos);
            doc.open();

            /*
            String filename =
                    "https://raw.githubusercontent.com/imgios/mypersonaltrainer/main/.github/logo.png";
            */
            /*
            * inserimento del logo più piccolo di dimensione. vedere se il link cambia quando si passa in main, nel caso aggiornare url immagine
             */
            String filename = "https://github.com/imgios/mypersonaltrainer/blob/develop/src/main/webapp/img/logo_fattura.png?raw=true";

            Image image = Image.getInstance(filename);
            image.setAlignment(Element.ALIGN_CENTER);
            doc.add(image);

            Paragraph p = new Paragraph(
                    "Scheda di allenamento assegnata il " + date);
            p.setAlignment(Element.ALIGN_CENTER);
            doc.add(p);

            p = new Paragraph(
                    "Al cliente " + emailCliente);
            p.setAlignment(Element.ALIGN_CENTER);
            doc.add(p);

            doc.add(new Paragraph(" "));
            p = new Paragraph(
                    "La tua Scheda\n " + exercises);
            p.setAlignment(Element.ALIGN_CENTER);
            doc.add(p);


            p = new Paragraph(
                    "\nBUON LAVORO!");
            p.setAlignment(Element.ALIGN_RIGHT);
            doc.add(p);

            doc.close();

            response.setHeader(
                    "Expires", "0");
            response.setHeader(
                    "Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader(
                    "Pragma", "public");

            response.setContentType("application/pdf");

            response.setContentLength(baos.size());

            ServletOutputStream out =
                    response.getOutputStream();
            baos.writeTo(out);
            out.flush();
            out.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
