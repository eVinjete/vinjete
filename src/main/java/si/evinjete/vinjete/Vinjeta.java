package si.evinjete.vinjete;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "vinjeta")
@NamedQueries({
        @NamedQuery(
                name = "Vinjeta.findVinjete",
                query = "SELECT v " +
                        "FROM Vinjeta v"
        ),
})
@NamedNativeQueries(
        {
                @NamedNativeQuery(
                        name = "Vinjeta.findVinjetaFromTablica",
                        query = "SELECT * FROM vinjete WHERE numberplate LIKE :tablica", resultClass = Vinjeta.class
                )
        }
)
public class Vinjeta implements Serializable {

    @Column(nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "clientId", nullable = false, updatable = false)
    private Integer clientId;
    @Column(name = "numberPlate", nullable = false, updatable = false)
    private String numberPlate;

    @Column(name = "timestamp", nullable = false, updatable = false)
    private Date timestamp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer id) {
        this.clientId = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

}
