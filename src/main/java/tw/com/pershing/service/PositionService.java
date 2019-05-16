package tw.com.pershing.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.pershing.domain.Customer;
import tw.com.pershing.domain.MemberResponse;
import tw.com.pershing.domain.Position;
import tw.com.pershing.repository.PositionRepo;
import tw.com.pershing.web.error.UserAlreadyExistException;

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
	
	public boolean positionIdExist(final Integer positionId) {
        return repository.findPositionById(positionId).size() > 0;
    }
	
	public Position updatePosition(final Position position) {
        final Position pst = new Position();
        
        pst.setBrand(position.getBrand());
        pst.setDistrict(position.getDistrict());
        pst.setBlock(position.getBlock());
        pst.setBrandEqual(position.getBrandEqual());
        pst.setDistrictEqual(position.getDistrictEqual());
        pst.setBlockEqual(position.getBlockEqual());
        
        if (positionIdExist(position.getPositionId())) {
        	pst.setPositionId(position.getPositionId());
        }
        
        final Position returnPosition = repository.savePosition(pst);
        return returnPosition;
    }
	
	public Position deletePosition(final Position position) {
        final Position pst = new Position();
        
        pst.setBrand(position.getBrand());
        
        if (positionIdExist(position.getPositionId())) {
        	pst.setPositionId(position.getPositionId());
        }
        
        final Position returnPosition = repository.deletePosition(pst);
        return returnPosition;
    }
}
