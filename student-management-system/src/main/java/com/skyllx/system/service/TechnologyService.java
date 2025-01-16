package com.skyllx.system.service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import com.skyllx.system.dto.TechnologyListDTO;

public interface TechnologyService {

	Set<ConstraintViolation<TechnologyListDTO>> validateAndAdd(TechnologyListDTO dto);

	default List<TechnologyListDTO> listById(int id){
		return Collections.emptyList();
	}
}
