package testCases;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class ReadAll {

	@Test
	public void read_all() {
		Response response=
		given().baseUri("https://techfios.com/api-prod/api/product")
				.header("Content-Type", "application/json; charset=UTF-8").when().get("/read.php").then()
				.extract().response() ;
		int actual = response.getStatusCode();
		Assert.assertEquals(actual, 200);
		
		String actualHeader=response.header("Content-Type");
		Assert.assertEquals(actualHeader, "application/json; charset=UTF-8");
			
	}

}
