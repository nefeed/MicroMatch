package Bogu.Entity;
// default package

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * AccessoryEntity entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="BG_GAccessory"
    ,schema="dbo"
    ,catalog="BG_CloudCourse"
)

public class AccessoryEntity  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String accessoryName;
     private String accessoryAddress;
     private Integer objectType;
     private String objectNum;
     private Timestamp createTime;
     private String userNum;
     private Integer downloadNum;


    // Constructors

    /** default constructor */
    public AccessoryEntity() {
    }

    
    /** full constructor */
    public AccessoryEntity(String accessoryName, String accessoryAddress, Integer objectType, String objectNum, Timestamp createTime, String userNum, Integer downloadNum) {
        this.accessoryName = accessoryName;
        this.accessoryAddress = accessoryAddress;
        this.objectType = objectType;
        this.objectNum = objectNum;
        this.createTime = createTime;
        this.userNum = userNum;
        this.downloadNum = downloadNum;
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
    
    @Column(name="AccessoryName", length=50)

    public String getAccessoryName() {
        return this.accessoryName;
    }
    
    public void setAccessoryName(String accessoryName) {
        this.accessoryName = accessoryName;
    }
    
    @Column(name="AccessoryAddress")

    public String getAccessoryAddress() {
        return this.accessoryAddress;
    }
    
    public void setAccessoryAddress(String accessoryAddress) {
        this.accessoryAddress = accessoryAddress;
    }
    
    @Column(name="ObjectType")

    public Integer getObjectType() {
        return this.objectType;
    }
    
    public void setObjectType(Integer objectType) {
        this.objectType = objectType;
    }
    
    @Column(name="ObjectNum", length=20)

    public String getObjectNum() {
        return this.objectNum;
    }
    
    public void setObjectNum(String objectNum) {
        this.objectNum = objectNum;
    }
    
    @Column(name="CreateTime", length=23)

    public Timestamp getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="UserNum", length=20)

    public String getUserNum() {
        return this.userNum;
    }
    
    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }
    
    @Column(name="DownloadNum")

    public Integer getDownloadNum() {
        return this.downloadNum;
    }
    
    public void setDownloadNum(Integer downloadNum) {
        this.downloadNum = downloadNum;
    }
   








}