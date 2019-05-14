package tw.com.pershing.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import tw.com.pershing.domain.Position;
import tw.com.pershing.service.PositionService;

@Controller
public class PositionManagementController {
	
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
    
    @PostMapping("/update")
    public String updatePosition(@RequestBody Position position, Model model) {
    	Position result = positionService.updatePosition(position);
    	model.addAttribute("position", result);
        return "helloworld";
    }
}
