package tw.com.pershing.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.pershing.domain.IntroSpot;
import tw.com.pershing.repository.IntroSpotRepo;

@Service
public class IntroSpotService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	IntroSpotRepo repository;
	
	public List<IntroSpot> getIntroSpotList() {
		return repository.getIntroSpot();
	}
	
	public List<IntroSpot> getIntroSpotListById(String introSpotId) {
		return repository.findIntroSpotById(Integer.valueOf(introSpotId));
	}
	
	public boolean introSpotIdExist(final Integer introSpotId) {
        return repository.findIntroSpotById(introSpotId).size() > 0;
    }
	
	public IntroSpot updateIntroSpot(final IntroSpot introSpot) {
        final IntroSpot intro = new IntroSpot();
        
        intro.setFloor(introSpot.getFloor());
        intro.setTitle(introSpot.getTitle());
        intro.setPhoto(introSpot.getPhoto());
        intro.setDescription(introSpot.getDescription());
        
        if (introSpotIdExist(introSpot.getIntroSpotId())) {
        	intro.setIntroSpotId(introSpot.getIntroSpotId());
        }
        
        final IntroSpot returnIntro = repository.saveIntroSpot(intro);
        return returnIntro;
    }
	
	public IntroSpot deleteIntroSpot(final IntroSpot introSpot) {
        final IntroSpot intro = new IntroSpot();
        
        intro.setTitle(introSpot.getTitle());
        
        if (introSpotIdExist(introSpot.getIntroSpotId())) {
        	intro.setIntroSpotId(introSpot.getIntroSpotId());
        }
        
        final IntroSpot returnIntro = repository.deleteIntroSpot(intro);
        return returnIntro;
    }
}
