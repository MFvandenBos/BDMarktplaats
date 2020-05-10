package dao;

import javax.persistence.EntityManager;

public class CategorieDao extends AbstractDao {

    public CategorieDao(EntityManager em){
        super.em = em;
    }
}
