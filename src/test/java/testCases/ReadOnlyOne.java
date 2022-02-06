package testCases;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class ReadOnlyOne {
	SoftAssert softassert;
	
	public ReadOnlyOne() {
		softassert = new SoftAssert();

	}
	
	@Test
	public void read_only_one() {
		
		 
		Response response=
		given().baseUri("https://techfios.com/api-prod/api/product")
				.header("Content-Type", "application/json").
				queryParam("id","2726").
				//queryParam("name","Rango 11.0").
				when().
				get("/read_one.php").
				then()
				.extract().
				response() ;
		int actual = response.getStatusCode();
	//	Assert.assertEquals(actual, 200);
		softassert.assertEquals(actual, 201);
		
		String actualHeader=response.header("Content-Type");
//		Assert.assertEquals(actualHeader, "application/json");
		softassert.assertEquals(actualHeader, "application/json");
		String actualBody= response.getBody().asString();
		System.out.println("Response Body:"+actualBody);
		JsonPath jp= new JsonPath(actualBody);
		
	 	String productId = jp.getString("id");
//		Assert.assertEquals(productId,"2727");
		softassert.assertEquals(productId,"2727");

		String productName = jp.getString("name");
//		Assert.assertEquals(productName,"Rango 11.0");
		softassert.assertEquals(productName,"Rango 11.0");

		String productPrice = jp.getString("price");
		//Assert.assertEquals(productPrice,"699");
		softassert.assertEquals(productPrice,"699");
	
		softassert.assertAll();//tell wher is failed
	}

}
