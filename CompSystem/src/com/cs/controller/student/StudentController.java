package com.cs.controller.student;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cs.pojo.Awards;
import com.cs.pojo.Budget;
import com.cs.pojo.Competition;
import com.cs.pojo.Department;
import com.cs.pojo.Hours;
import com.cs.pojo.Material;
import com.cs.pojo.Project;
import com.cs.pojo.Schedule;
import com.cs.pojo.Teacher;
import com.cs.service.student.StudentService;
import com.cs.service.teacher.TeacherService;


@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	/**
	 * 获取学生材料信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getMaterial.do")
	public List<Material> getInfo(@Param("studentNo") Integer studentNo){
		//假学生
		List<Material> materials = studentService.getMaterials(studentNo);
		return materials;
	}
	
	/**
	 * 获取学生报名的比赛
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getComp.do")
	public List<Project> getCompInfo(@Param("studentNo") Integer studentNo){
		//假学生
		List<Project> projects = studentService.selectCompByStudentNo(1);
		return projects;
	}
	
	/**
	 * 获取学生获得的奖项
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getAwards.do")
	public List<Awards> getAwards(@Param("studentNo") Integer studentNo){
		//假学生
		List<Awards> awards=studentService.selectAwardsByStudentNo(studentNo);
		return awards;
	}
	
	
}
