package org.jhey;

import org.jhey.nsa.request.Requester;
import org.jhey.nsa.request.Transcriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Main {

   @Autowired
   static Transcriber transcriber = new Transcriber();
   public static void main(String[] args) throws IOException {
//      System.setProperty("webdriver.chrome.driver", "src/drive/chromedriver.exe");
//      ChromeDriver chromeDriver = new ChromeDriver();
//      chromeDriver.get("https://nsa.cps.sp.gov.br/");
//
//      LoginPage loginPage = new LoginPage(chromeDriver);
      SpringApplication.run(Main.class, args);

      Requester requester = new Requester(null);

      transcriber.transcribe(requester.getSchedulePage());
   }
}