package com.halfwanybus.controller.pagemanage.insiderecommended;

import com.halfwanybus.controller.base.BaseController;
import com.halfwanybus.entity.Page;

import com.halfwanybus.service.pagemanage.insiderecommended.InsiderecommendedManager;
import com.halfwanybus.service.system.tools.singleImg.SingleImgManager;
import com.halfwanybus.util.AppUtil;
import com.halfwanybus.util.Jurisdiction;
import com.halfwanybus.util.PageData;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/** 
 * 说明：站内推荐表
 * 创建人：liangzhilin
 * 创建时间：2016-08-24
 */
@Controller
@RequestMapping(value="/insiderecommended")
public class InsiderecommendedController extends BaseController {
	
	String menuUrl = "insiderecommended/list.do"; //菜单地址(权限用)
	@Resource(name="insiderecommendedService")
	private InsiderecommendedManager insiderecommendedService;
	@Resource(name="singleImgService")
	private SingleImgManager singleImgService;
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save(
			@RequestParam(value="image",required=false) CommonsMultipartFile image, HttpSession session,
			@RequestParam(value="LINK",required=false) String LINK
			) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增Insiderecommended");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("ADD_TIME", new Date());
		pd.put("ADD_USER", Jurisdiction.getUsername());
		pd.put("INSIDERECOMMENDED_ID", this.get32UUID());	//主键
		String imageUrl = null;
		if(image.getSize()>0){
			String uploadPath ="/static/images";
			String realUploadPath = session.getServletContext().getRealPath(uploadPath);
			imageUrl = singleImgService.uploadImage(image, uploadPath, realUploadPath,this.get32UUID());
			session.removeAttribute("image");
		}
		pd.put("IMG_PATH",imageUrl);
		insiderecommendedService.save(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"删除Insiderecommended");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		insiderecommendedService.delete(pd);
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
			@RequestParam(value="LINK",required=false) String LINK,
			@RequestParam(value="INSIDERECOMMENDED_ID",required=false) String INSIDERECOMMENDED_ID,
			@RequestParam(value="imgChange",required=false) String imgChange
	) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改Insiderecommended");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("INSIDERECOMMENDED_ID",INSIDERECOMMENDED_ID);
		pd = insiderecommendedService.findById(pd);
		pd.put("LINK",LINK);
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
		insiderecommendedService.edit(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"列表Insiderecommended");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("selectValue");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		page.setPd(pd);
		List<PageData>	varList = insiderecommendedService.list(page);	//列出Insiderecommended列表
		
		for(int i = 0; i < varList.size();i++ ) {
			if(varList.get(i).getString("LINK").length()>15)
			{
				String text = varList.get(i).getString("LINK").substring(0, 15)+"......";
				varList.get(i).put("LINK", text);
			}
		}
		PageData special = new PageData();
		mv.setViewName("pagemanage/insiderecommended/insiderecommended_list");
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
		PageData special = new PageData();
		mv.setViewName("pagemanage/insiderecommended/insiderecommended_edit");
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
		pd = insiderecommendedService.findById(pd);	//根据ID读取
		PageData special = new PageData();
		mv.setViewName("pagemanage/insiderecommended/insiderecommended_edit");
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
		logBefore(logger, Jurisdiction.getUsername()+"批量删除Insiderecommended");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			insiderecommendedService.deleteAll(ArrayDATA_IDS);
			pd.put("msg", "ok");
		}else{
			pd.put("msg", "no");
		}
		pdList.add(pd);
		map.put("list", pdList);
		return AppUtil.returnObject(pd, map);
	}
	
	/**去细阅页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goDetail")
	public ModelAndView goDetail()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd = insiderecommendedService.findById(pd);	//根据ID读取
		PageData special = new PageData();
		mv.setViewName("pagemanage/insiderecommended/insiderecommended_detail");
		mv.addObject("msg", "detail");
		mv.addObject("pd", pd);
		return mv;
	}
	
	/**
	 * 置顶
	 */
	@RequestMapping(value="/setTop")
	public void setTop(PrintWriter out) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"置顶Insiderecommended");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		insiderecommendedService.editMaxSort(pd);
		out.write("success");
		System.out.println("success");
		out.close();
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
