package com.macrosoft.apifirst.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.macrosoft.apifirst.model.Client;

@RepositoryRestResource
public interface ClientRepository extends JpaRepository<Client, Long> {
	
	public Client  findByNom(String nom);
	public Client  findByPrenom(String prenom);
	
	/*
	@Override
    @RestResource(exported = false)
    public void deleteById(Long aLong);
	*/
	
	/*
	Assume Persons have Addresses
	List<Person> findByAddress_ZipCode(ZipCode zipCode);
	*/
	
	
	/*
		CREATE VIEW Vue
	(colonneA,colonneB,colonneC,colonneD)
	AS SELECT colonne1,colonne2,colonneI,colonneII
	FROM Nom_table1 Alias1,Nom_tableII AliasII
	WHERE Alias1.colonne1 = AliasII.colonneI
	AND Alias1.colonne2 = AliasII.colonneII
	Les vues ainsi créées peuvent être l'objet de nouvelles requêtes en précisant le nom de la vue au lieu d'un nom de table dans un ordre SELECT...
	*/
	
	
	/*
	Types of Caching
	There are four types of caching are as follows:

	In-memory Caching
	Database Caching
	Web server Caching
	CDN Caching
	In-memory Caching
	*/

}

