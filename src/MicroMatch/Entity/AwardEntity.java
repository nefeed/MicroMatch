package MicroMatch.Entity;
// default package

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * AwardEntity entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="Award"
    ,schema=""
    ,catalog="micromatch"
)

public class AwardEntity  implements java.io.Serializable {


    // Fields    

     private Integer id ;
     private String matchNum ;
     private String awardName ;
     private String courseNum ;
     private Timestamp awardTime ;
     private String remark ;


    // Constructors

    /** default constructor */
    public AwardEntity() {
    }

	/** minimal constructor */
    public AwardEntity(String awardName) {
        this.awardName = awardName;
    }
    
    /** full constructor */
    public AwardEntity(String matchNum, String awardName, String courseNum) {
        this.matchNum = matchNum;
        this.awardName = awardName;
        this.courseNum = courseNum;
    }

   
    // Property accessors
    @Id @GeneratedValue
    
    @Column(name="ID", unique=true, nullable=false)

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    @Column(name="MatchNum", length=20)

    public String getMatchNum() {
        return this.matchNum;
    }
    
    public void setMatchNum(String matchNum) {
        this.matchNum = matchNum;
    }
    
    @Column(name="AwardName")

    public String getAwardName() {
        return this.awardName;
    }
    
    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }
    
    @Column(name="CourseNum", length=20)

    public String getCourseNum() {
        return this.courseNum;
    }
    
    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
    }

    @Column(name="AwardTime")
	public Timestamp getAwardTime() {
		return awardTime;
	}

	public void setAwardTime(Timestamp awardTime) {
		this.awardTime = awardTime;
	}
	
	@Column(name="Remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	} 
}
