package modeles;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn( name = "id" )
public class Activite extends Service {

    @Column
    private String type;

    public String getType() {
        return type;
    }

    public void setType( String type ) {
        this.type = type;
    }

    @Column
    private Integer duree;

    public Integer getDuree() {
        return duree;
    }

    public void setDuree( Integer duree ) {
        this.duree = duree;
    }

    @Column( name = "heure_Debut" )
    private String heureDebut;

    public String getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut( String heureDebut ) {
        this.heureDebut = heureDebut;
    }

    @Column( name = "heure_Fin" )
    private String heureFin;

    public String getHeureFin() {
        return heureFin;
    }

    public void setHeureFin( String heureFin ) {
        this.heureFin = heureFin;
    }

    @Column( name = "marge_Negociee" )
    private Integer margeNegociee;

}
