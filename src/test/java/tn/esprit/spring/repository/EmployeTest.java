package tn.esprit.spring.repository;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.entities.TimesheetPK;
import tn.esprit.spring.services.TimesheetServiceImpl;

import org.junit.Test;



@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
//@ComponentScan({"tn.esprit.spring.services"})

public class EmployeTest {
	private static Logger LOGGER = LogManager.getLogger(TimesheetServiceImpl.class);
	
	@Autowired
	private EmployeRepository repo;
	private TimesheetServiceImpl service;
	@Autowired
	private TestEntityManager entityManager;
	Employe employe = new Employe("test","test","test",true,Role.ADMINISTRATEUR);
	//Entreprise entreprise = new Entreprise ();
	
	
	public void employeNamesTest() {
        LOGGER.debug("getting employes names");

		assertNotNull(repo.employeNames());
	
	}
	
	public void employeNamesFailureTest() {
        LOGGER.debug("getting employes names failed");

		assertNotNull(repo.employeNames());
	
	}
	
	
	
	
	public void countempTest() {
        LOGGER.debug("counting employes in database ");

		assertNotNull(repo.countemp());
	
	}
	
	//public void getAllEmployeByEntreprisecTest() {
		//entityManager.persist(entreprise);
		//assertNotNull(repo.getAllEmployeByEntreprisec(entreprise));
		
	//}
	
	
	public void getSalaireByEmployeIdJPQLTest() {
		
		assertNotNull(repo.getSalaireByEmployeIdJPQL(3));
		
		
	}
	
	

	
	public void getSalaireMoyenByDepartementIdTest() {
		
		assertNotNull(repo.getSalaireMoyenByDepartementId(3));
		
		
		
	}
	
	
	
	

}
