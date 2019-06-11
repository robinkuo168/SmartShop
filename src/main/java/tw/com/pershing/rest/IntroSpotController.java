package tw.com.pershing.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.com.pershing.domain.IntroSpot;
import tw.com.pershing.repository.IntroSpotRepo;
import tw.com.pershing.service.IntroSpotService;

@RestController
@RequestMapping("/introSpot")
public class IntroSpotController {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	IntroSpotRepo introMananger;
	
	@Autowired
	IntroSpotService introService;
	

	@RequestMapping(value = "/findById")
	public List<IntroSpot> findIntroSpotById(@RequestParam(value = "id") String introSpotId) {
		return introService.getIntroSpotListById(introSpotId);
	}

	@RequestMapping(value = "/list")
	public List<IntroSpot> getIntroSpotList() {
		return introService.getIntroSpotList();
	}
}
