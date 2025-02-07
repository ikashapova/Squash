package api;

import jdk.jfr.Description;
import org.junit.Assert;
import org.junit.Test;
import org.opentestfactory.exception.ParameterException;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.*;

public class RegressTest {
    private final static String URL = "https://reqres.in/";

    @Test
    @Description("Проверка Get запроса")
    public void checkAvatarIdTest() throws ParameterException {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.ResponseSpecOk200().response());
        List<UserData> users = given()
                .when()
                .get("api/users?page=2")
                .then().log().all()
                .extract().body()
                .jsonPath()
                .getList("data", UserData.class);

        users.stream().forEach(x-> Assert
                .assertTrue(x.getAvatar().contains(x
                        .getId().toString())));
        Assert.assertTrue(users.stream().allMatch(x->x.getEmail().endsWith(".in")));

        //int var1 = ParameterService.INSTANCE.getInt("DS_VAR1");

/*        //Проверка статуса
        ValidatableResponse code = given()
                .baseUri("http://cookiemonster.com")
                .when()
                .get("/cookies")
                .then()
                .assertThat()
                .statusCode(200);*/

        List<String> avatars = users.stream().map(UserData::getAvatar).collect(Collectors.toList());
        List<String> ids = users.stream().map(x->x.getId().toString()).collect(Collectors.toList());
    }

    @Test
    @Description("Проверка POST запроса")
    public void checkPostTest(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.ResponseSpecError400().response());
        Integer id = 4;
        String token = "QpwL5tke4Pnpja7X4";
        Register user = new Register("eve.holt@reqres.in", "pistol");
        SuccessRegisterResponse successReg = given()
                .body(user)
                .when()
                .post("api/register")
                .then().log().all()
                .extract().as(SuccessRegisterResponse.class);
        //Assert.assertEquals(id, successReg.getId());
        int x=0;

    }

}
