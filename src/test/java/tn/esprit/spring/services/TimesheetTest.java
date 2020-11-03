package tn.esprit.spring.services;

import static org.junit.Assert.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Role;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TimesheetTest {
	
	 @Autowired
	 ITimesheetService timesheetService;
	
	int missId;
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	String dateString = format.format( new Date());
	
	 

	Mission mission1 = new Mission("mymission1","mymission1");
	Departement departement =new Departement("yafetDepartement");
	Mission mission2 = new Mission("mymission2","mymission2");
	Employe employe = new Employe("test","test","test",true,Role.ADMINISTRATEUR);

	@Test
	public void addMissionTest() {
		 int missionId = timesheetService.ajouterMission(mission1);
		 missId = missionId;
		 assertEquals(mission1.getId(), missionId);
	}

	
	
	@Test
	public void findAllMissionByEmployeJPQLTest() {
		assertNotNull(timesheetService.findAllMissionByEmployeJPQL(mission1.getId()));

	}
	
	
	@Test
	public void getAllEmployeByMissionTest() {

		assertNotNull(timesheetService.getAllEmployeByMission(mission1.getId()));
	
	}



}
