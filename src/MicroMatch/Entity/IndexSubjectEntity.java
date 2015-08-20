package MicroMatch.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BgGindexSubject entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "IndexSubject", schema = "", catalog = "micromatch")
public class IndexSubjectEntity implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer sid;

	// Constructors

	/** default constructor */
	public IndexSubjectEntity() {
	}

	/** full constructor */
	public IndexSubjectEntity(Integer sid) {
		this.sid = sid;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "sid")
	public Integer getSid() {
		return this.sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

}
