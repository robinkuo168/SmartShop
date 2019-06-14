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

import tw.com.pershing.domain.IntroYoutube;
import tw.com.pershing.service.IntroYoutubeService;

@Controller
public class IntroYoutubeManagementController {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	IntroYoutubeService introYoutubeService;

    @GetMapping("/introYoutube")
    public String introYoutubePage(Model model) {
    	model.addAttribute("introYoutubeList", introYoutubeService.getIntroYoutubeList());
    	model.addAttribute("user", "guest");
    	model.addAttribute("title", "嚴選影片");
    	model.addAttribute("pageTitle", "嚴選影片");
    	model.addAttribute("footer", "商業發展研究院");
    	return "introYoutube";
    }
    
    @PostMapping("/introYoutube/update")
    @ResponseBody
    public IntroYoutube updateIntroYoutube(@RequestBody IntroYoutube introYoutube) {
    	LOGGER.debug("post update");
    	IntroYoutube result = introYoutubeService.updateIntroYoutube(introYoutube);
        return result;
    }
    
    @PostMapping("/introYoutube/delete")
    @ResponseBody
    public IntroYoutube deleteIntroYoutube(@RequestBody IntroYoutube introYoutube) {
    	LOGGER.debug("post delete");
    	IntroYoutube result = introYoutubeService.deleteIntroYoutube(introYoutube);
        return result;
    }
}
