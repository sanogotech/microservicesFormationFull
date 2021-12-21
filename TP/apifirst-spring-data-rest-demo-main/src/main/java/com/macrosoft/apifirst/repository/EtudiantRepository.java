package com.macrosoft.apifirst.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;
import com.macrosoft.apifirst.model.Etudiant;

@RepositoryRestResource
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
	
	public  List<Etudiant>  findByFirstName(String firstName);
	public  List<Etudiant>  findByLastName(String lastName);
	
	

}

