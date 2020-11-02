package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService {
	public final static Logger logger=LogManager.getLogger(EntrepriseServiceImpl.class);

	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	@Autowired
	DepartementRepository deptRepoistory;
	
	public int ajouterEntreprise(Entreprise entreprise) {
		logger.info("adding Entreprise...");
		entrepriseRepoistory.save(entreprise);
		logger.info("Entreprise added!");

		return entreprise.getId();
	}

	public int ajouterDepartement(Departement dep) {
		logger.info("adding Departement...");

		deptRepoistory.save(dep);
		logger.info("Departement added!");

		return dep.getId();
	}
	
	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
		//Le bout Master de cette relation N:1 est departement  
				//donc il faut rajouter l'entreprise a departement 
				// ==> c'est l'objet departement(le master) qui va mettre a jour l'association
				//Rappel : la classe qui contient mappedBy represente le bout Slave
				//Rappel : Dans une relation oneToMany le mappedBy doit etre du cote one.
				logger.info("affecting Departement...");

				Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).get();
				Departement depManagedEntity = deptRepoistory.findById(depId).get();
				
				depManagedEntity.setEntreprise(entrepriseManagedEntity);
				deptRepoistory.save(depManagedEntity);
				
				logger.info("Departement affected!");

		
	}
	
	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		logger.info("getting Departements...");

		Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).get();
		List<String> depNames = new ArrayList<>();
		for(Departement dep : entrepriseManagedEntity.getDepartements()){
			depNames.add(dep.getName());
		}
		logger.debug("Departemens: "+depNames);

		return depNames;
	}

	@Transactional
	public void deleteEntrepriseById(int entrepriseId) {
		logger.warn("Deleting entreprise..action cannot be reversed!");

		entrepriseRepoistory.delete(entrepriseRepoistory.findById(entrepriseId).get());	
		logger.info("Entreprise deleted!");

	}

	@Transactional
	public void deleteDepartementById(int depId) {
		logger.warn("Deleting departement..action cannot be reversed!");

		deptRepoistory.delete(deptRepoistory.findById(depId).get());	
		logger.info("Departement deleted!");

	}


	public Entreprise getEntrepriseById(int entrepriseId) {
		logger.info("retrieving entreprise...");

		return entrepriseRepoistory.findById(entrepriseId).get();	
	}

}
