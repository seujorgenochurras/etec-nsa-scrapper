package org.jhey.nsa;

import org.jhey.nsa.pages.LoginPage;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;

public class TokenGrabber {

   WebDriver webDriver;

   public WebDriver getWebDriver() {
      return webDriver;
   }

   public TokenGrabber setWebDriver(WebDriver webDriver) {
      this.webDriver = webDriver;
      return this;
   }

   public String getUserToken(){
      LoginPage loginPage = new LoginPage(webDriver);
      loginPage.login();
      return webDriver.manage().getCookies().stream().filter(cookie -> cookie.getName().equals("NSA_OnLine_SessionId"))
                      .findFirst().map(Cookie::getValue).orElseThrow(() -> new NotFoundException("Cookie token not found"));
   }
}
