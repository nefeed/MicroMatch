package MicroMatch.Entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BgGcourse entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "course", schema = "", catalog = "micromatch")
public class CourseEntity implements java.io.Serializable {

	// Fields

	private Integer id;
	private String courseName;
	private String userNum;
	private Integer courseType;
	private Integer subId;
	private String courseContent;
	private Integer audienceNum;
	private Timestamp createtime;
	private String coverPicture;
	private String courseNum;
	private Integer isExam;
	private Integer subscriptionNum;
	private Short isChecked;
	private Short isComment;

	// Constructors

	/** default constructor */
	public CourseEntity() {
	}

	/** full constructor */
	public CourseEntity(String courseName, String userNum, Integer courseType,
			Integer subId, String courseContent, Integer audienceNum,
			Timestamp createtime, String coverPicture, String courseNum,
			Integer isExam, Integer subscriptionNum) {
		this.courseName = courseName;
		this.userNum = userNum;
		this.courseType = courseType;
		this.subId = subId;
		this.courseContent = courseContent;
		this.audienceNum = audienceNum;
		this.createtime = createtime;
		this.coverPicture = coverPicture;
		this.courseNum = courseNum;
		this.isExam = isExam;
		this.subscriptionNum = subscriptionNum;
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

	@Column(name = "CourseName", length = 200)
	public String getCourseName() {
		return this.courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	@Column(name = "UserNum", length = 20)
	public String getUserNum() {
		return this.userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	@Column(name = "CourseType")
	public Integer getCourseType() {
		return this.courseType;
	}

	public void setCourseType(Integer courseType) {
		this.courseType = courseType;
	}

	@Column(name = "SubID")
	public Integer getSubId() {
		return this.subId;
	}

	public void setSubId(Integer subId) {
		this.subId = subId;
	}

	@Column(name = "CourseContent")
	public String getCourseContent() {
		return this.courseContent;
	}

	public void setCourseContent(String courseContent) {
		this.courseContent = courseContent;
	}

	@Column(name = "AudienceNum")
	public Integer getAudienceNum() {
		return this.audienceNum;
	}

	public void setAudienceNum(Integer audienceNum) {
		this.audienceNum = audienceNum;
	}

	@Column(name = "Createtime", length = 23)
	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	@Column(name = "CoverPicture")
	public String getCoverPicture() {
		return this.coverPicture;
	}

	public void setCoverPicture(String coverPicture) {
		this.coverPicture = coverPicture;
	}

	@Column(name = "CourseNum", length = 20)
	public String getCourseNum() {
		return this.courseNum;
	}

	public void setCourseNum(String courseNum) {
		this.courseNum = courseNum;
	}

	@Column(name = "isExam")
	public Integer getIsExam() {
		return this.isExam;
	}

	public void setIsExam(Integer isExam) {
		this.isExam = isExam;
	}

	@Column(name = "SubscriptionNum")
	public Integer getSubscriptionNum() {
		return this.subscriptionNum;
	}

	public void setSubscriptionNum(Integer subscriptionNum) {
		this.subscriptionNum = subscriptionNum;
	}
	@Column(name = "isChecked")
	public Short getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(Short isChecked) {
		this.isChecked = isChecked;
	}
	@Column(name = "isComment")
	public Short getIsComment() {
		return isComment;
	}

	public void setIsComment(Short isComment) {
		this.isComment = isComment;
	}

}
