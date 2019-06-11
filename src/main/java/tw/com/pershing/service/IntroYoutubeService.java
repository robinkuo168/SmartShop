package tw.com.pershing.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.pershing.domain.IntroYoutube;
import tw.com.pershing.repository.IntroYoutubeRepo;

@Service
public class IntroYoutubeService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	IntroYoutubeRepo repository;
	
	public List<IntroYoutube> getIntroYoutubeList() {
		return repository.getIntroYoutube();
	}
	
	public List<IntroYoutube> getIntroYoutubeListById(String introId) {
		return repository.findIntroYoutubeById(Integer.valueOf(introId));
	}
	
	public boolean introIdExist(final Integer introId) {
        return repository.findIntroYoutubeById(introId).size() > 0;
    }
	
	public IntroYoutube updateIntroYoutube(final IntroYoutube introYoutube) {
        final IntroYoutube intro = new IntroYoutube();
        
        intro.setName(introYoutube.getName());
        intro.setUrl(introYoutube.getUrl());
        
        if (introIdExist(introYoutube.getIntroId())) {
        	intro.setIntroId(introYoutube.getIntroId());
        }
        
        final IntroYoutube returnIntro = repository.saveIntroYoutube(intro);
        return returnIntro;
    }
	
	public IntroYoutube deleteIntroYoutube(final IntroYoutube introYoutube) {
        final IntroYoutube intro = new IntroYoutube();
        
        intro.setName(introYoutube.getName());
        
        if (introIdExist(introYoutube.getIntroId())) {
        	intro.setIntroId(introYoutube.getIntroId());
        }
        
        final IntroYoutube returnIntro = repository.deleteIntroYoutube(introYoutube);
        return returnIntro;
    }
}
