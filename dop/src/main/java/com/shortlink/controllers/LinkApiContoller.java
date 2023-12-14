package com.shortlink.controllers;

import com.shortlink.models.Link;
import com.shortlink.service.DBException;
import com.shortlink.service.DBService;
import com.shortlink.service.LinkService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class LinkApiContoller {
    @Autowired
    DBService dbService;
    @Autowired
    LinkService linkService;
    @GetMapping("/list")
    private Iterable<Link> list() throws DBException {
        return dbService.getAllLinks();
//        return linkService.findAllLinks();
    }
    @GetMapping("/get/{id}")
    private Link getLink(@PathVariable("id") Long id) throws DBException {
//        return linkService.getById(id);
        return dbService.getLink(id);
    }
    @PostMapping("/")
    private void addProduct(@RequestBody JSONObject jsonProduct) throws DBException {
        var name = jsonProduct.get("link-name").toString();
        var desc = jsonProduct.get("link-desc").toString();
        dbService.addLink(name, desc);
//        linkService.save(name, desc);
    }
    @PutMapping("/")
    private void updateProduct(@RequestBody JSONObject jsonProduct) throws DBException {
        var name = jsonProduct.get("link-name").toString();
        var desc = jsonProduct.get("link-desc").toString();
        var id = jsonProduct.get("link-id").toString();
//        linkService.update(Long.parseLong(id), name, desc);
        dbService.updateLink(Long.parseLong(id), name, desc);
    }
    @DeleteMapping("/{id}")
    private void delete(@PathVariable("id") Long id) throws DBException {
        dbService.deleteLink(id);
//        linkService.delete(id);
    }

    @GetMapping("/l/{shortUrl}")
    public ResponseEntity getAndRedirect(@PathVariable String shortUrl) throws Exception {
//        var url = linkService.getOriginalUrl(shortUrl);
        var url = dbService.getOriginalUrl(shortUrl);
//        linkService.setUseLink(shortUrl);
        dbService.setUseLink(shortUrl);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(url))
                .build();
    }
}
