package modeles;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Offre {

    @Id
    @GeneratedValue
    @Column
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    @Column( name = "nb_Personnes" )
    private Integer nbPersonnes;

    public Integer getNbPersonnes() {
        return nbPersonnes;
    }

    public void setNbPersonnes( Integer nbPersonnes ) {
        this.nbPersonnes = nbPersonnes;
    }

    @Column
    private Double prix;

    public Double getPrix() {
        return prix;
    }

    public void setPrix( Double prix ) {
        this.prix = prix;
    }

    @Column
    private String descriptif;

    public String getDescriptif() {
        return descriptif;
    }

    public void setDescriptif( String descriptif ) {
        this.descriptif = descriptif;
    }

    // chaque Offre est proposé par un seul prestataire
    @ManyToOne( fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE } )
    @JoinColumn( name = "id_Service" )
    Service prestataire;

    public Service getPrestataire() {
        return prestataire;
    }

    public void setPrestataire( Service prestataire ) {
        this.prestataire = prestataire;
    }

}
