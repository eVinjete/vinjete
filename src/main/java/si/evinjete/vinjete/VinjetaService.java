package si.evinjete.vinjete;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
public class VinjetaService {
    @PersistenceContext(unitName = "evinjete-vinjeta")
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

    public List<Vinjeta> getVinjetaFromUporabnik(Integer uporabnikId){
        List<Vinjeta> vinjete = em
                .createNamedQuery("Vinjeta.findVinjetaFromUporabnikId", Vinjeta.class)
                .setParameter("id", uporabnikId)
                .getResultList();

        return vinjete;
    }

    public List<Vinjeta> getVinjetaFromTablica(String t){
        System.out.print("INFO -- Recieved query for tablica: " + t);
        List<Vinjeta> v = em
                .createNamedQuery("Vinjeta.findVinjetaFromTablica", Vinjeta.class)
                .setParameter("tablica", t)
                .getResultList();

        if(v.isEmpty()){
            System.out.println("  -- no entry found");
        }
        else{
            System.out.println("  -- found for client id: " + v.get(0).getId());
        }

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
        Integer id = Integer.valueOf(VinjetaId);
        Vinjeta vinjeta = em.find(Vinjeta.class, id);
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