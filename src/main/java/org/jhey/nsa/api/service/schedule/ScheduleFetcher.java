package org.jhey.nsa.api.service.schedule;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.transaction.Transactional;
import org.jhey.jsoup.NsaSession;
import org.jhey.nsa.api.model.schedule_classes.DailySchedule;
import org.jhey.nsa.request.Transcriber;
import org.jhey.selenium.LoginWith;
import org.jhey.selenium.NsaLogin;
import org.jhey.student.StudentCredentials;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ScheduleFetcher {

   @Autowired
   private Transcriber transcriber;
   @Autowired
   private DailyScheduleService dailyScheduleService;

   private DailySchedule fetchSchedule() {
      NsaSession nsaSession = createNsaSession();
      return transcriber.transcribe(nsaSession.getScheduleTimetableDocument());
   }

   private NsaSession createNsaSession() {
      ChromeDriver chromeDriver = setupChromeDriver();
      chromeDriver.get(getStringFromDotEnv("SCHOOL_URL"));

      StudentCredentials credentials = getStudentCredentials();
      final String assemblyAiToken = getStringFromDotEnv("ASSEMBLYAI_TOKEN");

      NsaSession session = NsaLogin.login(LoginWith.credentials(credentials, assemblyAiToken, chromeDriver));
      chromeDriver.quit();
      return session;
   }
   private String getStringFromDotEnv(String string){
      Dotenv dotenv = Dotenv.load();
      return dotenv.get(string);
   }

   private StudentCredentials getStudentCredentials() {

      final String etecId = getStringFromDotEnv("ETEC_ID");
      final String rm = getStringFromDotEnv("ETEC_USER_ID");
      final String password = getStringFromDotEnv("ETEC_PASS");

      return new StudentCredentials(etecId, rm, password);
   }

   private ChromeDriver setupChromeDriver() {
      ChromeOptions options = new ChromeOptions().addArguments("--remote-allow-origins=*"); //needed to fix a common bug among new chromeDriver versions (112.0)
      return new ChromeDriver(options);
   }

   @Async
   @Transactional
   public void fetchAndSaveIfFoundFreshSchedule() {
      DailySchedule freshSchedule = fetchSchedule();

      if (dailyScheduleService.isLatestSchedule(freshSchedule)) {
         dailyScheduleService.save(freshSchedule);
      }
   }
}