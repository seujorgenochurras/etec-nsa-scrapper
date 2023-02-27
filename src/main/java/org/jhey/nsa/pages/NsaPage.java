package org.jhey.nsa.pages;

import org.openqa.selenium.WebDriver;

public abstract class NsaPage {
   WebDriver webDriver;
   protected NsaPage(WebDriver webDriver) {
      this.webDriver = webDriver;
   }
}
