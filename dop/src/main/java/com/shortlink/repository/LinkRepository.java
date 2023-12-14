package com.shortlink.repository;

import com.shortlink.models.Link;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class LinkRepository {
    private final Map<Long, Link> links = new HashMap<>();
    public Iterable<Link> findAll(){
        return links.values();
    }
    public String getShortLinkNoDomen(String shortLink) {
        return shortLink.split("/")[2];
    }
    public void save(Link product){
        links.put(product.getId(), product);
    }
    public void update(Long id, Link product){
        links.replace(id, product);
    }
    public void deleteByShortLink(String shortLink){
        links.remove(shortLink);
    }
    public Long getCount(){
        return (long) links.size();
    }
    public String getMainLinkByShort(String shortLink) throws Exception {

        for (Link el: links.values()  ) {
            if(getShortLinkNoDomen(el.getShortLink()).equals(shortLink)){
                return el.getMainLink();
            }
        }

        throw new Exception("Error url");

    }
    public void setUseLinkByShort(String shortLink){

        for (Link el: links.values()  ) {
            if(getShortLinkNoDomen(el.getShortLink()).equals(shortLink)){
                el.setUseCount();
            }
        }
    }
    public Link getById(Long id){
        return links.get(id);
    }
    public void deleteById(Long id){
        links.remove(id);
    }
}
