package org.jhey.nsa.pages;


import io.github.cdimascio.dotenv.Dotenv;
import org.jhey.captcha_breaker.stt.html.elements.captcha.Captcha;
import org.jhey.captcha_breaker.stt.selenium.captcha.CaptchaFinder;
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
      Captcha captcha;

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
      public void login(){
            insertStudentAccountInfo();
            captcha.solveCaptcha();
            loginButton.click();
      }
}
