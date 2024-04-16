package org.apache.maven.tpbanqueravaka.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;
import org.apache.maven.tpbanqueravaka.entity.CompteBancaire;
import org.apache.maven.tpbanqueravaka.jsf.util.Util;
import org.apache.maven.tpbanqueravaka.service.GestionnaireCompte;

@Named(value = "detailsCompteBean")
@ViewScoped
public class DetailsCompteBean implements Serializable {

    private Long idCompte;
    private CompteBancaire compte;
    private int montant;
    private String typeMouvement;

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    @PostConstruct
    public void init() {
        if (idCompte != null) {
            compte = gestionnaireCompte.getCompteById(idCompte);
        }
    }


    public Long getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(Long idCompte) {
        this.idCompte = idCompte;
    }

    public CompteBancaire getCompte() {
        return compte;
    }

    public void setCompte(CompteBancaire compte) {
        this.compte = compte;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public String getTypeMouvement() {
        return typeMouvement;
    }

    public void setTypeMouvement(String typeMouvement) {
        this.typeMouvement = typeMouvement;
    }

    public String effectuerMouvement() {
        if (typeMouvement.equals("ajouter")) {
            gestionnaireCompte.deposer(compte, montant);
        } else if (typeMouvement.equals("retirer")) {
            gestionnaireCompte.retirer(compte, montant);
        }
        Util.addFlashInfoMessage("Mouvement enregistré sur le compte de " + compte.getNomProprietaire());
        // Rediriger vers la page listeComptes.xhtml après l'enregistrement du mouvement
        return "listeComptes.xhtml?faces-redirect=true";
    }

}
