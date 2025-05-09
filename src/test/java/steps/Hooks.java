package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.CommonMethods;

public class Hooks extends CommonMethods {
    @Before
    public void start()  {
        openBrowserAndLaunchApplication();
        //OR
        /*driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("http://hrm.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login");*/



    }
    @After
    public void end(Scenario scenario) {
        byte[] pic ;
        if (scenario.isFailed()){
            pic =takeScreenshot("failed/"+scenario.getName());
        }else {
            pic =takeScreenshot("passed/"+scenario.getName());
        }
        scenario.attach(pic,"image/png",scenario.getName());
        closeBrowser();
        //OR
        // driver.quit();
    }

}

