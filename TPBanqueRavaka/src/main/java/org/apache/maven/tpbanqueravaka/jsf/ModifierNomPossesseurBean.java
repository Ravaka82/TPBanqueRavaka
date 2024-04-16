package org.apache.maven.tpbanqueravaka.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import org.apache.maven.tpbanqueravaka.entity.CompteBancaire;
import org.apache.maven.tpbanqueravaka.service.GestionnaireCompte;
import org.apache.maven.tpbanqueravaka.jsf.util.Util;

@Named("modifierNomPossesseurBean")
@ViewScoped
public class ModifierNomPossesseurBean implements Serializable {

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    private Long compteId;
    private String nouveauNom;
    private int nouveauSolde;
    private CompteBancaire compteBancaire;

    @PostConstruct
    public void init() {
        if (compteId != null) {
            compteBancaire = gestionnaireCompte.getCompteById(compteId);
        }
    }

    public String modifierNomPossesseur() {
        if (compteBancaire != null) {
            String ancienNom = compteBancaire.getNomProprietaire();
            int ancienSolde = compteBancaire.getSolde();
            compteBancaire.setNomProprietaire(nouveauNom);
            compteBancaire.setSolde(nouveauSolde);
            gestionnaireCompte.update(compteBancaire);
            Util.addFlashInfoMessage("Nom " + ancienNom + " changé en " + nouveauNom + " et solde modifié de " + ancienSolde + " à " + nouveauSolde);
            return "listeComptes?faces-redirect=true";
        } else {
            // Gérer le cas où le compte est null
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Le compte est null"));
            return null;
        }
    }

    // Getters et Setters
    public Long getCompteId() {
        return compteId;
    }

    public void setCompteId(Long compteId) {
        this.compteId = compteId;
    }

    public String getNouveauNom() {
        return nouveauNom;
    }

    public void setNouveauNom(String nouveauNom) {
        this.nouveauNom = nouveauNom;
    }

    public int getNouveauSolde() {
        return nouveauSolde;
    }

    public void setNouveauSolde(int nouveauSolde) {
        this.nouveauSolde = nouveauSolde;
    }

    public CompteBancaire getCompteBancaire() {
        return compteBancaire;
    }

    public void setCompteBancaire(CompteBancaire compteBancaire) {
        this.compteBancaire = compteBancaire;
    }
}
