package org.jhey.nsa.selenium.pages;


import io.github.cdimascio.dotenv.Dotenv;
import org.jhey.captcha_breaker.stt.html.elements.captcha.Captcha;
import org.jhey.captcha_breaker.stt.selenium.captcha.CaptchaFinder;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage extends NsaPage {
      @FindBy(id = "btnEntrar")
      private WebElement loginButton;
      @FindBy(id = "txtCod")
      private WebElement etecId;
      @FindBy(id = "txtlogin")
      private WebElement userId;
      @FindBy(id = "txtSenha")
      private WebElement userPass;
      private final Captcha captcha;

      public LoginPage(WebDriver webDriver) {
            super(webDriver);
            PageFactory.initElements(webDriver, this);
            this.captcha = CaptchaFinder.findCaptchaElement(webDriver);

      }

      private void insertStudentAccountInfo(){
            Dotenv dotenv = Dotenv.load();
            final String ETEC_ID = dotenv.get("ETEC_ID");
            final String ETEC_USER_ID = dotenv.get("ETEC_USER_ID");
            final String ETEC_PASS = dotenv.get("ETEC_PASS");
           this.etecId.sendKeys(ETEC_ID);
           this.userId.sendKeys(ETEC_USER_ID);
           this.userPass.sendKeys(ETEC_PASS);
      }
      public Login login(){
            insertStudentAccountInfo();
            captcha.solveCaptcha();
            loginButton.click();
            return new Login();
      }
      public class Login{
            public String getToken() {
                  return getWebDriver().manage().getCookies().stream().filter(cookie -> cookie.getName().equals("NSA_OnLine_SessionId"))
                          .findFirst().map(Cookie::getValue).orElseThrow(() -> new NotFoundException("Cookie token not found"));
            }
            public String getARRAffinity(){
                  return getWebDriver().manage().getCookies().stream().filter(cookie -> cookie.getName().equals("ARRAffinity"))
                          .findFirst().map(Cookie::getValue).orElseThrow(() -> new NotFoundException("Cookie ARRAffinity not found"));
            }
            public String getARRAffinitySameSite(){
                  return getWebDriver().manage().getCookies().stream().filter(cookie -> cookie.getName().equals("ARRAffinitySameSite"))
                          .findFirst().map(Cookie::getValue).orElseThrow(() -> new NotFoundException("Cookie ARRAffinitySameSite not found"));
            }
      }
}
