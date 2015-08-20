package MicroMatch.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BgGexamCheck entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ExamCheck", schema = "", catalog = "micromatch")
public class ExamCheckEntity implements java.io.Serializable {

	// Fields

	private Integer id;
	private String courseNum;
	private String userNum;
	private Double grade;
	private String checkTime;

	// Constructors

	/** default constructor */
	public ExamCheckEntity() {
	}

	/** full constructor */
	public ExamCheckEntity(String courseNum, String userNum, Double grade,
			String checkTime) {
		this.courseNum = courseNum;
		this.userNum = userNum;
		this.grade = grade;
		this.checkTime = checkTime;
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

	@Column(name = "UserNum", length = 20)
	public String getUserNum() {
		return this.userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	@Column(name = "Grade", precision = 53, scale = 0)
	public Double getGrade() {
		return this.grade;
	}

	public void setGrade(Double grade) {
		this.grade = grade;
	}

	@Column(name = "CheckTime")
	public String getCheckTime() {
		return this.checkTime;
	}

	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}

}
