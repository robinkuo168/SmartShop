package tw.com.pershing.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.pershing.domain.Position;
import tw.com.pershing.repository.PositionRepo;

@Service
public class PositionService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	PositionRepo repository;
	
	public List<Position> getPositionList() {
		return repository.getPosition();
	}
	
	public List<Position> getPositionListById(String positionId) {
		return repository.findPositionById(Integer.valueOf(positionId));
	}
}
