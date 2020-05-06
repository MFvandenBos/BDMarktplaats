package dao;

import domain.AbstractEntity;
import org.slf4j.Logger;

import javax.persistence.EntityManager;

//import static util.Util.logger;

public abstract class AbstractDao {

    protected EntityManager em;
    //protected Logger log = logger(getClass());

    public void insert(AbstractEntity f)  {
        em.getTransaction().begin();
        em.persist(f);
        em.getTransaction().commit();
    }

    public void insert(AbstractEntity ... x)  {
        for (AbstractEntity p: x) {
            insert(p);
        }
    }

    public AbstractEntity update(AbstractEntity f)  {
        em.getTransaction().begin();
        AbstractEntity merged = em.merge(f);
        em.getTransaction().commit();
        return merged;
    }

    public void update(AbstractEntity ... x)  {

        //TODO: Batch updates
        for (AbstractEntity p: x) {
            update(p);
        }
    }
}