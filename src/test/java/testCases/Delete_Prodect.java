package testCases;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;



public class Delete_Prodect {
	SoftAssert softassert;
	
	public Delete_Prodect() {
		softassert = new SoftAssert();

	}
	@Test
	public void Delete_Prodect() {

		
		Response response=
		given()
		.baseUri("https://techfios.com/api-prod/api/product")
				.header("Content-Type", "application/json; charset=UTF-8 ")
			
				.body(new File(".\\src\\main\\java\\data\\DeletePayload.json")).
				
				when()
				.delete("/delete.php").
				then()
				.extract().response() ;
		int actual = response.getStatusCode();
		//Assert.assertEquals(actual, 201);
		softassert.assertEquals(actual, 200);
		
		String actualHeader=response.header("Content-Type");
		//Assert.assertEquals(actualHeader, "application/json; charset=UTF-8");
		softassert.assertEquals(actualHeader, "application/json; charset=UTF-8");	
		
		String actualBody= response.getBody().asString();
		JsonPath jp= new JsonPath(actualBody);
		String responseMessage = jp.getString("message");
		//Assert.assertEquals(responseMessage, "Product was created.");
		softassert.assertEquals(responseMessage, "Product was deleted.");
		
		//JsonPath jp= new JsonPath(actualBody);
		//String accessToken = jp.getString("message");
		//Assert.assertEquals(accessToken, "Product was created.");
		
		long responseTime = response.getTimeIn(TimeUnit.MILLISECONDS);
		System.out.println("Response Time:"+responseTime);
		if(responseTime<=2000) {
			System.out.println("Response time is within range");
		}else {
			System.out.println("Response time is out of range");
		}
		
		
	softassert.assertAll();	
		
		
		
		
	
	}

}
