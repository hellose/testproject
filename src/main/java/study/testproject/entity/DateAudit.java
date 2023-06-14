package study.testproject.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class DateAudit {

	// TODO - Spring Security 연동 후 @CreatedBy, @LastModifiedBy 추가 필요

	@Column(nullable = false, updatable = false)
	@CreatedDate
	private LocalDateTime createdDate;

	@Column(nullable = false)
	@LastModifiedDate
	private LocalDateTime updatedDate;
}