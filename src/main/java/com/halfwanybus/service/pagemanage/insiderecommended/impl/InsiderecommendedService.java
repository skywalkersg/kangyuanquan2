package com.halfwanybus.service.pagemanage.insiderecommended.impl;

import com.halfwanybus.dao.DaoSupport;
import com.halfwanybus.entity.Page;
import com.halfwanybus.service.pagemanage.insiderecommended.InsiderecommendedManager;
import com.halfwanybus.util.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/** 
 * 说明： 站内推荐表
 * 创建人：liangzhilin
 * 创建时间：2016-08-24
 * @version
 */
@Service("insiderecommendedService")
public class InsiderecommendedService implements InsiderecommendedManager {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		Integer sort = this.getMaxSort();
		if(sort != null)
			pd.put("SORT", sort+1);
		else 
			pd.put("SORT", 1);
		dao.save("InsiderecommendedMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("InsiderecommendedMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("InsiderecommendedMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("InsiderecommendedMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("InsiderecommendedMapper.listAll", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("InsiderecommendedMapper.findById", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("InsiderecommendedMapper.deleteAll", ArrayDATA_IDS);
	}
	
	/**
	 * 置顶
	 */
	public void editMaxSort(PageData pd) throws Exception {
		pd = this.findById(pd);
		Integer sort = this.getMaxSort();
		if((Integer) pd.get("SORT") != sort) {
			pd.put("SORT", sort+1);
			this.edit(pd);;	//根据ID读取
		}
	}
	
	/**
	 * 获取优先数最大的数
	 */
	public Integer getMaxSort() throws Exception {
		return (Integer)dao.findForObject("InsiderecommendedMapper.getMaxSort", null);
	}
	
	/**
	 * 通过SPECIAL_ID删除item
	 */
	public void deleteBySpecialId(String specialId) throws Exception {
		dao.delete("InsiderecommendedMapper.deleteBySpecialId", specialId);
	}
	
	/**
	 * 通过SPECIAL_ID数组批量删除item
	 */
	public void deleteAllBySpecialIds(String[] ids) throws Exception {
		dao.delete("InsiderecommendedMapper.deleteAllBySpecialIds", ids);
	}
	
}

