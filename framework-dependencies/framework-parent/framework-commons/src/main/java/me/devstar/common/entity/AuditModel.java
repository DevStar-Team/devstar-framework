package me.devstar.common.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AuditModel implements Serializable {
	private static final long serialVersionUID = -4392213622794911032L;

	@Column(name = "IS_DEL")
	private boolean isDeleted;

	/**
	 * 등록 일시
	 */
	@DateTimeFormat(pattern = "yyyy.MM.dd HH:mm:ss.SS")
	@JsonFormat(pattern = "yyyy.MM.dd HH:mm:ss")
	@CreatedDate
	@Column(name = "REG_DATETIME", updatable = false)
	private LocalDateTime createdDate;

	/**
	 * 등록 사용자 ID
	 */
	@CreatedBy
	@Column(name = "REG_USER_ID", updatable = false)
	private String createdBy;

	/**
	 * 최종 수정 일시
	 */
	@DateTimeFormat(pattern = "yyyy.MM.dd HH:mm:ss.SS")
	@JsonFormat(pattern = "yyyy.MM.dd HH:mm:ss")
	@LastModifiedDate
	@Column(name = "MOD_DATETIME")
	private LocalDateTime lastModifiedDate;

	/**
	 * 최종 수정 사용자 ID
	 */
	@LastModifiedBy
	@Column(name = "MOD_USER_ID")
	private String lastModifiedBy;

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @return the createdDate
	 */
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	/**
	 * @return the lastModifiedBy
	 */
	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	/**
	 * @return the lastModifiedDate
	 */
	public LocalDateTime getLastModifiedDate() {
		return lastModifiedDate;
	}

	/**
	 * @return the isDeleted
	 */
	public boolean isDeleted() {
		return isDeleted;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	@Temporal(TemporalType.TIMESTAMP)
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @param isDeleted the isDeleted to set
	 */
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	/**
	 * @param lastModifiedBy the lastModifiedBy to set
	 */
	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	/**
	 * @param lastModifiedDate the lastModifiedDate to set
	 */
	@Temporal(TemporalType.TIMESTAMP)
	public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
}
