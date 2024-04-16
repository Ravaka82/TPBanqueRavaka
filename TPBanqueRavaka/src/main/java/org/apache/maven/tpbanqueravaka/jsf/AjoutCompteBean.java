package org.apache.maven.tpbanqueravaka.jsf;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;
import org.apache.maven.tpbanqueravaka.entity.CompteBancaire;
import org.apache.maven.tpbanqueravaka.jsf.util.Util;
import org.apache.maven.tpbanqueravaka.service.GestionnaireCompte;

@Named(value = "ajoutCompteBean")
@ViewScoped
public class AjoutCompteBean implements Serializable {

    private String nomProprietaire;
    private int solde;

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    public AjoutCompteBean() {
    }

    public String getNomProprietaire() {
        return nomProprietaire;
    }

    public void setNomProprietaire(String nomProprietaire) {
        this.nomProprietaire = nomProprietaire;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    public String creerCompte() {
        if (solde < 0) {
            Util.messageErreur("Le solde ne peut pas être négatif !", "Le solde ne peut pas être négatif !", "form:solde");
            return null;
        }

        CompteBancaire compte = new CompteBancaire(nomProprietaire, solde);
        gestionnaireCompte.creerCompte(compte);

        Util.addFlashInfoMessage("Compte créé avec succès !");
        return "listeComptes.xhtml?faces-redirect=true";
    }
}
