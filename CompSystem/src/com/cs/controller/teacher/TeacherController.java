package com.cs.controller.teacher;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.cs.dao.competition.CompetitionMapper;
import com.cs.dao.message.MessageMapper;
import com.cs.pojo.Awards;
import com.cs.pojo.Budget;
import com.cs.pojo.Competition;
import com.cs.pojo.Conditions;
import com.cs.pojo.Department;
import com.cs.pojo.FileUpload;
import com.cs.pojo.Groups;
import com.cs.pojo.Hours;
import com.cs.pojo.Material;
import com.cs.pojo.Message;
import com.cs.pojo.Schedule;
import com.cs.pojo.Student;
import com.cs.pojo.Teacher;
import com.cs.service.condition.ConditionService;
import com.cs.service.groups.GroupsService;
import com.cs.service.teacher.TeacherService;
import com.cs.vo.CompetitionInfoVo;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
	/**
	 * 1.查看、修改教师个人信息√ 
	 * 2.管理申报表。 
	 *   2.1）查看所有申报书审批结果（列表以及结果。）√
	 *     2.1.1）根据结果查看申报书，比如，查找所有通过的申报书√ 
	 *     2.1.2）查看申报书详情√ 
	 *   2.2）新增 √
	 *   2.3）修改√、删除√。仅限申报不通过时。
	 * 3.查看审批通过的竞赛。 ——》2.1.1）根据结果查看申报书：这两个差不多
	 *   3.1）查看某个竞赛参与的组别。√ 
	 *     3.1.1）查看某个组别中的成员√ 
	 *   3.2）管理（删除/允许）某个报名竞赛的组别√
	 * 4.查看已经结束的竞赛。√ 
	 *   4.1）反馈竞赛结果 √ 
	 * 5.导出申报表√
	 * 6.审批员的功能
	 *    6.1系主任审批申报书√
	 *    6.2教学处审批申报书√
	 * 7.查找所有的教师√
	 * 8.获取所有的条件√
	 * 9.获取个人消息。√
	 */

	@Autowired
	private TeacherService teacherService;
	@Autowired
	private GroupsService groupsService;
	@Autowired
	private ConditionService conditionService;
	@Autowired
	private MessageMapper messageMapper;
	@Autowired  
    private HttpServletRequest request; 
	@Autowired  
	private CompetitionMapper comMapper;

	/**
	 * 1.查看教师个人信息
	 * 
	 * @param teacherNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getTeacherInfo")
	public Teacher getTeacherInfo(Integer teacherNo) {
		return teacherService.selectByTeacherNo(teacherNo);
	}

	/**
	 * 1.修改教师信息
	 */
	@ResponseBody
	@RequestMapping(value = "/updateTeacherInfo")
	public boolean updateTeacherInfo(@RequestBody Teacher teacher) {
		return teacherService.updateByTeacherNo(teacher);
	}

	/**
	 * 2.1根据teacherno查看申报列表及结果
	 * 
	 * @param teacherNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getCompResult")
	public List<Competition> getCompResult(Integer teacherNo) {
		return teacherService.getCompResults(teacherNo);
	}

	/**
	 * 2.1.1）根据申报结果查看申报书，比如，查找所有通过的申报书三个条件
	 * competition.setDepspstatus(depStatus);
	 *	competition.setTeaspstatus(teaStatusInteger);
	 *	competition.setTeacherno(1);
	 * @param competition
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getCompBySpStatus")
	public List<Competition> getCompBySpStatus(@RequestBody Competition competition) {
		return teacherService.getCompBySpStatus(competition);
	}

	/**
	 * 2.1.2）查看申报书详情
	 * 
	 * @param comId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getCompDetail")
	public Map<String, Object> getCompDetail(Integer comId) {
		return teacherService.getCompDetail(comId);
	}

	/**
	 * 2.2）新增申报书
	 * 
	 * @param comId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addComp")
	public void addComp(@RequestBody Competition competition) {
		System.out.println(competition.getComname());
		//teacherService.addComp(competitionInfovo);
	}

	/**
	 * 2.3删除申报表
	 * 
	 * @param comId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteComp")
	public void deleteComp(Integer comId) {
		teacherService.deleteComp(comId);
	} 
	
	/**
	 * 2.3）修改申报书
	 * 
	 * @param comId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateComp")
	public void updateComp(@RequestBody CompetitionInfoVo compVo) {
		teacherService.updateComp(compVo);
	}

	/**
	 * 3.1）查看某个竞赛参与的组别。
	 * 
	 * @param comId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getCompGroups")
	public List<Groups> getCompGroups(Integer comId) {
		return groupsService.getCompGroups(comId);
	}

	/**
	 * 3.1.1）查看某个组别中的成员
	 * 
	 * @param groupsNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getGroupsMember")
	public List<Student> getGroupsMember(Integer groupsNo) {
		return teacherService.getGroupsMember(groupsNo);
	}

	/**
	 * 3.2）管理（删除/允许）某个报名竞赛的组别
	 * 
	 * @param teacherNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/manageGroupsMember")
	public boolean manageGroupsMember(@RequestBody Groups groups) {
		return teacherService.approveGroups(groups);
	}

	/**
	 * 4.查看已经结束的竞赛。
	 * 
	 * @param teacherNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getEndCompetition")
	public List<Competition> getEndCompetition(Integer teacherNo) {
		return teacherService.getEndCompetition(teacherNo);
	}

	/**
	 * 4.1）反馈竞赛结果
	 * 
	 * @param awards
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/setCompResult")
	public void setCompResult(@RequestParam("file") MultipartFile file,Awards awards) throws IllegalStateException, IOException{
		// 判断文件是否为空  
        if (!file.isEmpty()) {  
            try {  
            	//文件名
            	String fileName="fileUpload/awards/"+UUID.randomUUID().toString()+".jpg";  
                // 文件保存路径  
                String filePath = request.getSession().getServletContext().getRealPath("/") +fileName;
                // 转存文件  
                file.transferTo(new File(filePath));  
				
				//保存进数据库
				awards.setAwardsimg(fileName);
				awards.setIspublish(0);
				teacherService.setCompResult(awards);
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
		
	}
	
	/**
	 * 5.导出申报表
	 * 
	 * @param comId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getAprroveTable")
	public boolean getAprroveTable(Integer comId) {
		return teacherService.createWord(comId);
	}
	
	/**
	 *测试
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value ="/getAprrove")    
	public  void getAprrove(Integer comId,HttpServletResponse response) throws UnsupportedEncodingException {
		
	}
	
	/**
	 * 6.1系主任审批申报书
	 * @param comId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateCompStatusByDept")
	public void updateCompStatusByDept(@RequestBody Competition comp) {
		teacherService.updateCompStatusByDept(comp);
	}
	
	/**
	 * 6.2教学处审批申报书
	 * @param comId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateCompStatusByTea")
	public void updateCompStatusByTea(@RequestBody Competition comp) {
		teacherService.updateCompStatusByTea(comp);
	}
	
	 
	 /**
	 * 7.查找所有的教师
     * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getAllTeacher")
	public List<Teacher> getAllTeacher() {
		return teacherService.selectAllTeacher();
	}
	
	/**
	 * 8.获取所有的条件√
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getAllConditions")
	public List<Conditions> getAllConditions(){
	    return conditionService.getAllCondition();
	}
	
	/**
	 * 9.获取个人消息。√
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getAllMessage")
	public List<Message> getAllMessage(String no){
	    return messageMapper.selectBysendtoNo(no);
	}
	
}
