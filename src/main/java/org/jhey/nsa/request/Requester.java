package org.jhey.nsa.request;

import org.jhey.nsa.selenium.pages.LoginPage.Login;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Requester {
   private final Login login;
   //private static final String SCHEDULE_PAGE_URL = "https://nsa.cps.sp.gov.br/alunos/frmhorario.aspx";
   private static final String SCHEDULE_PAGE_URL = "http://127.0.0.1:5500/nsa.html";

   private static final Logger logger = Logger.getLogger(Requester.class.getName());

   public Requester(Login login) {
      this.login = login;
   }

   public Document getSchedulePage(){
      try {
         Connection connection = Jsoup.connect(SCHEDULE_PAGE_URL)
//                 .cookie("NSA_OnLine_SessionId", login.tokenCookie)
//                 .cookie("ARRAffinity", login.arrAffinityCookie)
//                 .cookie("ARRAffinitySameSite", login.arrAffinitySameSiteCookie)
                 .method(Connection.Method.GET);

         //Need to parse because of Brazil chars such as "ç~éà"
         Document document = Jsoup.parse(new String(connection.execute().bodyAsBytes()), "UTF-8");


         if(document.body().getElementsByClass("MsoNormal").tagName("strong").hasText()) {
             Thread.sleep(250);
             getSchedulePage();
         }
         return document;

      }catch (IOException | InterruptedException e){
         logger.log(Level.SEVERE, "Something went wrong when doing the request");
      }
      return null;
   }
}
