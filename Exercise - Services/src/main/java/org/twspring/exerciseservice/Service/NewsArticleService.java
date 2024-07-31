package org.twspring.exerciseservice.Service;

import org.springframework.stereotype.Service;
import org.twspring.exerciseservice.Model.NewsArticle;

import java.util.ArrayList;

@Service
public class NewsArticleService {
    ArrayList<NewsArticle> newsArticles = new ArrayList<NewsArticle>();

    public ArrayList<NewsArticle> getNewsArticles() {
        return newsArticles;
    }

    public ArrayList<NewsArticle> getNewsArticlesByCategory(String category) {
       ArrayList<NewsArticle> foundNewsArticles = new ArrayList<>();
        if (!category.equalsIgnoreCase("politics")&&!category.equalsIgnoreCase("sports")&&!category.equalsIgnoreCase("technology")){
            return null;
        }
        for (NewsArticle newsArticle : newsArticles) {
            if (newsArticle.getCategory().equalsIgnoreCase(category)) {
                foundNewsArticles.add(newsArticle);
            }
        }
        if (foundNewsArticles.isEmpty()) {
            return null;
        }
        return foundNewsArticles;
    }

    public ArrayList<NewsArticle> getPublishedNewsArticles() {
        ArrayList<NewsArticle> foundNewsArticles = new ArrayList<>();
        for (NewsArticle newsArticle : newsArticles) {
            if (newsArticle.isPublished()) {
                foundNewsArticles.add(newsArticle);
            }
        }
        if (foundNewsArticles.isEmpty()) {
            return null;
        }
        return foundNewsArticles;
    }

    public void addNewsArticle(NewsArticle newsArticle){
        newsArticles.add(newsArticle);
    }

    public boolean updateNewsArticle(NewsArticle newsArticle, int id){
        for (int i = 0; i < newsArticles.size(); i++) {
            if (newsArticles.get(i).getId() == id) {
                newsArticles.set(i, newsArticle);
                return true;
            }
        }
        return false;
    }

    public boolean publishNewsArticle(int id){

        for (int i = 0; i < newsArticles.size(); i++) {
            if (newsArticles.get(i).isPublished()) { //if already published
                return false;
            }

            if (newsArticles.get(i).getId() == id) {
                newsArticles.get(i).setPublished(true);
                return true;
            }
        }
        return false;
    }

    public boolean deleteNewsArticle(int id){
        for (int i = 0; i < newsArticles.size(); i++) {
            if (newsArticles.get(i).getId() == id) {
                newsArticles.remove(i);
                return true;
            }
        }
        return false;
    }
}
