package tn.esprit.spring.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.services.IEntrepriseService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EntrepriseServiceTest {
	
@Autowired
IEntrepriseService ies;

@Test
public void ajouterEntrepriseTest()
{
	Entreprise e = new Entreprise("Entreprise", "Entreprise1"); 
	int er = ies.ajouterEntreprise(e); 
	assertEquals(e.getId(), er);
}

@Test
public void ajouterDepartementTest()
{
	Departement d = new Departement("Departement1"); 
	int dr = ies.ajouterDepartement(d); 
	assertEquals(d.getId(), dr);
}

@Test
public void affecterDepartementAEntrepriseTest()
{
	Entreprise e = new Entreprise("Entreprise", "Entreprise2"); 
	Departement d = new Departement("Departement2"); 
	int er = ies.ajouterEntreprise(e); 
	e.setId(er);
	d.setEntreprise(e);

	int dr = ies.ajouterDepartement(d); 

    ies.affecterDepartementAEntreprise(dr,er); 
	assertEquals(e.getId(), d.getEntreprise().getId());
}

@Test
public void getAllDepartementsNamesByEntrepriseTest()
{
	List<String> listDeparts = ies.getAllDepartementsNamesByEntreprise(5); 
	assertEquals(1, listDeparts.size());
	
}

@Test
public void deleteEntrepriseByIdTest() 
{
	
	Entreprise e = new Entreprise("Entreprise", "Entreprise3"); 
	int er = ies.ajouterEntreprise(e); 
	ies.deleteEntrepriseById(er);  	
	
	Entreprise erd=	ies.getEntrepriseById(er);  	

    assertNotNull(erd);

	
	
}

@Test
public void deleteDepartementByIdTest()
{
	
	Entreprise e = new Entreprise("Entreprise", "Entreprise4"); 
	Departement d = new Departement("Departement4"); 
	int er = ies.ajouterEntreprise(e); 
	e.setId(er);
	d.setEntreprise(e);
	int dr = ies.ajouterDepartement(d); 
    ies.affecterDepartementAEntreprise(dr,er); 
    
	ies.deleteDepartementById(dr);  	

	List<String> listDeparts = ies.getAllDepartementsNamesByEntreprise(er); 

	assertEquals(0, listDeparts.size()	);

	
	
	
}

@Test
public void getEntrepriseByIdTest()
{
	
	Entreprise e = new Entreprise("Entreprise", "Entreprise4"); 
	int er = ies.ajouterEntreprise(e); 
	
	//List<String> listDeparts = ies.getAllDepartementsNamesByEntreprise(er); 
	Entreprise erd=	ies.getEntrepriseById(er);  	

	assertEquals(e.getId(), erd.getId());

	
	
}

}
