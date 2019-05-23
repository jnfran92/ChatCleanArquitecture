package com.juanchango.data.suppliers.cache.serializer;

import com.juanchango.data.entity.PostEntity;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;

public class SerializerTest {

    private static final String POST_PATH = "/Users/Juan/GitHub/ChatCleanArquitecture/data/src/test/java/com/speakliz/data/suppliers/cache/serializer/sample_data/sample_post.json";
    private Serializer serializer;


    @Before
    public void setUp(){
        serializer = Serializer.getInstance();
    }

    @Test
    public void testDeSerialize(){

        // Init
        final PostEntity postEntityFake = new PostEntity();
        postEntityFake.setPostId(1);
        postEntityFake.setUserId(1);
        postEntityFake.setBody("quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto");
        postEntityFake.setTitle("sunt aut facere repellat provident occaecati excepturi optio reprehenderit");

        // Test
        String stringJson = getResponseFromLocalJson(POST_PATH);
        final PostEntity postEntityToTest = serializer.deserialize(stringJson, PostEntity.class);

        assertEquals(postEntityToTest.getPostId(), postEntityFake.getPostId());
        assertEquals(postEntityToTest.getTitle(), postEntityFake.getTitle());
        assertEquals(postEntityToTest.getUserId(), postEntityFake.getUserId());
        assertEquals(postEntityToTest.getBody(), postEntityFake.getBody());
    }

    @Test
    public void testSerialize(){

        // Init
        final PostEntity postEntityFake = new PostEntity();
        postEntityFake.setPostId(1);
        postEntityFake.setUserId(1);
        postEntityFake.setBody("quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto");
        postEntityFake.setTitle("sunt aut facere repellat provident occaecati excepturi optio reprehenderit");


        // Test
        String stringJsonFake = getResponseFromLocalJson(POST_PATH);

        String stringJsonToTest = serializer.serialize(postEntityFake, PostEntity.class);

        assertEquals(stringJsonFake, stringJsonToTest);

    }


    private String getResponseFromLocalJson(String path) {
        String str = "";
        try {
            StringBuilder builder = new StringBuilder();
            InputStreamReader json = new InputStreamReader(new FileInputStream(path),
                    StandardCharsets.UTF_8);
            BufferedReader in = new BufferedReader(json);

            while ((str = in.readLine()) != null) {
                builder.append(str);
            }
            in.close();
            str = builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

}