package com.itzon.won.ebook.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장불러오기() {
        String title = "테스트 게시글";
        String content = "테스트 본문";
        postsRepository.save(Posts.builder().title(title).content(content).author("jws7077@gmail.com").build());
    }

    @Test
    public void BaseTimeEntity등록() {
        //given
        LocalDateTime now = LocalDateTime.of(2020, 2, 7, 16, 29, 0);
        postsRepository.save(Posts.builder().title("title").content("content").author("author").build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>>>>>>>> createdDate = " + posts.getCreatedDate() + ", modifiedDate =" + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
