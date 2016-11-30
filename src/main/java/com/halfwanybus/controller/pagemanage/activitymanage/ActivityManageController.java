package com.halfwanybus.controller.pagemanage.activitymanage;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.halfwanybus.service.system.tools.singleImg.SingleImgManager;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.halfwanybus.controller.base.BaseController;
import com.halfwanybus.entity.Page;
import com.halfwanybus.util.AppUtil;
import com.halfwanybus.util.ObjectExcelView;
import com.halfwanybus.util.PageData;
import com.halfwanybus.util.Jurisdiction;
import com.halfwanybus.util.Tools;
import com.halfwanybus.service.pagemanage.activitymanage.ActivityManageManager;

/** 
 * 说明：活动公告管理
 * 创建人：liangzhilin
 * 创建时间：2016-11-27
 */
@Controller
@RequestMapping(value="/activitymanage")
public class ActivityManageController extends BaseController {
	
	String menuUrl = "activitymanage/list.do"; //菜单地址(权限用)
	@Resource(name="activitymanageService")
	private ActivityManageManager activitymanageService;
	@Resource(name="singleImgService")
	private SingleImgManager singleImgService;
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save(
			@RequestParam(value="image",required=false) CommonsMultipartFile image, HttpSession session,
			@RequestParam(value="imgChange",required=false) String imgChange,
			@RequestParam(value="TITLE",required=false) String TITLE,
			@RequestParam(value="DESCRIBES",required=false) String DESCRIBES,
			@RequestParam(value="ISSHOW",required=false) String ISSHOW,
			@RequestParam(value="RELEASEPEOPLE",required=false) String RELEASEPEOPLE
	) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增ActivityManage");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String imageUrl = null;
		if(image.getSize()>0){
			String uploadPath ="/static/images";
			String realUploadPath = session.getServletContext().getRealPath(uploadPath);
			imageUrl = singleImgService.uploadImage(image, uploadPath, realUploadPath,this.get32UUID());
			session.removeAttribute("image");
		}
		pd.put("ACTIVITYMANAGE_ID",this.get32UUID());
		pd.put("TITLE",TITLE);
		pd.put("DESCRIBES",DESCRIBES);
		pd.put("ISSHOW",ISSHOW);
		pd.put("RELEASEPEOPLE",RELEASEPEOPLE);
		pd.put("IMG_PATH",imageUrl);
		activitymanageService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**删除
	 * @param out
	 * @throws Exception
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"删除ActivityManage");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		activitymanageService.delete(pd);
		out.write("success");
		out.close();
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(
			@RequestParam(value="image",required=false) CommonsMultipartFile image, HttpSession session,
			@RequestParam(value="imgChange",required=false) String imgChange,
			@RequestParam(value="ACTIVITYMANAGE_ID",required=false) String ACTIVITYMANAGE_ID,
			@RequestParam(value="TITLE",required=false) String TITLE,
			@RequestParam(value="DESCRIBES",required=false) String DESCRIBES,
			@RequestParam(value="ISSHOW",required=false) String ISSHOW,
			@RequestParam(value="IMG_PATH",required=false) String IMG_PATH,
			@RequestParam(value="RELEASEPEOPLE",required=false) String RELEASEPEOPLE
							 ) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改ActivityManage");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd.put("ACTIVITYMANAGE_ID",ACTIVITYMANAGE_ID);
		pd = activitymanageService.findById(pd);
		pd.put("TITLE",TITLE);
		pd.put("DESCRIBES",DESCRIBES);
		pd.put("ISSHOW",ISSHOW);
		pd.put("RELEASEPEOPLE",RELEASEPEOPLE);
		//图片更新
		String imageUrl = null;
		if(image!=null){
			if(image.getSize()>0){
				String uploadPath ="/static/images";
				String realUploadPath = session.getServletContext().getRealPath(uploadPath);
				imageUrl = singleImgService.uploadImage(image, uploadPath, realUploadPath,this.get32UUID());
				pd.put("IMG_PATH", imageUrl);//覆盖
				session.removeAttribute("image");
			}else {
				System.out.println(imgChange);
				if(imgChange!=null){//前台页面点击了重新选择图片，只是没有选图片
					pd.put("IMG_PATH", null);//覆盖
				}
			}
		}
		activitymanageService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表ActivityManage");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		page.setPd(pd);
		List<PageData>	varList = activitymanageService.list(page);	//列出ActivityManage列表
		mv.setViewName("pagemanage/activitymanage/activitymanage_list");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	/**去新增页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.setViewName("pagemanage/activitymanage/activitymanage_edit");
		mv.addObject("msg", "save");
		mv.addObject("pd", pd);
		return mv;
	}	
	
	 /**去修改页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goEdit")
	public ModelAndView goEdit()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd = activitymanageService.findById(pd);	//根据ID读取
		mv.setViewName("pagemanage/activitymanage/activitymanage_edit");
		mv.addObject("msg", "edit");
		mv.addObject("pd", pd);
		return mv;
	}	
	
	 /**批量删除
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"批量删除ActivityManage");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			activitymanageService.deleteAll(ArrayDATA_IDS);
			pd.put("msg", "ok");
		}else{
			pd.put("msg", "no");
		}
		pdList.add(pd);
		map.put("list", pdList);
		return AppUtil.returnObject(pd, map);
	}
	
	 /**导出到excel
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"导出ActivityManage到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("标题");	//1
		titles.add("描述");	//2
		titles.add("是否展示在首页");	//3
		titles.add("添加时间");	//4
		titles.add("添加人");	//5
		titles.add("发布人");	//6
		dataMap.put("titles", titles);
		List<PageData> varOList = activitymanageService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("TITLE"));	//1
			vpd.put("var2", varOList.get(i).getString("DESCRIBES"));	//2
			vpd.put("var3", varOList.get(i).getString("ISSHOW"));	//3
			vpd.put("var4", varOList.get(i).getString("ADDTIME"));	//4
			vpd.put("var5", varOList.get(i).getString("ADDUSER"));	//5
			vpd.put("var6", varOList.get(i).getString("RELEASEPEOPLE"));	//6
			varList.add(vpd);
		}
		dataMap.put("varList", varList);
		ObjectExcelView erv = new ObjectExcelView();
		mv = new ModelAndView(erv,dataMap);
		return mv;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
