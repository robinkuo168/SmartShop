package tw.com.pershing.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.com.pershing.domain.Guide;
import tw.com.pershing.repository.GuideRepo;
import tw.com.pershing.service.GuideService;

@RestController
@RequestMapping("/guide")
public class GuideController {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	GuideRepo guideMananger;
	
	@Autowired
	GuideService guideService;
	

	@RequestMapping(value = "/findById")
	public List<Guide> findGuideById(@RequestParam(value = "id") String guideId) {
		return guideService.getGuideListById(guideId);
	}

	@RequestMapping(value = "/list")
	public List<Guide> getGuideList() {
		return guideService.getGuideList();
	}
}
