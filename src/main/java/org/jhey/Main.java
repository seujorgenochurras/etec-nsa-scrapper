package org.jhey;

import org.jhey.nsa.api.request.Requester;
import org.jhey.nsa.api.request.Transcriber;
import org.jhey.nsa.selenium.pages.LoginPage;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

public class Main {
   public static void main(String[] args) throws IOException, InterruptedException {
//      System.setProperty("webdriver.chrome.driver", "src/drive/chromedriver.exe");
//      ChromeDriver chromeDriver = new ChromeDriver();
//      chromeDriver.get("https://nsa.cps.sp.gov.br/");

//      LoginPage loginPage = new LoginPage(chromeDriver);

      Requester requester = new Requester(null);
      Transcriber transcriber = new Transcriber();
      transcriber.transcribe(requester.getSchedulePage());
   }
}