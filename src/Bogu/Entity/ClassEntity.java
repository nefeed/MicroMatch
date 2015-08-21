package Bogu.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BgGclass entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BG_GClass", schema = "dbo", catalog = "BG_CloudCourse")
public class ClassEntity implements java.io.Serializable {

	// Fields

	private Integer id;
	private String teacherNum;
	private String className;
	private String classNum;
	private String createTime;

	// Constructors

	/** default constructor */
	public ClassEntity() {
	}

	/** full constructor */
	public ClassEntity(String teacherNum, String className, String classNum,
			String createTime) {
		this.teacherNum = teacherNum;
		this.className = className;
		this.classNum = classNum;
		this.createTime = createTime;
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

	@Column(name = "TeacherNum", length = 20)
	public String getTeacherNum() {
		return this.teacherNum;
	}

	public void setTeacherNum(String teacherNum) {
		this.teacherNum = teacherNum;
	}

	@Column(name = "ClassName", length = 50)
	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Column(name = "ClassNum", length = 20)
	public String getClassNum() {
		return this.classNum;
	}

	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}

	@Column(name = "CreateTime")
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}