package org.toilelibre.libe.person;

import static org.toilelibre.libe.curl.Curl.$;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.toilelibre.libe.curl.Curl.CurlException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class ApiTest {

    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();
    
    @Test
    public void addAccountStepByStep () throws JsonParseException, JsonMappingException, CurlException, IOException {
        final ObjectMapper om = new ObjectMapper ();
        UUID id = UUID.fromString (om.readValue (
           $("curl -X 'POST' http://localhost:8080 -H'Content-Type: application/json' -d '{}'"),
           Map.class).get ("id").toString ());
        
        $("curl -X 'PUT' http://localhost:8080 -H'Content-Type: application/json' -d '{\"id\":\"" + id + "\",\"firstName\":\"John\",\"lastName\":\"Doe\"}'");
        $("curl -X 'PUT' http://localhost:8080 -H'Content-Type: application/json' -d '{\"id\":\"" + id + "\",\"firstName\":\"John\",\"lastName\":\"Doe\",\"birthCity\":\"Nice\",\"birthCountry\":\"France\",\"birthDate\":\"1934-11-08\",\"nationality\":\"French\",\"phoneNumbers\":[{\"phoneKind\":\"WORK\",\"intCode\":33,\"prefix\":4,\"number\":93457482}]}'");
        $("curl -X 'PUT' http://localhost:8080 -H'Content-Type: application/json' -d '{\"id\":\"" + id + "\",\"firstName\":\"John\",\"lastName\":\"Doe\",\"birthCity\":\"Nice\",\"birthCountry\":\"France\",\"birthDate\":\"1934-11-08\",\"nationality\":\"French\",\"phoneNumbers\":[{\"phoneKind\":\"WORK\",\"intCode\":33,\"prefix\":4,\"number\":93457482}],\"addresses\":[{\"addressKind\":\"WORK\",\"name\":\"Travail\",\"number\":10,\"kindOfRoute\":\"ALLEY\",\"streetName\":\"de l'arche\",\"postalCode\":\"92400\",\"city\":\"Courbevoie\",\"regionOrState\":\"Ile-de-France\",\"country\":\"France\"}]}'");
        
        System.out.println ();
        System.out.println ("Version #1 :");
        System.out.println ("------------");
        System.out.println ($("curl -X 'GET' http://localhost:8080/snapshots/" + id + "/number/1"));
        System.out.println ();
        System.out.println ("Version #2 :");
        System.out.println ("------------");
        System.out.println ($("curl -X 'GET' http://localhost:8080/snapshots/" + id + "/number/2"));
        System.out.println ();
        System.out.println ("Version #3 :");
        System.out.println ("------------");
        System.out.println ($("curl -X 'GET' http://localhost:8080/snapshots/" + id + "/number/3"));
        System.out.println ();
        System.out.println ("Version #4 :");
        System.out.println ("------------");
        System.out.println ($("curl -X 'GET' http://localhost:8080/snapshots/" + id + "/number/4"));
    }
}
