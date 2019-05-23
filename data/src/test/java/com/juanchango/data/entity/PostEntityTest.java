package com.juanchango.data.entity;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 *
 */
public class PostEntityTest {

    private static final String FAKE_TITLE = "Something...";

    @Before
    public void init(){
    }

    @Test
    public void testConstructorPostEntity(){

        PostEntity postEntity = new PostEntity();
        postEntity.setTitle(FAKE_TITLE);

        assertThat(postEntity.getPostId(), is(0));
        assertThat(postEntity.getTitle(), is(FAKE_TITLE));

    }

}
