package me.jaebong.springbootdeveloper.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.jaebong.springbootdeveloper.domain.Article;
import me.jaebong.springbootdeveloper.dto.AddArticleRequest;
import me.jaebong.springbootdeveloper.dto.UpdateArticleRequest;
import me.jaebong.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor //final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Service //빈으로 등록
public class BlogService {

    private final BlogRepository blogRepository;

    //블로그 글 추가 메서드
    public Article save(AddArticleRequest request){
        return blogRepository.save(request.toEntity());
    }

    //JPA 지원 메서드인 findAll()을 호출해 article 테이블에 저장되어 있는 모든 데이터를 조회함
    public List<Article> findAll(){
        return blogRepository.findAll();
    }

    //데이터베이스에 저장되어 있는 글의 ID를 이용해 하나의 글을 조회
    public Article findById(long id){
        return blogRepository.findById(id)
                //JPA에서 제공하는 findById()메서드를 사용해 ID를 받아 엔티티를 조회하고 없으면 예외를 발생시킴
                .orElseThrow(()-> new IllegalArgumentException("not found: " + id));
    }

    //블로그 글의 ID를 받은 뒤 JPA에서 제공하는 deleteById()메서드를 이용해 데이터베이스에서 데이터를 삭제
    public void delete(long id){
        blogRepository.deleteById(id);
    }

    //@Transactional 애너테이션은 매칭한 메서드를 하나의 트랜잭션으로 묶는 역할을 함
    @Transactional //트랜잭션 메서드
    public Article update(long id, UpdateArticleRequest request){
        Article article = blogRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("not found:" + id));

        article.update(request.getTitle(), request.getContent());

        return article;
    }
}
