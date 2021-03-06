package com.cs.pojo;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class Competition {
    private Integer comid;

    private String comname;

    private Integer teacherno;
    
    private Integer leaderNo;

    private String unit;

    private String levelname;

    private String department;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private String host;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date time;

    private String place;

    private String introduce;

    private String object;

    private Integer people;

    private String sponsor;

    private String plan;

    private String tgoal;

    private String wgoal;

    private String sopinion;

    private String slsign;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date slsdate;

    private String uopinion;

    private String dsign;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dsdate;

    private String tdopinion;

    private String olsign;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date olsdate;

    private String lsign;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lsdate;

    private Integer depspstatus;

    private Integer teaspstatus;

    private Integer saveStatus;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date applybegindate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date applyenddate;

    private Long cost;

    private Integer compstatus;

    private Integer ispublish;
    
    private Integer isneedgroup;
    
    private Integer groupnum;
    
    private int awardPub;
    
   
    public int getAwardPub() {
		return awardPub;
	}

	public void setAwardPub(int awardPub) {
		this.awardPub = awardPub;
	}

	public Integer getComid() {
        return comid;
    }

    public void setComid(Integer comid) {
        this.comid = comid;
    }

    public String getComname() {
        return comname;
    }

    public void setComname(String comname) {
        this.comname = comname == null ? null : comname.trim();
    }

    public Integer getTeacherno() {
        return teacherno;
    }

    public void setTeacherno(Integer teacherno) {
        this.teacherno = teacherno;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getLevelname() {
        return levelname;
    }

    public void setLevelname(String levelname) {
        this.levelname = levelname == null ? null : levelname.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host == null ? null : host.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place == null ? null : place.trim();
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object == null ? null : object.trim();
    }

    public Integer getPeople() {
        return people;
    }

    public void setPeople(Integer people) {
        this.people = people;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor == null ? null : sponsor.trim();
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan == null ? null : plan.trim();
    }

    public String getTgoal() {
        return tgoal;
    }

    public void setTgoal(String tgoal) {
        this.tgoal = tgoal == null ? null : tgoal.trim();
    }

    public String getWgoal() {
        return wgoal;
    }

    public void setWgoal(String wgoal) {
        this.wgoal = wgoal == null ? null : wgoal.trim();
    }

    public String getSopinion() {
        return sopinion;
    }

    public void setSopinion(String sopinion) {
        this.sopinion = sopinion == null ? null : sopinion.trim();
    }

    public String getSlsign() {
        return slsign;
    }

    public void setSlsign(String slsign) {
        this.slsign = slsign == null ? null : slsign.trim();
    }

    public Date getSlsdate() {
        return slsdate;
    }

    public void setSlsdate(Date slsdate) {
        this.slsdate = slsdate;
    }

    public String getUopinion() {
        return uopinion;
    }

    public void setUopinion(String uopinion) {
        this.uopinion = uopinion == null ? null : uopinion.trim();
    }

    public String getDsign() {
        return dsign;
    }

    public void setDsign(String dsign) {
        this.dsign = dsign == null ? null : dsign.trim();
    }

    public Date getDsdate() {
        return dsdate;
    }

    public void setDsdate(Date dsdate) {
        this.dsdate = dsdate;
    }

    public String getTdopinion() {
        return tdopinion;
    }

    public void setTdopinion(String tdopinion) {
        this.tdopinion = tdopinion == null ? null : tdopinion.trim();
    }

    public String getOlsign() {
        return olsign;
    }

    public void setOlsign(String olsign) {
        this.olsign = olsign == null ? null : olsign.trim();
    }

    public Date getOlsdate() {
        return olsdate;
    }

    public void setOlsdate(Date olsdate) {
        this.olsdate = olsdate;
    }

    public String getLsign() {
        return lsign;
    }

    public void setLsign(String lsign) {
        this.lsign = lsign == null ? null : lsign.trim();
    }

    public Date getLsdate() {
        return lsdate;
    }

    public void setLsdate(Date lsdate) {
        this.lsdate = lsdate;
    }

    public Integer getDepspstatus() {
        return depspstatus;
    }

    public void setDepspstatus(Integer depspstatus) {
        this.depspstatus = depspstatus;
    }

    public Integer getTeaspstatus() {
        return teaspstatus;
    }

    public void setTeaspstatus(Integer teaspstatus) {
        this.teaspstatus = teaspstatus;
    }

    

    public Integer getSaveStatus() {
		return saveStatus;
	}

	public void setSaveStatus(Integer saveStatus) {
		this.saveStatus = saveStatus;
	}

	public Date getApplybegindate() {
        return applybegindate;
    }

    public void setApplybegindate(Date applybegindate) {
        this.applybegindate = applybegindate;
    }

    public Date getApplyenddate() {
        return applyenddate;
    }

    public void setApplyenddate(Date applyenddate) {
        this.applyenddate = applyenddate;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public Integer getCompstatus() {
        return compstatus;
    }

    public void setCompstatus(Integer compstatus) {
        this.compstatus = compstatus;
    }

    public Integer getIspublish() {
        return ispublish;
    }

    public void setIspublish(Integer ispublish) {
        this.ispublish = ispublish;
    }

	public Integer getIsneedgroup() {
		return isneedgroup;
	}

	public void setIsneedgroup(Integer isneedgroup) {
		this.isneedgroup = isneedgroup;
	}

	public Integer getGroupnum() {
		return groupnum;
	}

	public void setGroupnum(Integer groupnum) {
		this.groupnum = groupnum;
	}

	public Integer getLeaderNo() {
		return leaderNo;
	}

	public void setLeaderNo(Integer leaderNo) {
		this.leaderNo = leaderNo;
	}

	@Override
	public String toString() {
		return "Competition [comid=" + comid + ", comname=" + comname
				+ ", teacherno=" + teacherno + ", unit=" + unit
				+ ", levelname=" + levelname + ", department=" + department
				+ ", date=" + date + ", host=" + host + ", time=" + time
				+ ", place=" + place + ", introduce=" + introduce + ", object="
				+ object + ", people=" + people + ", sponsor=" + sponsor
				+ ", plan=" + plan + ", tgoal=" + tgoal + ", wgoal=" + wgoal
				+ ", sopinion=" + sopinion + ", slsign=" + slsign
				+ ", slsdate=" + slsdate + ", uopinion=" + uopinion
				+ ", dsign=" + dsign + ", dsdate=" + dsdate + ", tdopinion="
				+ tdopinion + ", olsign=" + olsign + ", olsdate=" + olsdate
				+ ", lsign=" + lsign + ", lsdate=" + lsdate + ", depspstatus="
				+ depspstatus + ", teaspstatus=" + teaspstatus
				+ ", completestatus=" + saveStatus + ", applybegindate="
				+ applybegindate + ", applyenddate=" + applyenddate + ", cost="
				+ cost + ", compstatus=" + compstatus + ", ispublish="
				+ ispublish + ", isneedgroup=" + isneedgroup + ", groupnum="
				+ groupnum +"leaderNo="+leaderNo+ "awardPub="+awardPub+"]";
	}
     
	
}