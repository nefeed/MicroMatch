package Bogu.Entity;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BgGregistration entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BG_GRegistration", schema = "dbo", catalog = "BG_CloudCourse")
public class RegistrationEntity implements java.io.Serializable {

	// Fields

	private Integer id;
	private String courseNum;
	private String matchNum;
	private float poll;
	private Integer pollNum;
	private Timestamp registerTime ;
	private Integer matchTemp ;  // 比赛阶段，0初赛 1复赛 2决赛
	// Constructors

	/** default constructor */
	public RegistrationEntity() {
	}

	/** minimal constructor */
	public RegistrationEntity(Integer pollNum) {
		this.pollNum = pollNum;
	}

	/** full constructor */
	public RegistrationEntity(String courseNum, String matchNum, float poll,
			Integer pollNum) {
		this.courseNum = courseNum;
		this.matchNum = matchNum;
		this.poll = poll;
		this.pollNum = pollNum;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "CourseNum", length = 20)
	public String getCourseNum() {
		return this.courseNum;
	}

	public void setCourseNum(String courseNum) {
		this.courseNum = courseNum;
	}

	@Column(name = "MatchNum", length = 20)
	public String getMatchNum() {
		return this.matchNum;
	}

	public void setMatchNum(String matchNum) {
		this.matchNum = matchNum;
	}

	@Column(name = "Poll", precision = 53, scale = 0)
	public float getPoll() {
		return this.poll;
	}

	public void setPoll(float poll) {
		this.poll = poll;
	}

	@Column(name = "PollNum")
	public Integer getPollNum() {
		return this.pollNum;
	}

	public void setPollNum(Integer pollNum) {
		this.pollNum = pollNum;
	}
	
	@Column(name = "RegisterTime")
	public Timestamp getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Timestamp timestamp) {
		this.registerTime = timestamp;
	}
	@Column(name = "MatchTemp")
	public Integer getMatchTemp() {
		return matchTemp;
	}

	public void setMatchTemp(Integer matchTemp) {
		this.matchTemp = matchTemp;
	}
	
}