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
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.entities.TimesheetPK;
import tn.esprit.spring.services.TimesheetServiceImpl;
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
//@ComponentScan({"tn.esprit.spring.services"})
public class TimesheetTest {
	
	private static Logger LOGGER = LogManager.getLogger(TimesheetServiceImpl.class);

	@Autowired
	private TimesheetRepository repo;
	private TimesheetServiceImpl service;
	@Autowired
	private TestEntityManager entityManager;
	Date date1 = new java.sql.Date(2020, 12, 29);
	Date date2 = new java.sql.Date(2020, 12, 29);
	Mission mission1 = new Mission("mymission1","mymission1");
	Mission mission2 = new Mission("mymission2","mymission2");
	TimesheetPK timesheetpk = new TimesheetPK(1,1, date1, date2);
	Employe employe = new Employe("test","test","test",true,Role.ADMINISTRATEUR);



	
	@Test
	public void getAllEmployeByMissionTest() {
        LOGGER.debug("getAllEmployeByMission going to be started");

		assertNotNull(repo.getAllEmployeByMission(1));
	
	}
	@Test
	public void findAllMissionByEmployeJPQLTest() {
		assertNotNull(repo.findAllMissionByEmployeJPQL(1));

		
	}

	@Test
	public void getTimesheetsByMissionAndDateTest() {
		entityManager.persist(employe);
		entityManager.persist(mission1);
		
		assertNotNull(repo.getTimesheetsByMissionAndDate(employe,mission1,date1,date2));

		
	}
	@Test
	public void findBytimesheetPKTest() {
	
		assertNull(repo.findBytimesheetPK(timesheetpk));
		
	}
	/*@Test
	public void AjouterMission() {

		//service.ajouterMission(mission1);

		assertNotNull(repo.existsById(mission1.getId()));
		
	}*/

}
