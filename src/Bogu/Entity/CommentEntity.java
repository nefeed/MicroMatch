package Bogu.Entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BgGcomment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BG_GComment", schema = "dbo", catalog = "BG_CloudCourse")
public class CommentEntity implements java.io.Serializable {

	// Fields

	private Integer id;
	private Timestamp commentTime;
	private String userNum;
	private String commentContent;
	private Integer pid;
	private Integer objectType;
	private String objectNum;

	// Constructors

	/** default constructor */
	public CommentEntity() {
	}

	/** full constructor */
	public CommentEntity(Timestamp commentTime, String userNum,
			String commentContent, Integer pid, Integer objectType,
			String objectNum) {
		this.commentTime = commentTime;
		this.userNum = userNum;
		this.commentContent = commentContent;
		this.pid = pid;
		this.objectType = objectType;
		this.objectNum = objectNum;
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

	@Column(name = "CommentTime", length = 23)
	public Timestamp getCommentTime() {
		return this.commentTime;
	}

	public void setCommentTime(Timestamp commentTime) {
		this.commentTime = commentTime;
	}

	@Column(name = "UserNum", length = 20)
	public String getUserNum() {
		return this.userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	@Column(name = "CommentContent")
	public String getCommentContent() {
		return this.commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	@Column(name = "PID")
	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	@Column(name = "ObjectType")
	public Integer getObjectType() {
		return this.objectType;
	}

	public void setObjectType(Integer objectType) {
		this.objectType = objectType;
	}

	@Column(name = "ObjectNum", length = 20)
	public String getObjectNum() {
		return this.objectNum;
	}

	public void setObjectNum(String objectNum) {
		this.objectNum = objectNum;
	}

}