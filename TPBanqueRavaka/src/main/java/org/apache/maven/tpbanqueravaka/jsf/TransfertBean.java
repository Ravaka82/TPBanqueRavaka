package org.apache.maven.tpbanqueravaka.jsf;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;
import org.apache.maven.tpbanqueravaka.entity.CompteBancaire;
import org.apache.maven.tpbanqueravaka.jsf.util.Util;
import org.apache.maven.tpbanqueravaka.service.GestionnaireCompte;

@Named(value = "transfertBean")
@ViewScoped
public class TransfertBean implements Serializable {

    private String idSource;
    private String idDestination;
    private int montant;

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    public TransfertBean() {
    }

    public String getIdSource() {
        return idSource;
    }

    public void setIdSource(String idSource) {
        this.idSource = idSource;
    }

    public String getIdDestination() {
        return idDestination;
    }

    public void setIdDestination(String idDestination) {
        this.idDestination = idDestination;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public String transferer() {
        boolean erreur = false;

        CompteBancaire source = gestionnaireCompte.getCompteById(idSource);
        if (source == null) {
            Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:source");
            erreur = true;
        } else {
            if (source.getSolde() < montant) {
                Util.messageErreur("Solde insuffisant dans le compte source !", "Solde insuffisant dans le compte source !", "form:montant");
                erreur = true;
            }
        }

        if (erreur) {
            // En cas d'erreur, rester sur la même page
            return null;
        }

        CompteBancaire destination = gestionnaireCompte.getCompteById(idDestination);
        gestionnaireCompte.transfererArgent(source, destination, montant);

        // Récupérer les noms des clients titulaires des comptes
        String nomSource = source.getNomProprietaire();
        String nomDestination = destination.getNomProprietaire();

        // Message de succès avec les noms des clients et le montant du transfert
        String messageSuccess = String.format("Transfert de %d€ de %s vers %s effectué avec succès.",
                montant, nomSource, nomDestination);

        // Ajout du montant du transfert dans le message de succès
        Util.addFlashInfoMessage(messageSuccess + " Montant : " + montant + "€");

        return "listeComptes.xhtml?faces-redirect=true";
    }
}
