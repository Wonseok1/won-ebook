package com.itzon.won.ebook.service.posts;

import com.itzon.won.ebook.domain.posts.Posts;
import com.itzon.won.ebook.domain.posts.PostsRepository;
import com.itzon.won.ebook.web.dto.PostsResponseDto;
import com.itzon.won.ebook.web.dto.PostsSaveRequestDto;
import com.itzon.won.ebook.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당사용자가 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당사용자가 없습니다. id="+id));
        return new PostsResponseDto(entity);
    }
}
