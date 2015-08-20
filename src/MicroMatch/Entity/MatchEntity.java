package MicroMatch.Entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BgGmatch entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "Match", schema = "", catalog = "micromatch")
public class MatchEntity implements java.io.Serializable {

	// Fields

	private Integer id;
	private String matchName;
	private String userNum;
	private String matchContent;
	private String matchStartTime;
	private String matchEndTime;
	private String matchNum;
	private Integer pid;
	private Integer isStop;
	private Integer registrationNum;
	private Timestamp publishTime ;
	private Short isChecked;
	private String matchPicture ;
	private Integer matchTemp ; // 比赛阶段，0初赛 1复赛 2决赛
	// Constructors

	/** default constructor */
	public MatchEntity() {
	}

	/** full constructor */
	public MatchEntity(String matchName, String userNum, String matchContent,
			String matchStartTime, String matchEndTime, String matchNum,
			Integer pid, Integer isStop, Integer registrationNum) {
		this.matchName = matchName;
		this.userNum = userNum;
		this.matchContent = matchContent;
		this.matchStartTime = matchStartTime;
		this.matchEndTime = matchEndTime;
		this.matchNum = matchNum;
		this.pid = pid;
		this.isStop = isStop;
		this.registrationNum = registrationNum;
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

	@Column(name = "MatchName")
	public String getMatchName() {
		return this.matchName;
	}

	public void setMatchName(String matchName) {
		this.matchName = matchName;
	}

	@Column(name = "UserNum", length = 20)
	public String getUserNum() {
		return this.userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	@Column(name = "MatchContent")
	public String getMatchContent() {
		return this.matchContent;
	}

	public void setMatchContent(String matchContent) {
		this.matchContent = matchContent;
	}

	@Column(name = "MatchStartTime", length = 50)
	public String getMatchStartTime() {
		return this.matchStartTime;
	}

	public void setMatchStartTime(String matchStartTime) {
		this.matchStartTime = matchStartTime;
	}

	@Column(name = "MatchEndTime", length = 50)
	public String getMatchEndTime() {
		return this.matchEndTime;
	}

	public void setMatchEndTime(String matchEndTime) {
		this.matchEndTime = matchEndTime;
	}

	@Column(name = "MatchNum", length = 20)
	public String getMatchNum() {
		return this.matchNum;
	}

	public void setMatchNum(String matchNum) {
		this.matchNum = matchNum;
	}

	@Column(name = "PID")
	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	@Column(name = "isStop")
	public Integer getIsStop() {
		return this.isStop;
	}

	public void setIsStop(Integer isStop) {
		this.isStop = isStop;
	}

	@Column(name = "RegistrationNum")
	public Integer getRegistrationNum() {
		return this.registrationNum;
	}

	public void setRegistrationNum(Integer registrationNum) {
		this.registrationNum = registrationNum;
	}
	@Column(name = "PublishTime")
	public Timestamp getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Timestamp publishTime) {
		this.publishTime = publishTime;
	}
	@Column(name = "isChecked")
	public Short getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(Short isChecked) {
		this.isChecked = isChecked;
	}
	@Column(name = "MatchPicture")
	public String getMatchPicture() {
		return matchPicture;
	}

	public void setMatchPicture(String matchPicture) {
		this.matchPicture = matchPicture;
	}
	@Column(name = "MatchTemp")
	public Integer getMatchTemp() {
		return matchTemp;
	}

	public void setMatchTemp(Integer matchTemp) {
		this.matchTemp = matchTemp;
	}

	
}
