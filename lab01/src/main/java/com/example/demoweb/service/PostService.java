package com.example.demoweb.service;

import com.example.demoweb.model.Post;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PostService {
    public ArrayList<Post> listAllPosts() {
        ArrayList<Post> posts = new ArrayList<Post>();

        posts.add(new Post("В Африканском союзе объяснили, почему делегацию Израиля выгнали с саммита", 34));
        posts.add(new Post("В Вашингтоне началась акция против поддержки Украины", 54));
        posts.add(new Post("Пекин отказался мириться с попытками США надавить на отношения России и КНР", 57));

        return posts;
    }
}
