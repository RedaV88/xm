package org.com.tasks.api;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.com.common.BaseTest;
import org.com.tasks.api.pojo.Film;
import org.com.tasks.api.pojo.People;
import org.com.tasks.api.pojo.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class SwapiTestsIT extends BaseTest {

    @Test
    public void verifyOldestCharacter() {

        //1. Search for a person with the name Vader.
        var expectedName = "Darth Vader";
        Services services = new Services();
        var people = services.getPeople().as(People.class);

        Person person = people.getResults()
                .stream()
                .filter(p -> p.getName().equals(expectedName))
                .findFirst()
                .orElse(null);

        //2. Using previous response (1) find which film that Darth Vader joined has the less
        //planets and validate the response.
        Optional<Film> filmWithFewestPlanets = person.getFilms().stream()
                .map(filmUrl -> given()
                        .get(filmUrl)
                        .then()
                        .log().all()
                        .extract()
                        .as(Film.class))
                .min(Comparator.comparingInt(film -> film.getPlanets().size()));

        //3. Using previous responses (1) &amp; (2) verify if Vader&#39;s starship is on film from
        //response (2).
        Optional<Film> matchingStarShip = person.getFilms().stream()
                .map(filmUrl -> given()
                        .get(filmUrl)
                        .then()
                        .log().all()
                        .extract()
                        .as(Film.class))
                .filter(film -> film.getStarships().stream()
                        .anyMatch(person.getStarships()::contains))
                .findFirst();


        //4. Find and verify the oldest person ever played in all Star Wars films with less than
        //10 requests.

        RestAssured.baseURI = config.BASE_ENDPOINT();

        List<Person> allPeople = new ArrayList<>();
        String nextUrl = "people/";

        while (nextUrl != null) {
            People peopleResponse = given()
                    .when()
                    .get(nextUrl)
                    .then()
                    .statusCode(200)
                    .extract()
                    .as(People.class);

            allPeople.addAll(peopleResponse.getResults());
            nextUrl = peopleResponse.getNext() != null ? peopleResponse.getNext() : null;
        }


        Optional<Person> oldestPersonOpt = allPeople.stream()
                .filter(p -> p.getBirth_year() != null && !p.getBirth_year().equals("unknown") && p.getBirth_year
                        ().endsWith("BBY"))
                .max(Comparator.comparingInt(p -> {
                    try {
                        return Integer.parseInt(p.getBirth_year().replace("BBY", "").trim());
                    } catch (NumberFormatException e) {
                        return Integer.MIN_VALUE;
                    }
                }));

    //Use Case 2:
    //1. Create contract test (Json schema validation) for /people API.
        given()
                .when()
                .get("/people")
                .then()
                .statusCode(200)
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("people-schema.json"))
                .body("count", greaterThan(0)) // Additional validation (optional)
                .body("results", not(empty())); // Additional validation (optional)
    }
}
