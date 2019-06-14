package tw.com.pershing.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.com.pershing.domain.Position;
import tw.com.pershing.service.PositionService;

@Controller
public class PositionManagementController {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	PositionService positionService;
    
    @GetMapping("/template")
    public String helloWorld(@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {
    	model.addAttribute("user", name);
    	model.addAttribute("title", "title");
    	model.addAttribute("pageTitle", "Template");
    	model.addAttribute("footer", "footer");
    	return "template";
    }
    
    @GetMapping("/position")
    public String positionPage(Model model) {
    	model.addAttribute("positionList", positionService.getPositionList());
    	model.addAttribute("user", "guest");
    	model.addAttribute("title", "櫃位");
    	model.addAttribute("pageTitle", "櫃位");
    	model.addAttribute("footer", "商業發展研究院");
    	return "position";
    }
    
    @PostMapping("/position/update")
    @ResponseBody
    public Position updatePosition(@RequestBody Position position) {
    	LOGGER.debug("post update");
    	Position result = positionService.updatePosition(position);
        return result;
    }
    
    @PostMapping("/position/delete")
    @ResponseBody
    public Position deletePosition(@RequestBody Position position) {
    	LOGGER.debug("post delete");
    	Position result = positionService.deletePosition(position);
        return result;
    }
}
