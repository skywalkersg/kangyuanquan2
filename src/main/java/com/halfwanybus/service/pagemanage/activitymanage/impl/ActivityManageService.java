package com.halfwanybus.service.pagemanage.activitymanage.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import com.halfwanybus.util.Jurisdiction;
import org.springframework.stereotype.Service;
import com.halfwanybus.dao.DaoSupport;
import com.halfwanybus.entity.Page;
import com.halfwanybus.util.PageData;
import com.halfwanybus.service.pagemanage.activitymanage.ActivityManageManager;

/** 
 * 说明： 活动公告管理
 * 创建人：liangzhilin
 * 创建时间：2016-11-27
 * @version
 */
@Service("activitymanageService")
public class ActivityManageService implements ActivityManageManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		pd.put("ADDTIME",new Date());
		pd.put("ADDUSER", Jurisdiction.getUsername());
		dao.save("ActivityManageMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("ActivityManageMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("ActivityManageMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("ActivityManageMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("ActivityManageMapper.listAll", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("ActivityManageMapper.findById", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("ActivityManageMapper.deleteAll", ArrayDATA_IDS);
	}
	
}

