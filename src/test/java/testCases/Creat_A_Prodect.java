package testCases;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;



public class Creat_A_Prodect {
	String accessToken = null;
	
	@Test
	public void Creatt_A_Product() {
//		HashMap <String,String>payload = new HashMap<String,String>();
//		payload.put("name", "Sonata");
//		payload.put("price", "2000");
//		payload.put("description", "nice car");
//		payload.put("category", "3");
//		
//		put play load on body(payload) thats ow to create by hashmap
//		
		
		Response response=
		given()
		.baseUri("https://techfios.com/api-prod/api/product")
				.header("Content-Type", "application/json; charset=UTF-8")
			//	.header("Authorozition","value token" or + accessToken)
			//	.auth().preemptive().basic("username","password")
				
				.body(new File(".\\src\\main\\java\\data\\CreatePayload.json")).
				
				when()
				.post("/create.php").
				then()
				.extract().response() ;
		int actual = response.getStatusCode();
		Assert.assertEquals(actual, 201);
		
		String actualHeader=response.header("Content-Type");
		Assert.assertEquals(actualHeader, "application/json; charset=UTF-8");
			
		String actualBody= response.getBody().asString();
		JsonPath jp= new JsonPath(actualBody);
		String responseMessage = jp.getString("message");
		Assert.assertEquals(responseMessage, "Product was created.");
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
		
		
		
		
		
		
		
	
	}

}
