package tw.com.pershing.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.com.pershing.domain.Position;
import tw.com.pershing.repository.PositionRepo;
import tw.com.pershing.service.PositionService;

@RestController
@RequestMapping("/position")
public class PositionController {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	PositionRepo positionMananger;
	
	@Autowired
	PositionService positionService;
	

	@RequestMapping(value = "/findById")
	public List<Position> findPositionById(@RequestParam(value = "id") String positionId) {
		return positionService.getPositionListById(positionId);
	}

	@RequestMapping(value = "/list")
	public List<Position> getPositionList() {
		return positionService.getPositionList();
	}
}
