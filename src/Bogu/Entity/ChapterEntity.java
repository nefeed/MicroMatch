package Bogu.Entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BgGchapter entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "BG_GChapter", schema = "dbo", catalog = "BG_CloudCourse")
public class ChapterEntity implements java.io.Serializable {

	// Fields

	private Integer id;
	private String chapterName;
	private String chapterContent;
	private Integer pid;
	private String chapterVideo;
	private Timestamp createTime;
	private String courseNum;
	private String chapterNum;
	private String chapterVideoName;
	private Short isChecked;
	private Short isComment;
	private int listId ;
	private String coverPicture ;

	// Constructors

	/** default constructor */
	public ChapterEntity() {
	}

	/** full constructor */
	public ChapterEntity(String chapterName, String chapterContent, Integer pid,
			String chapterVideo, Timestamp createTime, String courseNum,
			String chapterNum) {
		this.chapterName = chapterName;
		this.chapterContent = chapterContent;
		this.pid = pid;
		this.chapterVideo = chapterVideo;
		this.createTime = createTime;
		this.courseNum = courseNum;
		this.chapterNum = chapterNum;
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

	@Column(name = "ChapterName")
	public String getChapterName() {
		return this.chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	@Column(name = "ChapterContent")
	public String getChapterContent() {
		return this.chapterContent;
	}

	public void setChapterContent(String chapterContent) {
		this.chapterContent = chapterContent;
	}

	@Column(name = "PID")
	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	@Column(name = "ChapterVideo")
	public String getChapterVideo() {
		return this.chapterVideo;
	}

	public void setChapterVideo(String chapterVideo) {
		this.chapterVideo = chapterVideo;
	}

	@Column(name = "CreateTime", length = 23)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "CourseNum", length = 20)
	public String getCourseNum() {
		return this.courseNum;
	}

	public void setCourseNum(String courseNum) {
		this.courseNum = courseNum;
	}

	@Column(name = "ChapterNum")
	public String getChapterNum() {
		return this.chapterNum;
	}

	public void setChapterNum(String chapterNum) {
		this.chapterNum = chapterNum;
	}
	@Column(name = "ChapterVideoName")
	public String getChapterVideoName() {
		return chapterVideoName;
	}

	public void setChapterVideoName(String chapterVideoName) {
		this.chapterVideoName = chapterVideoName;
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
	@Column(name = "listID")
	public int getListId() {
		return listId;
	}

	public void setListId(int listId) {
		this.listId = listId;
	}
	@Column(name = "CoverPicture")
	public String getCoverPicture() {
		return coverPicture;
	}

	public void setCoverPicture(String coverPicture) {
		this.coverPicture = coverPicture;
	}

	
}