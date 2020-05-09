package dao;

import domain.Artikel;

import javax.persistence.EntityManager;

public class ArtikelDao extends AbstractDao {

    public ArtikelDao(EntityManager em){
        super.em = em;
    }
}
