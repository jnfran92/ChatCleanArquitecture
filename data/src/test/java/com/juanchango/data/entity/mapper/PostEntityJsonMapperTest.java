package com.juanchango.data.entity.mapper;

import com.juanchango.data.entity.PostEntity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;


public class PostEntityJsonMapperTest {

    private static final String FAKE_PATH_SAMPLE_POSTS
            = "/Users/Juan/GitHub/ChatCleanArquitecture/data/src/test/java/com/speakliz/data/entity/mapper/sample_data/sample_posts.json";

    private static final String FAKE_PATH_SAMPLE_POST
            = "/Users/Juan/GitHub/ChatCleanArquitecture/data/src/test/java/com/speakliz/data/entity/mapper/sample_data/sample_post.json";

    private PostEntityFromJsonMapper postEntityJsonMapper;
    private PostEntity fakePostEntity;

    @Before
    public void setUp(){

        postEntityJsonMapper = new PostEntityFromJsonMapper();

        // Fake Sample PostModel Entity
        fakePostEntity = new PostEntity();
        fakePostEntity.setPostId(1);
        fakePostEntity.setUserId(1);
        fakePostEntity.setTitle("sunt aut facere repellat provident occaecati excepturi optio reprehenderit");
        fakePostEntity.setBody("quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto");

    }

    @Test
    public void happyTransformPostEntity() {
        String jsonResponse = getResponseFromLocalJson(FAKE_PATH_SAMPLE_POST);
        PostEntity postEntity = postEntityJsonMapper.transformPostEntity(jsonResponse);

        Assert.assertEquals(fakePostEntity.getPostId(), postEntity.getPostId());
        Assert.assertEquals(fakePostEntity.getUserId(), postEntity.getUserId());
        Assert.assertEquals(fakePostEntity.getTitle(), postEntity.getTitle());
        Assert.assertEquals(fakePostEntity.getBody(), postEntity.getBody());
    }


    @Test
    public void happyTransformPostEntityList() {
        String jsonResponse = getResponseFromLocalJson(FAKE_PATH_SAMPLE_POSTS);
        List<PostEntity> postEntityList = postEntityJsonMapper.transformPostEntityList(jsonResponse);


        int position = fakePostEntity.getPostId() - 1;

        Assert.assertEquals(fakePostEntity.getPostId(), postEntityList.get(position).getPostId());
        Assert.assertEquals(fakePostEntity.getUserId(), postEntityList.get(position).getUserId());
        Assert.assertEquals(fakePostEntity.getTitle(), postEntityList.get(position).getTitle());
        Assert.assertEquals(fakePostEntity.getBody(), postEntityList.get(position).getBody());
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