package APITesting;
import APIClassObjects.InfoClass;
import APIClassObjects.PostClass;
import APIClassObjects.Posts;
import Advanced.Info;
import Advanced.PostAdv;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import sun.security.provider.ConfigFile;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class JasonServerRequests  {
    // GET      /posts

    //@Test
    public void test1(){
        Response resp = given().
        when().
        get("http://localhost:3000/posts");

        System.out.println("Get response is: "+resp.asString());

    }
    //POST      /posts
    //@Test
    public void test2(){
        Response resp = given().
        body(" {\"id\":\"2\","
                + " \"title\":\"dummyTitle\","
                + " \"author\":\"Jamshed\"}   ").
        when().
        contentType(ContentType.JSON).
        post("http://localhost:3000/posts");

        System.out.println(resp.asString());
    }

    //POST      /posts
    //@Test
    public void test3(){
        Response resp = given().
                body(" {\"id\":\"3\","
                        + " \"title\":\"gummyBear\","
                        + " \"author\":\"Raufjonik\"}   ").
                when().
                contentType(ContentType.JSON).
                post("http://localhost:3000/posts");

        System.out.println(resp.asString());
    }

    //POST    /posts
    //@Test
    public void test4(){
        Posts posts = new Posts();
        posts.setID("4");
        posts.setTitle("posts request by object");
        posts.setAuthor("Jasurbek");

        Response resp = given().
        when().
        contentType(ContentType.JSON).
        body(posts).
        post("http://localhost:3000/posts");

        System.out.println("Response: " +resp);
        System.out.println("Status code is: "+ resp.getStatusCode());
       // Assert.assertEquals(resp.getStatusCode(), 500);

    }

    //@Test
    public void test5(){
        Response resp = given().
                when().
                get("http://localhost:3000/posts/4");

        System.out.println(resp.asString());
    }

    //PUT    /posts/1
    //@Test
    public void test6(){
        Posts posts = new Posts();
        posts.setID("2");
        posts.setTitle("update the Title");
        posts.setAuthor("update to some other name");

        Response resp = given().
        when().
        contentType(ContentType.JSON).
        body(posts).
        put("http://localhost:3000/posts/2");

        System.out.println("Put API response: " + resp.asString());

    }

    // PATCH    /posts
    //@Test
    public void testPatch(){
        Response resp = given().
        body("{\"title\":\"updated by PUT request\"}").
        when().
        contentType(ContentType.JSON).
        patch("http://localhost:3000/posts/4");

        System.out.println("PATCH Request is: " + resp.asString());
    }


    //DELETE    /posts/1
    //@Test
    public void testDelete(){
        Response resp = given().
        when().
        delete("http://localhost:3000/posts/4");

        System.out.println("Delete response: " + resp.asString());
        System.out.println(resp.getStatusCode());

    }


    // COMPLEX POSTS
    //POST    /posts
    @Test
    public void test10(){
        InfoClass info = new InfoClass();
        info.setEmail("info@appium-selenium.com");
        info.setPhone("123456");
        info.setAddress("Brooklyn");

        PostClass postClass = new PostClass();
        postClass.setId("5");
        postClass.setTitle("title");
        postClass.setAuthor("Author");
        postClass.setInfo(info);

        Response resp = given().
        when().
        contentType(ContentType.JSON).
        body(postClass).
        post("http://localhost:3000/posts");

        System.out.println("Response: "+resp.asString());

        }


        //COMPLEX POSTS
        //POST   /posts
    @Test
    public void test11(){
        Info info1 = new Info();
        info1.setEmail("test email 1");
        info1.setPhone("test phone 1");
        info1.setAddress("test address 1");

        Info info2 = new Info();
        info2.setEmail("test email 2");
        info2.setPhone("test phone 2");
        info2.setAddress("test address 2");

        PostAdv posts = new PostAdv();
        posts.setId("100");
        posts.setTitle("title");
        posts.setAuthor("author");
        posts.setInfo(new Info[]{info1, info2});

        Response resp = given().
                when().
                contentType(ContentType.JSON).
                body(posts).
                post("http://localhost:3000/posts");

        System.out.println("Response: "+resp.statusLine());

    }


    // GET Request calculate Response time
    // GET   /posts
    @Test
    public void test15(){
        Response resp = given().
                when().
                get("http://localhost:3000/posts");

                Long time = resp.
                then().
                extract().
                time();

        System.out.println("Response time is: " +time);

                given().
                when().
                get("http://localhost:3000/posts").
                then().
                and().
                time(lessThan(800L));
    }
}
