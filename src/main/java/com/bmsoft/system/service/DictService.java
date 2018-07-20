package com.bmsoft.system.service;

import java.util.List;
import com.bmsoft.common.service.IService;
import com.bmsoft.system.domain.Dict;

public interface DictService extends IService<Dict> {

	List<Dict> findAllDicts(Dict dict);

	Dict findById(Long dictId);

	void addDict(Dict dict);

	void deleteDicts(String dictIds);

	void updateDict(Dict dicdt);
}
