package com.runners;

import org.testng.annotations.*;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;

@CucumberOptions(
	plugin= {"pretty", "html:target/cucumber-reports/cucumber.html", "json:target/cucumber-reports/cucumber.json"}, 
	features= {"src/test/resources/features"},
	glue= {"StepDefinitions", "com.hooks"},
	monochrome=true,
	//tags="@EndToEnd",
	dryRun=false)

public class TestsRunner extends AbstractTestNGCucumberTests{
	TestNGCucumberRunner tNGCukesRnr;
	
	@BeforeClass(alwaysRun = true)
	public void setup() {
		System.out.println("From Before Class, TestsRunner class");
		tNGCukesRnr = new TestNGCucumberRunner(this.getClass());
	}
	
	/*@Test(groups="Cukes", description="Class that runs scenarios from features", dataProvider="scenarios")
	public void runCukesScenarios(PickleWrapper pWrapper, FeatureWrapper fwrapper) {
		System.out.println("From Test, TestsRunner class");
		tNGCukesRnr.runScenario(pWrapper.getPickle());
	}
	
	@DataProvider
	public Object[][] scenarios(){
		if(tNGCukesRnr == null) {
			return new Object[0][0];
		}
		return tNGCukesRnr.provideScenarios();
	}*/
	
	/*@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios(){
		return super.scenarios();
	}*/
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {
		if(tNGCukesRnr == null) {
			return;
		}
		tNGCukesRnr.finish();
	}
}
