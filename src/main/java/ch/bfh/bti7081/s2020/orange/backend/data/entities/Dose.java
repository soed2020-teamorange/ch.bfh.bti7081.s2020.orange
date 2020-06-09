package ch.bfh.bti7081.s2020.orange.backend.data.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dose extends AbstractEntity {

	private double dosageMg;
	
	private LocalDateTime consumeTime;
}
