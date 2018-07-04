package tw.com.pershing.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.com.pershing.domain.Parameter;
import tw.com.pershing.repository.ParameterRepo;

@RestController
@RequestMapping("/parameter")
public class ParameterController {
	
	@Autowired
	ParameterRepo parameterRepo;
	
	@RequestMapping(value = "/param")
	public List<Parameter> byToken(@RequestParam(value = "kind") String kind) {
		return parameterRepo.findParameterByKind(kind);
	}
}
