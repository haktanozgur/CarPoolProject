package com.haktanozgur.CarPoolProject.Entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table (name = "company")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Company {

	@Id
	@SequenceGenerator(name = "company_id_seq_gen", sequenceName = "company_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_id_seq_gen")
	private Long id;
	private String name;
}
