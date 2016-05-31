package com.dcx.poz.dao;

import java.util.List;

import com.dcx.poz.model.Poem;
import com.dcx.poz.util.PageEntity;
import com.dcx.poz.util.Pager;

public interface PoemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Poem record);

    int insertSelective(Poem record);

    Poem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Poem record);

    int updateByPrimaryKey(Poem record);

	List<Poem> getPoemList(PageEntity<Poem> pageEntity);

	Integer getPoemListCount(PageEntity<Poem> pageEntity);

	List<Poem> getPoemPage(Pager<Integer> pager);
}