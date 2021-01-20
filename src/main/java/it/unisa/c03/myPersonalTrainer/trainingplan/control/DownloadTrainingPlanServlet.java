package it.unisa.c03.myPersonalTrainer.trainingplan.control;

import java.io.ByteArrayOutputStream;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;


/**
 * this class enable to download a training plan in pdf.
 */

@WebServlet(name = "DownloadSchedaServlet", value = "/DownloadSchedaServlet")
public class DownloadTrainingPlanServlet extends HttpServlet {

  private Document doc;

  @Override
  protected void doPost(HttpServletRequest request,
      HttpServletResponse response)
      throws IOException, ServletException {
    String date = request.getParameter("date");

    String emailCliente = (String)
            request.getSession().getAttribute("clienteMail");

    String exercises = request.getParameter("exercises");

    try {
      doc = new Document();
      ByteArrayOutputStream baos =
          new ByteArrayOutputStream();
      PdfWriter.getInstance(doc, baos);
      doc.open();

      String filename = "https://github.com/imgios/mypersonaltrainer/blob"
          + "/main/src/main/webapp/img/logo_fattura.png?raw=true";

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

  @Override
  protected void doGet(
      HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doPost(request, response);
  }
}
