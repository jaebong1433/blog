package me.jaebong.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.jaebong.springbootdeveloper.domain.Article;
import me.jaebong.springbootdeveloper.dto.AddArticleRequest;
import me.jaebong.springbootdeveloper.dto.ArticleResponse;
import me.jaebong.springbootdeveloper.dto.UpdateArticleRequest;
import me.jaebong.springbootdeveloper.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController // HTTP Response Body에 객체 데이터를 JSON 형식으로 반환하는 컨트롤러
public class BlogApiController {

    private final BlogService blogService;

    // HTTP 메서드가 POST일 때 전달받은 URL와 동일하면 메서드로 매핑
    @PostMapping("/api/articles")
    // 요청 본문 값 매핑
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request){
        Article savedArticle = blogService.save(request);

        // 요청한 자원이 성공적으로 생성되었으며 저장된 블로그 글 정보를 응답 객체에 담아 전송
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }

    // /api/articles GET 요청이 오면 글 전체를 조회하는 findAll()메서드를 호출한 다음 응답용 객체인 ArticleResponse로 파싱해 body에 담아 클라이언트로 전송
    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles(){
        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(articles);
    }

    @GetMapping("/api/articles/{id}")
    // URL 경로에서 값 추출
    //@PathVariable 애네터이션은 URL에서 값을 가져옴 /api/articles/3 GET 요청을 받으면 id에 3이 들어옴
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id){
        Article article = blogService.findById(id);

        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }

    // /api/articles/{id} DELETE요청이 오면 글을 삭제
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id){
        blogService.delete(id);

        return ResponseEntity.ok()
                .build();
    }

    // /api/articles/{id} PUT 요청이 오면 Request Body 정보가 request로 넘어옴
    // 다시 서비스 클래스의 update()메서드에 id와 request를 넘겨줌 응답 값은 body에 담아 전송함
    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id,
                                                 @RequestBody UpdateArticleRequest request){
        Article updateArticle = blogService.update(id, request);

        return ResponseEntity.ok()
                .body(updateArticle);
    }
 }
