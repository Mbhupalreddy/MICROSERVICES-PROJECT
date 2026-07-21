package com.nt.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Entity
@Table(name="MSMP_IPL_TEAM_INFO")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class IPLTeam implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer teamid;
	
	@Nonnull
	@Column(length = 20)
	private String teamname;
	
	@Nonnull
	@Column(length = 20)
	private String teamowner;
	
	@Nonnull
	@Column(length = 20)
	private String area;
	
	//metadata properties
	@Version
	private Integer updateCount;
	
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createdOn;
	
	@UpdateTimestamp
	@Column
	private LocalDateTime lastUpdatedOn;
	
	@Column(length = 30,updatable = false)
	private  String createdBy;
	
	@Column(length = 30,insertable =false)
	private  String updatedBy;
	
	@Column(length = 30)
	private  String active_SW="active";

	public IPLTeam() {
		System.out.println("IPLTeam.IPLTeam(0-param constructor:)");
	}
	
	

}
