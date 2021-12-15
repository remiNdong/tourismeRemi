package modeles;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Internaute {

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

    @Column
    private String nom;

    public void setNom( String n ) {
        nom = n;
    }

    public String getNom() {
        return nom;
    }

    @Column
    private String prenom;

    public void setPrenom( String p ) {
        prenom = p;
    }

    public String getPrenom() {
        return prenom;
    }

    @Column
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail( String e ) {
        email = e;
    }

    @OneToMany( mappedBy = "internaute" )
    private Set<Notation> notations = new HashSet<Notation>();

    public Set<Notation> getNotations() {
        return notations;
    }

    public void addNotations( Notation n ) {

        n.setInternaute( this );
        notations.add( n );
    }

}
