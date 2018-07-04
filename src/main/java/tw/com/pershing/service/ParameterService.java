package tw.com.pershing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.pershing.domain.Parameter;
import tw.com.pershing.repository.ParameterRepo;

@Service
public class ParameterService {
	
	@Autowired
	ParameterRepo repository;
	
	public List<Parameter> findParameterByKind(String kind){
		final List<Parameter> parameter = repository.findParameterByKind(kind);
		if (parameter != null) {
			return parameter;
		}
		return null;
	}
}
