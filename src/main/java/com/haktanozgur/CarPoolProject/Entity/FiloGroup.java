package com.haktanozgur.CarPoolProject.Entity;

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
@Table (name = "filo_group")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FiloGroup {

	@Id
	@SequenceGenerator(name = "filo_id_seq_gen", sequenceName = "filo_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "filo_id_seq_gen")
	private Long id;
	@Column(name = "group_name")
	private String name;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private FiloGroup parent;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
	private Company company;
	
    
}
