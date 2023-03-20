package org.jhey.nsa.request;


import org.jhey.nsa.selenium.pages.LoginPage.Login;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.jhey.nsa.api.utils.ThreadUtils.sleep;

public class Requester {
   private final Login login;

   private static final String SCHEDULE_PAGE_URL = "https://nsa.cps.sp.gov.br/alunos/frmhorario.aspx";

   private static final Logger logger = Logger.getLogger(Requester.class.getName());

   public Requester(Login login) {
      this.login = login;
   }

   public Document getSchedulePage(){
      try {
         Connection connection = Jsoup.connect(SCHEDULE_PAGE_URL)
                 .cookie("NSA_OnLine_SessionId", login.tokenCookie)
                 .cookie("ARRAffinity", login.arrAffinityCookie)
                 .cookie("ARRAffinitySameSite", login.arrAffinitySameSiteCookie)
                 .method(Connection.Method.GET);

         //Need to parse because of Brazil chars such as "ç~éà"
         Document document = Jsoup.parse(new String(connection.execute().bodyAsBytes()), "UTF-8");

        //need to check this because sometimes the NSA website doesnt let me login with this token
         if(!document.body().getElementsByClass("MsoNormal").isEmpty()) {
            sleep(110); //This is used just to prevent some StackOverFlow Errors
            return getSchedulePage();
         }
         return document;

      }catch (IOException e){
         logger.log(Level.SEVERE, "Something went wrong when doing the request");
      }
      return null;
   }
}
