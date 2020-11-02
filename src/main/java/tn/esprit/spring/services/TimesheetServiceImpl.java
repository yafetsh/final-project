package tn.esprit.spring.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.entities.TimesheetPK;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.repository.MissionRepository;
import tn.esprit.spring.repository.TimesheetRepository;

@Service
public class TimesheetServiceImpl implements ITimesheetService {
    
	private static Logger LOGGER = LogManager.getLogger(TimesheetServiceImpl.class);

	

	@Autowired
	MissionRepository missionRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;
	@Autowired
	EmployeRepository employeRepository;
	@Autowired
	EntrepriseRepository entrepriseRepository;
	
	
	public int ajouterMission(Mission mission) {
		try {
			   LOGGER.info("in Add Mission method");
		        LOGGER.debug("add mission going to be started");


				missionRepository.save(mission);
		        LOGGER.debug("Mission added successfully");
		}
     catch (Exception e) {
    	 LOGGER.error("Error in AddMission() : " + e);
    	 
     }

		return mission.getId();
	}
    
	public void affecterMissionADepartement(int missionId, int depId) {
		try {
			   LOGGER.info("in Affect MissionToDepartment method");

			
			Mission mission = missionRepository.findById(missionId).get();
	        LOGGER.info("Mission Found");

			Departement dep = deptRepoistory.findById(depId).get();
	        LOGGER.info("Deparrtment Found");

			mission.setDepartement(dep);
	        LOGGER.debug("Mission affected successfully");

	        LOGGER.info("Going to be saved into Database");
			missionRepository.save(mission);
	        LOGGER.debug("Successfully saved into Database");

			
			
		}
		catch(Exception e) {
	    	 LOGGER.error("Error in AffectMissionToDepartment() : " + e);

			
		}
		
		
	}

	public void ajouterTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin) {
		try {
			   LOGGER.info("in AddTimesheet method");

			TimesheetPK timesheetPK = new TimesheetPK();
			timesheetPK.setDateDebut(dateDebut);
			timesheetPK.setDateFin(dateFin);
			timesheetPK.setIdEmploye(employeId);
			timesheetPK.setIdMission(missionId);
	        LOGGER.info("TimeSheetPK executed successfully");

			Timesheet timesheet = new Timesheet();
			timesheet.setTimesheetPK(timesheetPK);
			timesheet.setValide(false); //par defaut non valide
	        LOGGER.debug("Passed TimesheetPK param successfully");
	        LOGGER.info("Going to be saved into Database");

			
			timesheetRepository.save(timesheet);
	        LOGGER.debug("Successfully saved into Database");

		}
		catch (Exception e) {
	    	 LOGGER.error("Error in AddTimesheet() : " + e);

			
		}
		
		
	}

	
	public void validerTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin, int validateurId) {
		try {
			   LOGGER.info("in ValiderTimesheet method");
			Employe validateur = employeRepository.findById(validateurId).get();
	        LOGGER.info("Employe validateur Found");

			Mission mission = missionRepository.findById(missionId).get();
	        LOGGER.info("Mission Found");

			//verifier s'il est un chef de departement (interet des enum)
			if(!validateur.getRole().equals(Role.CHEF_DEPARTEMENT)){
		        LOGGER.debug("l'employe doit etre chef de departement pour valider une feuille de temps !");

				return;
			}
			//verifier s'il est le chef de departement de la mission en question
			boolean chefDeLaMission = false;
			for(Departement dep : validateur.getDepartements()){
				
				if(dep.getId() == mission.getDepartement().getId()){
			        LOGGER.debug("le vrai chef de departement ");

					chefDeLaMission = true;
					break;
				}
			}
			if(!chefDeLaMission){
		        LOGGER.debug("l'employe doit etre chef de departement de la mission en question");

				//System.out.println("l'employe doit etre chef de departement de la mission en question");
				return;
			}
			
			TimesheetPK timesheetPK = new TimesheetPK(missionId, employeId, dateDebut, dateFin);

			Timesheet timesheet =timesheetRepository.findBytimesheetPK(timesheetPK);
	        LOGGER.debug("timesheet found by timesheetpk");

			timesheet.setValide(true);
	        LOGGER.debug("successfully validated");

			//Comment Lire une date de la base de données
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			System.out.println("dateDebut : " + dateFormat.format(timesheet.getTimesheetPK().getDateDebut()));
	        LOGGER.debug("dateDebut : " + dateFormat.format(timesheet.getTimesheetPK().getDateDebut()));

			
		} catch (Exception e) {
	    	 LOGGER.error("Error in ValidateTimesheet() : " + e);

			
		}
	
		
	}

	
	public List<Mission> findAllMissionByEmployeJPQL(int employeId) {
		   LOGGER.info("In findAllMissionByEmployeJPQL method");
	        LOGGER.debug("findAllMissionByEmployeJPQL Started! ");


		return timesheetRepository.findAllMissionByEmployeJPQL(employeId);
	}

	
	public List<Employe> getAllEmployeByMission(int missionId) {
		   LOGGER.info("In getAllEmployeByMission method");
	        LOGGER.debug("getAllEmployeByMission Started! ");
		return timesheetRepository.getAllEmployeByMission(missionId);
	}
	/////////logs for employe
	
	public int ajouterEmploye(Employe employe) {
		try {
			   LOGGER.info("add employe method");
		        LOGGER.debug("An emplyoe is going to be added");


				employeRepository.save(employe);
		        LOGGER.debug("Employe  added successfully");
		}
     catch (Exception e) {
    	 LOGGER.error("Error in AddMission() : " + e);
    	 
     }

		return employe.getId();
	}
	
	



public int countemp() {
	   LOGGER.info("count employes method");
     LOGGER.debug("method count employe started ");


	return employeRepository.countemp();
}

public List<String> employeNames(){
	
	   LOGGER.info("method to get all employe names ");
	     LOGGER.debug("method employeNames started ");


		return employeRepository.employeNames();
	
	
}


public List<Employe> getAllEmployeByEntreprisec( Entreprise entreprise){
	   LOGGER.info("getAllEmployeByEntreprisec");
       LOGGER.debug("getAllEmployeByEntreprisec Started! ");
	return employeRepository.getAllEmployeByEntreprisec( entreprise);


}
public float getSalaireByEmployeIdJPQL (int employeId) {
	LOGGER.info("getAllEmployeByEntreprisec");
    LOGGER.debug("getAllEmployeByEntreprisec Started! ");
	return employeRepository.getSalaireByEmployeIdJPQL( employeId);
	
	
	
}


public Double getSalaireMoyenByDepartementId(int departementId) {
	LOGGER.info("getAllEmployeByEntreprisec");
    LOGGER.debug("getAllEmployeByEntreprisec Started! ");
	return employeRepository.getSalaireMoyenByDepartementId( departementId);
	
	
}







}



