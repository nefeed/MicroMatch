package Bogu.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BgGstudentClass entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BG_GStudentClass", schema = "dbo", catalog = "BG_CloudCourse")
public class StudentClassEntity implements java.io.Serializable {

	// Fields

	private Integer id;
	private String studentNum;
	private String classNum;
	private String joinTime;

	// Constructors

	/** default constructor */
	public StudentClassEntity() {
	}

	/** full constructor */
	public StudentClassEntity(String studentNum, String classNum, String joinTime) {
		this.studentNum = studentNum;
		this.classNum = classNum;
		this.joinTime = joinTime;
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

	@Column(name = "StudentNum", length = 20)
	public String getStudentNum() {
		return this.studentNum;
	}

	public void setStudentNum(String studentNum) {
		this.studentNum = studentNum;
	}

	@Column(name = "ClassNum", length = 20)
	public String getClassNum() {
		return this.classNum;
	}

	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}

	@Column(name = "JoinTime")
	public String getJoinTime() {
		return this.joinTime;
	}

	public void setJoinTime(String joinTime) {
		this.joinTime = joinTime;
	}

}