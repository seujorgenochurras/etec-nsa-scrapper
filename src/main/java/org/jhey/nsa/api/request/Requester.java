package org.jhey.nsa.api.request;

import org.jhey.nsa.selenium.pages.LoginPage.Login;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.ParseSettings;
import org.jsoup.parser.Parser;

import java.io.IOException;
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
         return Jsoup.connect("http://127.0.0.1:5500/nsa.html")
                 .get();
//         return Jsoup.connect(SCHEDULE_PAGE_URL)
//                 .cookie("NSA_OnLine_SessionId", login.getToken())
//                 .cookie("ARRAffinitySameSite", login.getARRAffinitySameSite())
//                 .cookie("ARRAffinity", login.getARRAffinity())
//                 .get();
      }catch (IOException e){
         Logger logger = Logger.getLogger(Requester.class.getName());
         logger.log(Level.SEVERE, "Something went wrong when doing the request");
         throw e;
      }
   }
}
