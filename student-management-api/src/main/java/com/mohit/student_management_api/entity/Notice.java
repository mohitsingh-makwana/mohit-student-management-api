package com.mohit.student_management_api.entity;


import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name="notice")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE)
@Builder
public class Notice {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int noticeId;
	
	@Column(nullable = false)
	String topic;
	
	@Column(nullable = false)
	String noticeDoc;

	@Column
	LocalDateTime issuedAt;
	
	@Column
	LocalDate lastDate;
	
	@ManyToOne
	@JoinColumn(name="classroom_id")
	ClassRoom classroom;
	
	
	
	
}
