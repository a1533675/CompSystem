package com.cs.controller.student;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.cs.dao.awards.AwardsMapper;
import com.cs.dao.material.MaterialMapper;
import com.cs.pojo.Awards;
import com.cs.pojo.Conditions;
import com.cs.pojo.Groups;
import com.cs.pojo.Material;
import com.cs.pojo.Student;
import com.cs.pojo.Teacher;
import com.cs.service.condition.ConditionService;
import com.cs.service.groups.GroupsService;
import com.cs.service.student.StudentService;



@Controller
@RequestMapping("/student")
public class StudentController {
	/**
	 * 1.报名
	 *   1.1获取该竞赛所需的条件√
	 *   1.2判断该学生不符合的条件√
	 *   1.3个人信息：证书、学生证等的上传√
	 *   1.4报名竞赛
	 *      1.4.0获取所有队伍的信息√
	 *      1.4.1加入一个队伍.√
	 *          1.4.1.2取消加入队伍.(当队长长时间没有审核时，可取消加入队伍)√
	 *      1.4.2创建队伍。(当不需要组队时。为一人一组)√
	 *      1.4.3查找该竞赛所有指导老师。√
	 *      1.4.4队长审核加入的成员√
	 * 2.查看报名结果√
	 *     报名结果：即xx竞赛  等待队长审核  等待老师审核
	 * 3.缴纳比赛费用
	 * 4.查询获奖情况√
	 * 5.下载相关文件（获奖证书）√
	 * 6.查看个人信息。√
	 *   6.1修改个人信息。√
	 *   6.2获取个人材料。√
	 */
	@Autowired
	private StudentService studentService;
	@Autowired
	private ConditionService conditionService;
	@Autowired
	private MaterialMapper materialMapper;
	@Autowired
	private GroupsService groupsService;
	@Autowired
	private AwardsMapper awardsMapper;
	
	/**
	 * 1.1 获取该竞赛所需的条件
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getCompConditions")
	public List<Conditions> getCompConditions(Integer comId){
		return conditionService.getCompCondition(comId);
	}
	
	
	/**
	 * 1.2判断该学生不符合的条件
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getDissatisfyConditions")
	public List<Conditions> getDissatisfyConditions(Integer studentNo,Integer comId){
		return studentService.getDissatisfyConditions(studentNo, comId);
	}
	
	/**
	 * 1.3个人信息：证书、学生证等的上传
	 * @param request
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("/saveMaterial")
	public void saveMaterial(HttpServletRequest request,Material material) throws IllegalStateException, IOException{
		//将当前上下文初始化给CommonsMultipartResolver（多部分解析器）
		CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
				request.getSession().getServletContext());
		//检查表单是否有enctype="multipart/form-data"属性
		if(multipartResolver.isMultipart(request)){
			//将request变成多部分request
			MultipartHttpServletRequest multiRquest=(MultipartHttpServletRequest)request;
			//获取multiRquest的所有文件名
			Iterator iter=multiRquest.getFileNames();
			while (iter.hasNext()) {
				//一次遍历所有文件
				MultipartFile file=multiRquest.getFile(iter.next().toString());
				//String file.getOriginalFilename().split(".");
				if(file!=null){//文件不为空
					String imageName=material.getStudentno().toString()+System.currentTimeMillis();
					//上传的位置-----------------------------------------
					String path=request.getSession().getServletContext().getRealPath(File.separator)+"fileUpload\\material\\"+imageName+".jpg";
					//上传
					file.transferTo(new File(path));
					
					//保存进数据库
					material.setMaterialpic(imageName+".jpg");
					materialMapper.insert(material);
				}
			}
		
		}
		
		
	}
	 
	 /**
	  * 1.4.0获取所有队伍的信息
	  * groups表和competition表
	  * @return
	  */
	 @ResponseBody
	 @RequestMapping("/getAllGroups")
	 public List<Groups> getAllGroups(Integer comId){
		 return groupsService.getCompGroups(comId);
	 }
	 /**
	  * 1.4.1加入一个队伍.
	  * groups表和competition表
	  * @return
	  */
	 @ResponseBody
	 @RequestMapping("/joinGroup")
	 public void joinGroup(Integer groupsNo,Integer studentNo){
		 studentService.joinGroup(groupsNo, studentNo);
	 }
	 
	 /**
	  * 1.4.1.2取消加入队伍.(当队长长时间没有审核时，可取消加入队伍)
	  * 通过group表主键删除
	  * @return
	  */
	 @ResponseBody
	 @RequestMapping("/outGroup")
	 public void outGroup(Integer id){
		 groupsService.outGroups(id);
	 }
	 
	 /**
	  * 1.4.2创建队伍。(当不需要组队时。为一人一组)
	  * @return
	  */
	 @ResponseBody
	 @RequestMapping("/createGroup")
	 public void createGroup(@RequestBody Groups groups){
		 groupsService.createGroups(groups);
	 }
	 
	 /**
	  * 1.4.3查找该竞赛所有指导老师。
	  * 通过group表主键删除
	  * @return
	  */
	 @ResponseBody
	 @RequestMapping("/getCompGuideTeacher")
	 public List<Teacher> getCompGuideTeacher(Integer comId){
		 return studentService.getCompGuideTeacher(comId);
	 }
	 /**
	  * 1.4.4队长审核加入的成员
	  * @return
	  */
	 @ResponseBody
	 @RequestMapping("/setCaptainStatus")
	 public void setCaptainStatus(@RequestBody Groups groups){
		 studentService.setCaptainStatus(groups);
	 }
	 
	 
	/**
	 * 2.查看报名结果
	 * groups表和competition表
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getComp")
	public Map<String, Object> getComp(Integer studentNo){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("stuCom", studentService.getCompByStudentNo(studentNo));
		return map;
	}
	
	/**
	 * 3.查询获奖情况
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getAwards")
	public List<Map<String, Object>> getAwards(Integer studentNo){
		return  studentService.selectAwardsByStudentNo(studentNo);
	}
	
	/**
	 * 5.下载文件。获奖证书
	 * @return
	 */
	@RequestMapping("/downloadAwards")    
    public ResponseEntity<byte[]> downloadAwards(HttpServletRequest request,Awards awards) throws IOException {    
    	//文件所在的位置
        String path=request.getSession().getServletContext().getRealPath(File.separator)+"fileUpload\\awards\\"+awards.getAwardsimg();  

        File file=new File(path);  

        HttpHeaders headers = new HttpHeaders();    

        String fileName=new String(awards.getAwardsimg().getBytes("UTF-8"),"iso-8859-1");

        headers.setContentDispositionFormData("attachment", fileName);   

        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);   

        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);    

    } 
	
	
	/**
	 * 6.查看个人信息。
	 * 获取学生信息:包括基本信息和材料（包括已审批和等待审批）
	 * @param studentNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getStuInfo")
	public Student getStuInfo(Integer studentNo){
		return studentService.selectByNo(studentNo);
	}
	
	/**
	 * 6.1修改个人信息。
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateInfo")
	public int updateStuInfo(@RequestBody Student student){
	    return studentService.updateByNo(student);
	}
	/**
	 * 6.3获取个人材料。
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getMaterial")
	public List<Material> getMaterial(Integer studentNo){
	    return studentService.getMaterial(studentNo);
	}
	
}
