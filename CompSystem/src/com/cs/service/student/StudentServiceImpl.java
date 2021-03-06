package com.cs.service.student;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs.dao.awards.AwardsMapper;
import com.cs.dao.compcondition.CompConditionMapper;
import com.cs.dao.competition.CompetitionMapper;
import com.cs.dao.condition.ConditionsMapper;
import com.cs.dao.group.GroupsMapper;
import com.cs.dao.guideteacher.GuideTeacherMapper;
import com.cs.dao.material.MaterialMapper;
import com.cs.dao.student.StudentMapper;
import com.cs.pojo.Awards;
import com.cs.pojo.Competition;
import com.cs.pojo.Conditions;
import com.cs.pojo.Groups;
import com.cs.pojo.Material;
import com.cs.pojo.Student;
import com.cs.pojo.Teacher;


@Service("studentService")
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentMapper studentMapper;
	
	@Autowired
	private GroupsMapper groupsMapper;
	
	@Autowired
	private AwardsMapper awardsMapper;
	
	@Autowired
	private MaterialMapper materialMapper;
	
	@Autowired
	private CompConditionMapper compConditionMapper;
	
	@Autowired
	private GuideTeacherMapper guideTeacherMapper;
	@Autowired
	private CompetitionMapper competitionMapper;

	@Override
	public Student selectByNo(int studentNo) {
		return studentMapper.selectByPrimaryKey(studentNo);
	}

	@Override
	public int updateByNo(Student student) {
	   return studentMapper.updateByPrimaryKeySelective(student);
	}

	@Override
	public List<Map<String, Object>> getCompByStudentNo(Integer studentNo) {
		return groupsMapper.selectByStuNo(studentNo);
	}

	@Override
	public List<Map<String, Object>> selectAwardsByStudentNo(Integer studentNo) {
	     return	awardsMapper.selectAwardsByStuNo(studentNo);
	}

	@Override
	public List<Conditions> getDissatisfyConditions(Integer studentNo,Integer comId) {
		//查找出该学生已经拥有且审核通过的材料/条件
		List<Material> matList = materialMapper.selectByStudentno(studentNo);
		//该竞赛所需条件
		List<Conditions> conList = compConditionMapper.selectComId(comId);
		List<Conditions> dissatisfyCon=new ArrayList<Conditions>();
		for(int j=0;j<conList.size();j++){
				for(int i=0;i<matList.size();i++){
				if (conList.get(j).getConditionid()!=matList.get(i).getConditionid()) {
					dissatisfyCon.add(conList.get(j));
				}
			}
		}
		return dissatisfyCon;
	}

	@Override
	public void joinGroup(Integer groupsid,Integer studentNo) {
		Groups groups = groupsMapper.selectByPrimaryKey(groupsid);
		groups.setStudentno(studentNo);
		groups.setIscaptain(0);
		groups.setCaptainstatus(0);//队长未审核
		groupsMapper.insert(groups);
	}

	@Override
	public List<Teacher> getCompGuideTeacher(Integer comId) {
		return guideTeacherMapper.selectComId(comId);
	}

	@Override
	public void setCaptainStatus(Integer captainstatus,Integer id) {
		Groups groups=new Groups();
		groups.setCaptainstatus(captainstatus);
		groups.setId(id);
		groupsMapper.updateCaptainStatus(groups);
	}

	@Override
	public List<Material> getMaterial(int studentno) {
		return materialMapper.selectByStudentno(studentno);
	}

	@Override
	public List<Map<String, Object>> getCompGroupMembers(Integer studentNo) {
		List<Map<String,Object>> comGroupMembers=new ArrayList<Map<String,Object>>();
		//竞赛
		List<Competition> comList = groupsMapper.selectCompetitionByStuNo(studentNo);
		for (int i = 0; i < comList.size(); i++) {
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("comp", comList.get(i));
			Map<String, Object> conditionMap=new HashMap<String, Object>();
			conditionMap.put("comId", comList.get(i).getComid());
			conditionMap.put("studentno", studentNo);
			List<Groups> groupList = groupsMapper.selectByComIdAndStudentNo(conditionMap);
		    map.put("groupList", groupList);
		    List<Student> members = groupsMapper.selectByGroupsNo(groupList.get(0).getGroupsno());
		    map.put("members", members);
		    comGroupMembers.add(map);
		}
		return comGroupMembers;
	}

	
}
