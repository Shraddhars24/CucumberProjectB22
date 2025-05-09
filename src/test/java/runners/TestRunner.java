package runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        //it has the path of feature directory/file
        features = "src/test/resources/features/",
        //the name of the package where step definitions are available
        glue = "steps",
        //it stops actual execution when set to true and scans all the steps
        //also, it provides missing step definition
        //to start the execution, set the value of dry run to false
        dryRun =false,
        tags = "@addEmp",
        plugin = {"pretty","html:target/cucumber.html","json:target/cucumber.json"}


        // All three will be executed
        /*tags = "@sprint1 and @sprint2"//none of the scenario will be
        executed as condition fails as none of them have both tag name */
        //tags= "@sprint1 and @smoke"
        /*One scenario will be executed as that has both the tag name and condition is satisfied*/
)

public class TestRunner {
}
