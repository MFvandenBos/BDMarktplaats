package dao;

import dao.exceptions.GebruikerNotFoundException;
import domain.Gebruiker;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class GebruikerDao extends AbstractDao {

    public  GebruikerDao(EntityManager em){
        super.em = em;
    }

    public Gebruiker selectWithEmail(String email) throws GebruikerNotFoundException {
        TypedQuery<Gebruiker> sel = em.createQuery("select p from Gebruiker p where p.emailAdress = :firstarg", Gebruiker.class);
        sel.setParameter("firstarg", email);
        List<Gebruiker>  resultList = sel.getResultList();
        if(resultList.size() == 1){
            return resultList.get(0);
        }else {
            throw new GebruikerNotFoundException();
        }
    }
}
