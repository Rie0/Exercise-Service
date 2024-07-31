package org.twspring.exerciseservice.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.twspring.exerciseservice.Api.ApiResponse;
import org.twspring.exerciseservice.Model.NewsArticle;
import org.twspring.exerciseservice.Service.NewsArticleService;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/news_article")
@RequiredArgsConstructor
public class NewsArticleController {

    private final NewsArticleService newsArticleService;

    //==================================GET==========================================
    @GetMapping("/get/all_news_articles")
    public ResponseEntity getNewsArticles() {
        ArrayList<NewsArticle> newsArticles = newsArticleService.getNewsArticles();

        if (newsArticles.isEmpty()){
            return ResponseEntity.status(404).body(new ApiResponse("No news articles found"));
        }
        return ResponseEntity.status(200).body(newsArticleService.getNewsArticles());
    }

    @GetMapping("/get/news_article/by_category/{category}")
    public ResponseEntity getNewsArticlesByCategory(@PathVariable String category) {

        if (newsArticleService.getNewsArticlesByCategory(category)==null){
            return ResponseEntity.status(404).body(new ApiResponse("No news articles found"));
        }
        return ResponseEntity.status(200).body(newsArticleService.getNewsArticlesByCategory(category));
    }

    @GetMapping("get/news_article/published")
    public ResponseEntity getPublishedNewsArticles(){
        if (newsArticleService.getPublishedNewsArticles()==null){
            return ResponseEntity.status(404).body(new ApiResponse("No news articles found"));
        }
        return ResponseEntity.status(200).body(newsArticleService.getPublishedNewsArticles());
    }
    //==================================POST==========================================
    @PostMapping("/add/news_article")
    public ResponseEntity addNewsArticle(@Valid @RequestBody NewsArticle newsArticle, Errors errors) {

        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        newsArticleService.addNewsArticle(newsArticle);
            return ResponseEntity.status(201).body(new ApiResponse("News Article successfully added"));
    }

    //==================================UPDATE==========================================
    @PutMapping("/update/news_article/{id}")
    public ResponseEntity updateNewsArticle(@PathVariable int id,@Valid@RequestBody NewsArticle newsArticle, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        boolean isUpdated = newsArticleService.updateNewsArticle(newsArticle,id);
        if (isUpdated){
            return ResponseEntity.status(201).body(new ApiResponse("News Article successfully updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Update News Article failed."));
    }

    @PutMapping("update/publish/{id}")
    public ResponseEntity publishNewsArticle(@PathVariable int id){

        boolean isUpdated = newsArticleService.publishNewsArticle(id);
        if (isUpdated){
            return ResponseEntity.status(201).body(new ApiResponse("News Article successfully published"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Failed to publish new article."));
    }

    //==================================Delete==========================================
    @DeleteMapping("/delete/news_article/{id}")
    public ResponseEntity deleteNewsArticle(@PathVariable int id) {
        boolean isDeleted = newsArticleService.deleteNewsArticle(id);
        if (isDeleted){
            return ResponseEntity.status(201).body("News Article successfully deleted");
        }
        return ResponseEntity.status(400).body("Delete News Article failed.");
    }


}
