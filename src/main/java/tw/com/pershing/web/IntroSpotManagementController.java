package tw.com.pershing.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.com.pershing.domain.IntroSpot;
import tw.com.pershing.service.IntroSpotService;

@Controller
public class IntroSpotManagementController {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	IntroSpotService introSpotService;

    @GetMapping("/introSpot")
    public String introSpotPage(Model model) {
    	model.addAttribute("introSpotList", introSpotService.getIntroSpotList());
    	model.addAttribute("user", "guest");
    	model.addAttribute("title", "大推景點");
    	model.addAttribute("pageTitle", "大推景點");
    	model.addAttribute("footer", "商業發展研究院");
    	return "introSpot";
    }
    
    @PostMapping("/introSpot/update")
    @ResponseBody
    public IntroSpot updateIntroSpot(@RequestBody IntroSpot introSpot) {
    	LOGGER.debug("post update");
    	IntroSpot result = introSpotService.updateIntroSpot(introSpot);
        return result;
    }
    
    @PostMapping("/introSpot/delete")
    @ResponseBody
    public IntroSpot deleteIntroSpot(@RequestBody IntroSpot introSpot) {
    	LOGGER.debug("post delete");
    	IntroSpot result = introSpotService.deleteIntroSpot(introSpot);
        return result;
    }
}
