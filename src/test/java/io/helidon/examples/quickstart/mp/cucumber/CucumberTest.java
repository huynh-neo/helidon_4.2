package io.helidon.examples.quickstart.mp.cucumber;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        plugin = {"pretty", "summary"},
        glue = "io.helidon.examples.quickstart.mp.cucumber"
)
public class CucumberTest {
}