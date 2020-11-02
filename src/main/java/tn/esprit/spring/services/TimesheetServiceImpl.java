package tn.esprit.spring.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.entities.TimesheetPK;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.MissionRepository;
import tn.esprit.spring.repository.TimesheetRepository;

@Service
public class TimesheetServiceImpl implements ITimesheetService {
    
	private static Logger log = LogManager.getLogger(TimesheetServiceImpl.class);

	

	@Autowired
	MissionRepository missionRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;
	@Autowired
	EmployeRepository employeRepository;
	
	
	public int ajouterMission(Mission mission) {
			log.info("in Add Mission method");
			log.debug("add mission going to be started");


				missionRepository.save(mission);
				log.debug("Mission added successfully");
		
 

		return mission.getId();
	}
    
	public void affecterMissionADepartement(int missionId, int depId) {
		
			log.info("in Affect MissionToDepartment method");
			Optional<Mission> missionManagedEntity = missionRepository.findById(missionId);
			log.info("Mission Found");
			Optional<Departement> deptManagedEntity = deptRepoistory.findById(depId);

			log.info("Deparrtment Found");
if(missionManagedEntity.isPresent() && deptManagedEntity.isPresent() ){
	missionManagedEntity.get().setDepartement(deptManagedEntity.get());
	log.debug("Mission affected successfully");

	log.info("Going to be saved into Database");
	missionRepository.save(missionManagedEntity.get());
	log.debug("Successfully saved into Database");
}
		
		
	}

	public void ajouterTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin) {
	
			log.info("in AddTimesheet method");

			TimesheetPK timesheetPK = new TimesheetPK();
			timesheetPK.setDateDebut(dateDebut);
			timesheetPK.setDateFin(dateFin);
			timesheetPK.setIdEmploye(employeId);
			timesheetPK.setIdMission(missionId);
			log.info("TimeSheetPK executed successfully");

			Timesheet timesheet = new Timesheet();
			timesheet.setTimesheetPK(timesheetPK);
			timesheet.setValide(false); //par defaut non valide
			log.debug("Passed TimesheetPK param successfully");
			log.info("Going to be saved into Database");

			
			timesheetRepository.save(timesheet);
			log.debug("Successfully saved into Database");
		
		
	}

	
	public void validerTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin, int validateurId) {
	
			log.info("in ValiderTimesheet method");
			Optional<Employe> valManagedEntity = employeRepository.findById(validateurId);

			log.info("Employe validateur Found");
			Optional<Mission> misManagedEntity = missionRepository.findById(missionId);
			log.info("Mission Found");
			if(valManagedEntity.isPresent() && (!valManagedEntity.get().getRole().equals(Role.CHEF_DEPARTEMENT)))
			{
				
					log.debug("l'employe doit etre chef de departement pour valider une feuille de temps !");

					return;
					
		
			}
			boolean chefDeLaMission = false;
			if(valManagedEntity.isPresent() && misManagedEntity.isPresent()){
				for(Departement dep : valManagedEntity.get().getDepartements()){
					
					if(dep.getId() == misManagedEntity.get().getDepartement().getId()){
						log.debug("le vrai chef de departement ");

						chefDeLaMission = true;
						break;
					}
				}
			}
		
			if(!chefDeLaMission){
				log.debug("l'employe doit etre chef de departement de la mission en question");

				return;
			}
			
			TimesheetPK timesheetPK = new TimesheetPK(missionId, employeId, dateDebut, dateFin);

			Timesheet timesheet =timesheetRepository.findBytimesheetPK(timesheetPK);
			log.debug("timesheet found by timesheetpk");

			timesheet.setValide(true);
			log.debug("successfully validated");

			//Comment Lire une date de la base de données

			

	
		
	}

	
	public List<Mission> findAllMissionByEmployeJPQL(int employeId) {
		log.info("In findAllMissionByEmployeJPQL method");
		log.debug("findAllMissionByEmployeJPQL Started! ");


		return timesheetRepository.findAllMissionByEmployeJPQL(employeId);
	}

	
	public List<Employe> getAllEmployeByMission(int missionId) {
		log.info("In getAllEmployeByMission method");
		log.debug("getAllEmployeByMission Started! ");
		return timesheetRepository.getAllEmployeByMission(missionId);
	}

}
