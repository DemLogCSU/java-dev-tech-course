package com.shortlink.service;

import com.shortlink.models.Link;
import com.shortlink.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class LinkService {
    @Autowired
    LinkRepository linkRepository;
    @Autowired
    ConversionShortLink conversionShortLink;
    public Iterable<Link> findAllLinks() {
        return linkRepository.findAll();
    }
    public void save(String name, String desс) {
        linkRepository.save(new Link(linkRepository.getCount()+1, name, conversionShortLink.convert(name), desс));
    }
    public Link getById(Long id){
        return linkRepository.getById(id);
    }
    public void update(Long id, String name, String desс) {
        linkRepository.update(id, new Link(id, name, conversionShortLink.convert(name), desс));
    }
    public String getOriginalUrl(String shortLink) throws Exception {
        return linkRepository.getMainLinkByShort(shortLink);
    }
    public void setUseLink(String shortLink){
        linkRepository.setUseLinkByShort(shortLink);
    }
    public void delete(Long id){
        linkRepository.deleteById(id);
    }
}
