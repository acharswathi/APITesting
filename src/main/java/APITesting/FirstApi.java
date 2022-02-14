package APITesting;
import static io.restassured.RestAssured.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import java.util.concurrent.TimeUnit;
public class FirstApi {
	static String baseurl = "http://api.openweathermap.org/data/2.5/weather";
	static String  res;
	@Test
	public void getResponseBody(){
		  
		res = RestAssured.given()
			.queryParam("lat","34.0901")
			.queryParam("lon","-118.4065")
			.queryParam("appid","26efcb339694216345a0883ce2fba0f2")
		   .when().post(baseurl)
		   .then().statusCode(200).extract().response().asString();
		 System.out.println(res);
		 Assert.assertEquals(res.contains("Beverly Hills") , true , "Response contains Beverly Hills");
		 JsonPath js=new JsonPath(res);
		 System.out.println("id is "+js.getInt("id"));
		 System.out.println("Visibility value is "+js.getInt("visibility"));
	}
	
	@Test
	public static void getStatuscode() {	   
		int statuscode= get(baseurl).then().extract().statusCode();
		System.out.println("The response status is "+statuscode);
		   given().when().get(baseurl).then().assertThat().statusCode(401);
	
		
	}
	
	@Test
	public static void getResponseHeaders(){
		   System.out.println("The headers in the response "+
		                   get(baseurl).then().extract()
		           .headers());
		}
	
	@Test
	public static void getResponseTime(){
		  System.out.println("The time taken to fetch the response "+get(baseurl)
	         .timeIn(TimeUnit.MILLISECONDS) + " milliseconds");
	}
	
	@Test
	public static void getResponseContentType(){
		   System.out.println("The content type of response "+
		           get(baseurl).then().extract()
		              .contentType());
		}
	

}
