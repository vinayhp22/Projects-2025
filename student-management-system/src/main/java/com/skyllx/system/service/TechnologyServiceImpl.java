package com.skyllx.system.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skyllx.system.dto.TechnologyListDTO;
import com.skyllx.system.entity.TechnolgyListEntity;
import com.skyllx.system.repository.TechnologyRepo;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TechnologyServiceImpl implements TechnologyService {

	@Autowired
	private TechnologyRepo repo;

	@Override
	public Set<ConstraintViolation<TechnologyListDTO>> validateAndAdd(TechnologyListDTO dto) {
		log.info("validateAndAdd in TechnologyServiceImpl");

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<TechnologyListDTO>> violations = validator.validate(dto);
		if (violations != null && !violations.isEmpty()) {
			log.info("Violations in dto" + dto);
			return violations;
		}
		TechnolgyListEntity entity = new TechnolgyListEntity();
		BeanUtils.copyProperties(dto, entity);
		boolean add = this.repo.add(entity);
		if (add) {
			log.info("Technology added successfully");
		}
		return Collections.emptySet();
	}

	@Override
	public List<TechnologyListDTO> listById(int id) {
		log.info("findById " + id);
		List<TechnolgyListEntity> entities = this.repo.listById(id);
		log.info("entities.size() : " + entities.size());
		List<TechnologyListDTO> dtos = new ArrayList<>();
		if (!entities.isEmpty()) {
			for (TechnolgyListEntity entity : entities) {
				TechnologyListDTO dto = new TechnologyListDTO();
				BeanUtils.copyProperties(entity, dto);
				dtos.add(dto);
			}
			log.info("dtos.size() : " + dtos.size());
			return dtos;
		}
		return TechnologyService.super.listById(id);
	}
}
