import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class GetAllBookingsTests {

    private static String URL = "https://restful-booker.herokuapp.com/booking";

    @Test
    public void getAllBookingTest() {
        given().when()
                .get(URL)
                .then()
                .log().all()
                .statusCode(200);
    }
}
