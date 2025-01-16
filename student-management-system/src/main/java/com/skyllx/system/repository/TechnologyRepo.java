package com.skyllx.system.repository;

import java.util.Collections;
import java.util.List;
import com.skyllx.system.entity.TechnolgyListEntity;

public interface TechnologyRepo {

	boolean add(TechnolgyListEntity entity);

	default List<TechnolgyListEntity> listById(int id){
		return Collections.emptyList();
	}
}
