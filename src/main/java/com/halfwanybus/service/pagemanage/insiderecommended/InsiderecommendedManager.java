package com.halfwanybus.service.pagemanage.insiderecommended;

import java.util.List;

import com.halfwanybus.entity.Page;
import com.halfwanybus.util.PageData;

/** 
 * 说明： 站内推荐表接口
 * 创建人：liangzhilin
 * 创建时间：2016-08-24
 * @version
 */
public interface InsiderecommendedManager{

	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception;
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception;
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception;
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	public List<PageData> list(Page page)throws Exception;
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	public List<PageData> listAll(PageData pd)throws Exception;
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception;
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception;
	
	/**
	 * 置顶
	 */
	public void editMaxSort(PageData pd) throws Exception;
	
	/**
	 * 获取优先数最大的数
	 */
	public Integer getMaxSort() throws Exception;
	
	/**
	 * 通过SPECIAL_ID删除item
	 */
	public void deleteBySpecialId(String specialId) throws Exception;
	
	/**
	 * 通过SPECIAL_ID数组批量删除item
	 */
	public void deleteAllBySpecialIds(String[] ids) throws Exception;
}

