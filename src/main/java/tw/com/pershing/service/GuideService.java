package tw.com.pershing.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.pershing.domain.Guide;
import tw.com.pershing.repository.GuideRepo;

@Service
public class GuideService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	GuideRepo repository;
	
	public List<Guide> getGuideList() {
		return repository.getGuide();
	}
	
	public List<Guide> getGuideListById(String guideId) {
		return repository.findGuideById(Integer.valueOf(guideId));
	}
}
