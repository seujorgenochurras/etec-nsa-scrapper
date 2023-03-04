package org.jhey.nsa.request;

import org.jhey.nsa.selenium.pages.LoginPage.Login;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Requester {
   private final Login login;
   private static final String SCHEDULE_PAGE_URL = "https://nsa.cps.sp.gov.br/alunos/frmhorario.aspx";

   public Requester(Login login) {
      this.login = login;
   }

   public Document getSchedulePage() throws IOException {
      try {
         Connection connection = Jsoup.connect("http://127.0.0.1:5500/nsa.html")
//                 .cookie("NSA_OnLine_SessionId", login.getToken())
//                 .cookie("ARRAffinitySameSite", login.getARRAffinitySameSite())
//                 .cookie("ARRAffinity", login.getARRAffinity())
                 .method(Connection.Method.GET);
         return Jsoup.parse(new String(connection.execute().bodyAsBytes()), "UTF-8");

      }catch (IOException e){
         Logger logger = Logger.getLogger(Requester.class.getName());
         logger.log(Level.SEVERE, "Something went wrong when doing the request");
         throw e;
      }
   }
}
