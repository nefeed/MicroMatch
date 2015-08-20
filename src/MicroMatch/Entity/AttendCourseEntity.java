package MicroMatch.Entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BgGattendCourse entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "AttendCourse", schema = "", catalog = "micromatch")
public class AttendCourseEntity implements java.io.Serializable {

	// Fields

	private Integer id;
	private String studentNum;
	private String courseNum;
	private Timestamp attendTime;

	// Constructors

	/** default constructor */
	public AttendCourseEntity() {
	}

	/** full constructor */
	public AttendCourseEntity(String studentNum, String courseNum) {
		this.studentNum = studentNum;
		this.courseNum = courseNum;
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

	@Column(name = "CourseNum", length = 20)
	public String getCourseNum() {
		return this.courseNum;
	}

	public void setCourseNum(String courseNum) {
		this.courseNum = courseNum;
	}

	@Column(name = "AttendTime")
	public Timestamp getAttendTime() {
		return this.attendTime;
	}

	public void setAttendTime(Timestamp attendTime) {
		this.attendTime = attendTime;
	}

}
