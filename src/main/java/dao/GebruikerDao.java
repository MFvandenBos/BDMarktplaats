package dao;

import javax.persistence.EntityManager;

public class GebruikerDao extends AbstractDao {

    public  GebruikerDao(EntityManager em){
        super.em = em;
    }
}
