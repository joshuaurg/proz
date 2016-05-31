package com.dcx.poz.service.impl;

import com.dcx.poz.dao.PoemMapper;
import com.dcx.poz.model.Poem;
import com.dcx.poz.service.PoemService;
import com.dcx.poz.util.ConstantUtil;
import com.dcx.poz.util.PageEntity;
import com.dcx.poz.util.PageResult;
import com.dcx.poz.util.PageUtil;
import com.dcx.poz.util.Pager;
import com.dcx.poz.util.StringUtil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @description 
 * @author dcx
 * @date 2016年2月26日 下午10:12:48
 */
@Service
@Transactional
public class PoemServiceImpl implements PoemService {

	@Autowired
	PoemMapper poemMapper;

	@Override
	public PageResult<Poem> getPoemList(PageEntity<Poem> pageEntity) {
		PageResult<Poem> pageResult=new PageResult<Poem>();
		List<Poem> rows = poemMapper.getPoemList(pageEntity);
		if(!StringUtil.isEmpty(rows)){
			for(Poem poem : rows){
				poem.setProfileImg(ConstantUtil.QINIU_IMG_PREFIX + poem.getProfileImg());
			}
		}
		pageResult.setRows(rows);
		pageResult.setRecords(poemMapper.getPoemListCount(pageEntity));
		pageResult.setPage(pageEntity.getPage());
		pageResult.setTotal(PageUtil.calcPagNum(pageResult.getRecords(), pageEntity.getRows()));
		return pageResult;
	}

	@Override
	public void savePoem(Poem poem) {
		poemMapper.insertSelective(poem); 
	}

	@Override
	public void delPoem(String id) {
		poemMapper.deleteByPrimaryKey(Integer.parseInt(id));
	}

	@Override
	public Poem getPoemById(int id) {
		return poemMapper.selectByPrimaryKey(id);
	}

	@Override
	public void updatePoem(Poem poem) {
		poemMapper.updateByPrimaryKeySelective(poem);
	}

	@Override
	public List<Poem> getPoemPage(Pager<Integer> pager) {
		return	poemMapper.getPoemPage(pager); 
	}
	
	
}
