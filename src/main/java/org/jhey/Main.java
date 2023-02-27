package org.jhey;

import org.jhey.nsa.pages.LoginPage;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
   public static void main(String[] args) {
      System.setProperty("webdriver.chrome.driver", "src/drive/chromedriver.exe");
      ChromeDriver chromeDriver = new ChromeDriver();
      chromeDriver.get("https://nsa.cps.sp.gov.br/");
      LoginPage loginPage = new LoginPage(chromeDriver);
      loginPage.login();
   }
}