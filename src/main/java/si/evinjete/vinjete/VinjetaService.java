package si.evinjete.vinjete;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
public class VinjetaService {
    @PersistenceContext(unitName = "evinjete-vinjete")
    private EntityManager em;

    public Vinjeta getVinjeta(String VinjetaId) {
        Integer id = Integer.valueOf(VinjetaId);
        return em.find(Vinjeta.class, id);
    }

    public List<Vinjeta> getVinjete() {
        List<Vinjeta> vinjete = em
                .createNamedQuery("Vinjeta.findVinjete", Vinjeta.class)
                .getResultList();

        return vinjete;
    }

    public List<Vinjeta> getVinjetaFromTablica(String t){
        List<Vinjeta> v = em
                .createNamedQuery("Vinjeta.findVinjetaFromTablica", Vinjeta.class)
                .setParameter("tablica", t)
                .getResultList();

        return v;
    }

    @Transactional
    public void saveVinjeta(Vinjeta vinjeta) {
        if (vinjeta != null) {
            em.persist(vinjeta);
        }
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void deleteVinjeta(String VinjetaId) {
        Vinjeta vinjeta = em.find(Vinjeta.class, VinjetaId);
        if (vinjeta != null) {
            em.remove(vinjeta);
        }
    }

    @Transactional
    public Vinjeta addNewVinjeta(Vinjeta vinjeta) {
        if (vinjeta != null) {
            em.persist(vinjeta);
        }
        return vinjeta;
    }
}