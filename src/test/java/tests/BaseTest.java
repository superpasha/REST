package tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

public class BaseTest {

    public static RequestSpecification requestSpecification;
    public static ResponseSpecification responseSpecification;

    public RequestSpecification requestSpecification() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/test/resources/globalConfig.properties"));

            if (requestSpecification == null){
                PrintStream log = new PrintStream(new FileOutputStream("target/test.log"));
                requestSpecification = new RequestSpecBuilder().setBaseUri(properties.getProperty("baseUri"))
                        .addFilter(RequestLoggingFilter.logRequestTo(log))
                        .addFilter(ResponseLoggingFilter.logResponseTo(log))
                        .setContentType(ContentType.JSON).build();
                responseSpecification = new ResponseSpecBuilder()
                        .expectContentType(ContentType.JSON)
                        .build();
                return requestSpecification;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return requestSpecification;
    }


}
