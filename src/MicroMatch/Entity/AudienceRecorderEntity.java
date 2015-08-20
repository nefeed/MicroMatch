package MicroMatch.Entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BgGaudienceRecorder entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "AudienceRecorder", schema = "", catalog = "micromatch")
public class AudienceRecorderEntity implements java.io.Serializable {

	// Fields

	private Integer id;
	private String userNum;
	private String courseNum;
	private Timestamp recorderTime;

	// Constructors

	/** default constructor */
	public AudienceRecorderEntity() {
	}

	/** full constructor */
	public AudienceRecorderEntity(String userNum, String courseNum,
			Timestamp recorderTime) {
		this.userNum = userNum;
		this.courseNum = courseNum;
		this.recorderTime = recorderTime;
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

	@Column(name = "UserNum", length = 20)
	public String getUserNum() {
		return this.userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	@Column(name = "CourseNum", length = 20)
	public String getCourseNum() {
		return this.courseNum;
	}

	public void setCourseNum(String courseNum) {
		this.courseNum = courseNum;
	}

	@Column(name = "RecorderTime", length = 23)
	public Timestamp getRecorderTime() {
		return this.recorderTime;
	}

	public void setRecorderTime(Timestamp recorderTime) {
		this.recorderTime = recorderTime;
	}

}
