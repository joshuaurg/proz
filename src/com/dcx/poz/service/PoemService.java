package com.dcx.poz.service;
 
import java.util.List;

import com.dcx.poz.model.Poem;
import com.dcx.poz.util.PageEntity;
import com.dcx.poz.util.PageResult;
import com.dcx.poz.util.Pager;

public interface PoemService {

	PageResult<Poem> getPoemList(PageEntity<Poem> pageEntity);

	void savePoem(Poem poem);

	void delPoem(String id);

	Poem getPoemById(int id);

	void updatePoem(Poem poem);

	List<Poem> getPoemPage(Pager<Integer> pager);

}
