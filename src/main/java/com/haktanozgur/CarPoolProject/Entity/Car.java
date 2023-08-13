package com.haktanozgur.CarPoolProject.Entity;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table (name = "cars")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Car {

	@Id
	@SequenceGenerator(name = "car_id_seq_gen", sequenceName = "car_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car_id_seq_gen")
	private Long id;
	@Column(nullable = false)
	private String plateNumber;
	private String chassisNumber;
	private String ticket;
	@Column(nullable = false)
	private String brand;
	@Column(nullable = false)
	private String model;
	@Column(nullable = false)
	private Integer modelYear;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "created_by")
    @OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "filo_group_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
	private FiloGroup filogroup;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "company_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
	private Company company;
}
