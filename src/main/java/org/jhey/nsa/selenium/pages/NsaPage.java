package org.jhey.nsa.selenium.pages;

import org.openqa.selenium.WebDriver;

public abstract class NsaPage {
   public WebDriver getWebDriver() {
      return webDriver;
   }
  private final WebDriver webDriver;
   protected NsaPage(WebDriver webDriver) {
      this.webDriver = webDriver;
   }
}
