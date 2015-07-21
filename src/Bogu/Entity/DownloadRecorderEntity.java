package Bogu.Entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BgGdownloadRecorder entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BG_GDownloadRecorder", schema = "dbo", catalog = "BG_CloudCourse")
public class DownloadRecorderEntity implements java.io.Serializable {

	// Fields

	private Integer id;
	private String userNum;
	private Integer accessoryId;
	private Timestamp downloadTime;

	// Constructors

	/** default constructor */
	public DownloadRecorderEntity() {
	}

	/** full constructor */
	public DownloadRecorderEntity(String userNum, Integer accessoryId,
			Timestamp downloadTime) {
		this.userNum = userNum;
		this.accessoryId = accessoryId;
		this.downloadTime = downloadTime;
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

	@Column(name = "AccessoryID")
	public Integer getAccessoryId() {
		return this.accessoryId;
	}

	public void setAccessoryId(Integer accessoryId) {
		this.accessoryId = accessoryId;
	}

	@Column(name = "DownloadTime", length = 23)
	public Timestamp getDownloadTime() {
		return this.downloadTime;
	}

	public void setDownloadTime(Timestamp downloadTime) {
		this.downloadTime = downloadTime;
	}

}