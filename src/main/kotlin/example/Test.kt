package example

import com.example.example.ExampleJson2KtKotlin
import com.jayway.jsonpath.JsonPath
import io.restassured.RestAssured
import io.restassured.module.jsv.JsonSchemaValidator
import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import io.restassured.response.Response
import org.hamcrest.Matcher
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsEqual
import java.io.File
import kotlin.streams.toList


fun main(args: Array<String>) {

    RestAssured.baseURI = "https://reqres.in"

    var response : Response = RestAssured.given().log().all()
        .`when`().get("/api/users?page=2")
        .then().statusCode(200)
        // .body("page", IsEqual.equalTo(2))
//        .body("data.size()", IsEqual.equalTo(6))
        .extract().response()

    println(response.asString())
    println(response.statusCode() == 100)
    println(response.statusCode() == 200)

    var responseNew = Given {
        header("user-agent", "")
    } When {
        get("/api/users?page=2")
    } Then {
        statusCode(200)
    } Extract {
        response()
    }

//    var pageValue = responseNew.jsonPath().getInt("data[?(@.first_name=='Lindsay')].id")
//    println(pageValue)

    var jsonPth = JsonPath.parse(responseNew.asString())
    var id = jsonPth.read<List<Int>>("data[?(@.first_name=='Lindsay')].id")
    println("ID "+id[0])

    var instanc : ExampleJson2KtKotlin = responseNew.jsonPath().getObject("$", ExampleJson2KtKotlin::class.java)
    println(instanc.page)
    println(instanc.data.size)
    var filterData = instanc.data.stream().filter { d -> d.first_name.equals("Lindsay") }.toList()
    println(filterData[0].id)

//    var response =
//        RestAssured.given().log().all().`when`().get("/api/users/?page=2").then().log().all()
//            .extract().response()
//
//    var statusCode = response.statusCode()
//    println(statusCode > 199)
//
//    var listIds = response.jsonPath().getList<Int>("data.id")
//    var filteredIds = listIds.stream().filter { value -> value > 10 }.toList<Int>()
//    println(filteredIds)
//
//    var object12 = response.jsonPath().getObject("$",ExampleJson2KtKotlin::class.java)
//
//
//    var path = JsonPath.parse(response.asString())
//    var id : List<String> = path.read("$.data[?(@.email==\"lindsay.ferguson@reqres.in\")].id")
//    println(id[0])
//
//    Given {
//        header("user-agent", "xyz")
//    } When {
//        get("/api/users/?page=2")
//    } Then {
//        statusCode(200)
//        body("page", IsEqual.equalTo(2))
//        body(JsonSchemaValidator.matchesJsonSchema(File("/Users/syedtarifabbasrizvi/eclipse-workspace/test/src/main/kotlin/example/schema.json")))
//    }
//
//    MatcherAssert.assertThat("Validate JSON Schema",
//        response.asString(),
//        JsonSchemaValidator.matchesJsonSchema(File("/Users/syedtarifabbasrizvi/eclipse-workspace/test/src/main/kotlin/example/schema.json")))

    // Object Conversion
    // Json Validation
    // post, delete, put

    // Authentication
}



