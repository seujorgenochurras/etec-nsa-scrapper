package org.jhey;

import org.jhey.nsa.TokenGrabber;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
   public static void main(String[] args) {
      System.setProperty("webdriver.chrome.driver", "src/drive/chromedriver.exe");
      ChromeDriver chromeDriver = new ChromeDriver();
      chromeDriver.get("https://nsa.cps.sp.gov.br/");
      TokenGrabber tokenGrabber = new TokenGrabber().setWebDriver(chromeDriver);
      System.out.println(tokenGrabber.getUserToken());
   }
}