package MicroMatch.Entity;
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
@Table(name="User"
    ,schema=""
    ,catalog="micromatch"
)
public class UserEntity  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String userNum;
     private String userName;
     private String Password;
     private String nickName;
     private String realName;
     private String passQuestion;
     private String passKey;
     private String email;
     private String mobile;
     // sex=0是女，sex=1是男
     private Short sex=(short) userSex.man.ordinal();
     private Timestamp birthday;
     private String userinfo="空";
     private Short marriage=0;
     private Short isLock=0;
     private Timestamp regTime;
     private Timestamp lastLoginTime;
     private Integer onlineTime=0;
     private Integer onlineTf=0;
     private Integer loginNumber=0;
     private String lastIp;
     private Integer userType=3;
     private String userPicture;

     public enum userSex{
    	 woman, man
     }

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
    public UserEntity(String userNum, String userName, String Password,
			String nickName, String realName, String passQuestion,
			String passKey, String email, String mobile, Short sex,
			Timestamp birthday, String userinfo, Short marriage, Short isLock,
			Timestamp regTime, Timestamp lastLoginTime, Integer onlineTime,
			Integer onlineTf, Integer loginNumber, String lastIp,
			Integer userType, String userPicture) {
		super();
		this.userNum = userNum;
		this.userName = userName;
		this.Password = Password;
		this.nickName = nickName;
		this.realName = realName;
		this.passQuestion = passQuestion;
		this.passKey = passKey;
		this.email = email;
		this.mobile = mobile;
		this.sex = sex;
		this.birthday = birthday;
		this.userinfo = userinfo;
		this.marriage = marriage;
		this.isLock = isLock;
		this.regTime = regTime;
		this.lastLoginTime = lastLoginTime;
		this.onlineTime = onlineTime;
		this.onlineTf = onlineTf;
		this.loginNumber = loginNumber;
		this.lastIp = lastIp;
		this.userType = userType;
		this.userPicture = userPicture;
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
    
    @Column(name="Password")

    public String getPassword() {
        return this.Password;
    }
    
    public void setPassword(String Password) {
        this.Password = Password;
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
    
    @Column(name="marriage")

    public Short getMarriage() {
        return this.marriage;
    }
    
    public void setMarriage(Short marriage) {
        this.marriage = marriage;
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
    

 
    
    @Column(name="LastIP")

    public String getLastIp() {
        return this.lastIp;
    }
    
    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
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
