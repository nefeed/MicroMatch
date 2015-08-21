package Bogu.Entity;
// default package

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * UserEntity entity. @author MyEclipse Persistence Tools
 */
//服务器使用
//@Entity
//@Table(name="BG_User", schema="dbo", catalog="BG_User")

//本地计算机使用
@Entity
@Table(name="BG_User"
    ,schema="dbo"
    ,catalog="BG_User4"
)
public class UserEntity  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String userNum;
     private String userName;
     private String userPassword;
     private String nickName;
     private String realName;
     private Short isAdmin;
     private String userGroupNumber;
     private String passQuestion;
     private String passKey;
     private String certType;
     private String certNumber;
     private String email;
     private String mobile;
     private Short sex;
     private Timestamp birthday;
     private String userinfo;
     private String userFace;
     private String userFacesize;
     private Short marriage;
     private Integer ipoint;
     private Integer gpoint;
     private Integer cpoint;
     private Integer epoint;
     private Integer apoint;
     private Short isLock;
     private Timestamp regTime;
     private Timestamp lastLoginTime;
     private Integer onlineTime;
     private Integer onlineTf;
     private Integer loginNumber;
     private String friendClass;
     private Integer loginLimtNumber;
     private String lastIp;
     private String siteId;
     private Integer addfriend;
     private Short isOpen;
     private Integer parmConstrNum;
     private Short isIdcard;
     private String idcardFiles;
     private Short addfriendbs;
     private Short emailAtf;
     private String emailCode;
     private Short isMobile;
     private Short bindTf;
     private String mobileCode;
     private String univerNum;
     private String collegeNum;
     private String specNum;
     private String classNum;
     private Integer userType;
     private String userPicture ;


    // Constructors

    /** default constructor */
    public UserEntity() {
    }

	/** minimal constructor */
    public UserEntity(String userNum, String userName) {
        this.userNum = userNum;
        this.userName = userName;
    }
    
    /** full constructor */
    public UserEntity(String userNum, String userName, String userPassword, String nickName, String realName, Short isAdmin, String userGroupNumber, String passQuestion, String passKey, String certType, String certNumber, String email, String mobile, Short sex, Timestamp birthday, String userinfo, String userFace, String userFacesize, Short marriage, Integer ipoint, Integer gpoint, Integer cpoint, Integer epoint, Integer apoint, Short isLock, Timestamp regTime, Timestamp lastLoginTime, Integer onlineTime, Integer onlineTf, Integer loginNumber, String friendClass, Integer loginLimtNumber, String lastIp, String siteId, Integer addfriend, Short isOpen, Integer parmConstrNum, Short isIdcard, String idcardFiles, Short addfriendbs, Short emailAtf, String emailCode, Short isMobile, Short bindTf, String mobileCode, String univerNum, String collegeNum, String specNum, String classNum, Integer userType) {
        this.userNum = userNum;
        this.userName = userName;
        this.userPassword = userPassword;
        this.nickName = nickName;
        this.realName = realName;
        this.isAdmin = isAdmin;
        this.userGroupNumber = userGroupNumber;
        this.passQuestion = passQuestion;
        this.passKey = passKey;
        this.certType = certType;
        this.certNumber = certNumber;
        this.email = email;
        this.mobile = mobile;
        this.sex = sex;
        this.birthday = birthday;
        this.userinfo = userinfo;
        this.userFace = userFace;
        this.userFacesize = userFacesize;
        this.marriage = marriage;
        this.ipoint = ipoint;
        this.gpoint = gpoint;
        this.cpoint = cpoint;
        this.epoint = epoint;
        this.apoint = apoint;
        this.isLock = isLock;
        this.regTime = regTime;
        this.lastLoginTime = lastLoginTime;
        this.onlineTime = onlineTime;
        this.onlineTf = onlineTf;
        this.loginNumber = loginNumber;
        this.friendClass = friendClass;
        this.loginLimtNumber = loginLimtNumber;
        this.lastIp = lastIp;
        this.siteId = siteId;
        this.addfriend = addfriend;
        this.isOpen = isOpen;
        this.parmConstrNum = parmConstrNum;
        this.isIdcard = isIdcard;
        this.idcardFiles = idcardFiles;
        this.addfriendbs = addfriendbs;
        this.emailAtf = emailAtf;
        this.emailCode = emailCode;
        this.isMobile = isMobile;
        this.bindTf = bindTf;
        this.mobileCode = mobileCode;
        this.univerNum = univerNum;
        this.collegeNum = collegeNum;
        this.specNum = specNum;
        this.classNum = classNum;
        this.userType = userType;
    }

   
    // Property accessors
    @Id @GeneratedValue
    
    @Column(name="Id", unique=true, nullable=false)

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    @Column(name="UserNum", nullable=false)

    public String getUserNum() {
        return this.userNum;
    }
    
    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }
    
    @Column(name="UserName", nullable=false)

    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    @Column(name="UserPassword")

    public String getUserPassword() {
        return this.userPassword;
    }
    
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    
    @Column(name="NickName")

    public String getNickName() {
        return this.nickName;
    }
    
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    
    @Column(name="RealName")

    public String getRealName() {
        return this.realName;
    }
    
    public void setRealName(String realName) {
        this.realName = realName;
    }
    
    @Column(name="isAdmin")

    public Short getIsAdmin() {
        return this.isAdmin;
    }
    
    public void setIsAdmin(Short isAdmin) {
        this.isAdmin = isAdmin;
    }
    
    @Column(name="UserGroupNumber")

    public String getUserGroupNumber() {
        return this.userGroupNumber;
    }
    
    public void setUserGroupNumber(String userGroupNumber) {
        this.userGroupNumber = userGroupNumber;
    }
    
    @Column(name="PassQuestion")

    public String getPassQuestion() {
        return this.passQuestion;
    }
    
    public void setPassQuestion(String passQuestion) {
        this.passQuestion = passQuestion;
    }
    
    @Column(name="PassKey")

    public String getPassKey() {
        return this.passKey;
    }
    
    public void setPassKey(String passKey) {
        this.passKey = passKey;
    }
    
    @Column(name="CertType")

    public String getCertType() {
        return this.certType;
    }
    
    public void setCertType(String certType) {
        this.certType = certType;
    }
    
    @Column(name="CertNumber")

    public String getCertNumber() {
        return this.certNumber;
    }
    
    public void setCertNumber(String certNumber) {
        this.certNumber = certNumber;
    }
    
    @Column(name="Email")

    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Column(name="mobile")

    public String getMobile() {
        return this.mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    @Column(name="Sex")

    public Short getSex() {
        return this.sex;
    }
    
    public void setSex(Short sex) {
        this.sex = sex;
    }
    
    @Column(name="birthday", length=23)

    public Timestamp getBirthday() {
        return this.birthday;
    }
    
    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }
    
    @Column(name="Userinfo")

    public String getUserinfo() {
        return this.userinfo;
    }
    
    public void setUserinfo(String userinfo) {
        this.userinfo = userinfo;
    }
    
    @Column(name="UserFace")

    public String getUserFace() {
        return this.userFace;
    }
    
    public void setUserFace(String userFace) {
        this.userFace = userFace;
    }
    
    @Column(name="userFacesize")

    public String getUserFacesize() {
        return this.userFacesize;
    }
    
    public void setUserFacesize(String userFacesize) {
        this.userFacesize = userFacesize;
    }
    
    @Column(name="marriage")

    public Short getMarriage() {
        return this.marriage;
    }
    
    public void setMarriage(Short marriage) {
        this.marriage = marriage;
    }
    
    @Column(name="iPoint")

    public Integer getIpoint() {
        return this.ipoint;
    }
    
    public void setIpoint(Integer ipoint) {
        this.ipoint = ipoint;
    }
    
    @Column(name="gPoint")

    public Integer getGpoint() {
        return this.gpoint;
    }
    
    public void setGpoint(Integer gpoint) {
        this.gpoint = gpoint;
    }
    
    @Column(name="cPoint")

    public Integer getCpoint() {
        return this.cpoint;
    }
    
    public void setCpoint(Integer cpoint) {
        this.cpoint = cpoint;
    }
    
    @Column(name="ePoint")

    public Integer getEpoint() {
        return this.epoint;
    }
    
    public void setEpoint(Integer epoint) {
        this.epoint = epoint;
    }
    
    @Column(name="aPoint")

    public Integer getApoint() {
        return this.apoint;
    }
    
    public void setApoint(Integer apoint) {
        this.apoint = apoint;
    }
    
    @Column(name="isLock")

    public Short getIsLock() {
        return this.isLock;
    }
    
    public void setIsLock(Short isLock) {
        this.isLock = isLock;
    }
    
    @Column(name="RegTime", length=23)

    public Timestamp getRegTime() {
        return this.regTime;
    }
    
    public void setRegTime(Timestamp regTime) {
        this.regTime = regTime;
    }
    
    @Column(name="LastLoginTime", length=23)

    public Timestamp getLastLoginTime() {
        return this.lastLoginTime;
    }
    
    public void setLastLoginTime(Timestamp lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
    
    @Column(name="OnlineTime")

    public Integer getOnlineTime() {
        return this.onlineTime;
    }
    
    public void setOnlineTime(Integer onlineTime) {
        this.onlineTime = onlineTime;
    }
    
    @Column(name="OnlineTF")

    public Integer getOnlineTf() {
        return this.onlineTf;
    }
    
    public void setOnlineTf(Integer onlineTf) {
        this.onlineTf = onlineTf;
    }
    
    @Column(name="LoginNumber")

    public Integer getLoginNumber() {
        return this.loginNumber;
    }
    
    public void setLoginNumber(Integer loginNumber) {
        this.loginNumber = loginNumber;
    }
    
    @Column(name="FriendClass")

    public String getFriendClass() {
        return this.friendClass;
    }
    
    public void setFriendClass(String friendClass) {
        this.friendClass = friendClass;
    }
    
    @Column(name="LoginLimtNumber")

    public Integer getLoginLimtNumber() {
        return this.loginLimtNumber;
    }
    
    public void setLoginLimtNumber(Integer loginLimtNumber) {
        this.loginLimtNumber = loginLimtNumber;
    }
    
    @Column(name="LastIP")

    public String getLastIp() {
        return this.lastIp;
    }
    
    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }
    
    @Column(name="SiteID")

    public String getSiteId() {
        return this.siteId;
    }
    
    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }
    
    @Column(name="Addfriend")

    public Integer getAddfriend() {
        return this.addfriend;
    }
    
    public void setAddfriend(Integer addfriend) {
        this.addfriend = addfriend;
    }
    
    @Column(name="isOpen")

    public Short getIsOpen() {
        return this.isOpen;
    }
    
    public void setIsOpen(Short isOpen) {
        this.isOpen = isOpen;
    }
    
    @Column(name="ParmConstrNum")

    public Integer getParmConstrNum() {
        return this.parmConstrNum;
    }
    
    public void setParmConstrNum(Integer parmConstrNum) {
        this.parmConstrNum = parmConstrNum;
    }
    
    @Column(name="isIDcard")

    public Short getIsIdcard() {
        return this.isIdcard;
    }
    
    public void setIsIdcard(Short isIdcard) {
        this.isIdcard = isIdcard;
    }
    
    @Column(name="IDcardFiles")

    public String getIdcardFiles() {
        return this.idcardFiles;
    }
    
    public void setIdcardFiles(String idcardFiles) {
        this.idcardFiles = idcardFiles;
    }
    
    @Column(name="Addfriendbs")

    public Short getAddfriendbs() {
        return this.addfriendbs;
    }
    
    public void setAddfriendbs(Short addfriendbs) {
        this.addfriendbs = addfriendbs;
    }
    
    @Column(name="EmailATF")

    public Short getEmailAtf() {
        return this.emailAtf;
    }
    
    public void setEmailAtf(Short emailAtf) {
        this.emailAtf = emailAtf;
    }
    
    @Column(name="EmailCode")

    public String getEmailCode() {
        return this.emailCode;
    }
    
    public void setEmailCode(String emailCode) {
        this.emailCode = emailCode;
    }
    
    @Column(name="isMobile")

    public Short getIsMobile() {
        return this.isMobile;
    }
    
    public void setIsMobile(Short isMobile) {
        this.isMobile = isMobile;
    }
    
    @Column(name="BindTF")

    public Short getBindTf() {
        return this.bindTf;
    }
    
    public void setBindTf(Short bindTf) {
        this.bindTf = bindTf;
    }
    
    @Column(name="MobileCode")

    public String getMobileCode() {
        return this.mobileCode;
    }
    
    public void setMobileCode(String mobileCode) {
        this.mobileCode = mobileCode;
    }
    
    @Column(name="UniverNum", length=20)

    public String getUniverNum() {
        return this.univerNum;
    }
    
    public void setUniverNum(String univerNum) {
        this.univerNum = univerNum;
    }
    
    @Column(name="CollegeNum", length=20)

    public String getCollegeNum() {
        return this.collegeNum;
    }
    
    public void setCollegeNum(String collegeNum) {
        this.collegeNum = collegeNum;
    }
    
    @Column(name="SpecNum", length=20)

    public String getSpecNum() {
        return this.specNum;
    }
    
    public void setSpecNum(String specNum) {
        this.specNum = specNum;
    }
    
    @Column(name="ClassNum", length=20)

    public String getClassNum() {
        return this.classNum;
    }
    
    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }
    
    @Column(name="UserType")

    public Integer getUserType() {
        return this.userType;
    }
    
    public void setUserType(Integer userType) {
        this.userType = userType;
    }
    @Column(name="UserPicture")
	public String getUserPicture() {
		return userPicture;
	}

	public void setUserPicture(String userPicture) {
		this.userPicture = userPicture;
	}

}