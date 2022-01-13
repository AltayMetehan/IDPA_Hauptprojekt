/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package ch.bbbaden.lohnabrechner.controller;

import ch.bbbaden.lohnabrechner.model.Arbeitnehmer;
import ch.bbbaden.lohnabrechner.model.ArbeitnehmerDAO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.swing.JOptionPane;

/**
 *
 * @author miksh
 */
@Named(value = "arbeitnehmerController")
@SessionScoped
public class ArbeitnehmerController implements Serializable {

    private ArbeitnehmerDAO dao = new ArbeitnehmerDAO();

    private boolean kidsTrueFalse = false;
    private String feiertage;
    private String monatslohn;
    private String anzahlKinderKlein = "0";
    private String anzahlKinderGross = "0";

    //Konstante Werte/////////////////////////////////////////////////////////////////
    private final BigDecimal feiertagsZuschlag = new BigDecimal(5.90); //in CHF
    private final BigDecimal feiertagsEntschaedigung = new BigDecimal(0.0385);
    private final BigDecimal ferienEntschaedigung = new BigDecimal(0.1064);

    private final BigDecimal bvg = new BigDecimal(0.075);
    private final BigDecimal ahvAbzugArbeitnehmer = new BigDecimal(0.053);
    private final BigDecimal alvZuschlag = new BigDecimal(0.011);
    private final BigDecimal nbu = new BigDecimal(0.0827);
    private final BigDecimal ktg = new BigDecimal(0.028);
    /////////////////////////////////////////////////////////////////////////////////

    //Rückgabewerte//////////////////////////////////////////////////////////////////
    private BigDecimal feiertagsZuschlagBetrag;
    private BigDecimal feiertagsEntschaedigungBetrag;
    private BigDecimal ferienEntschaedigungBetrag;
    private BigDecimal extraLohn;

    private BigDecimal bvgBetrag;
    private BigDecimal ahvAbzugArbeitnehmerBetrag;
    private BigDecimal alvZuschlagBetrag;
    private BigDecimal nbuBetrag;
    private BigDecimal ktgBetrag;

    private BigDecimal bruttolohn;
    private BigDecimal nettolohn;
    /////////////////////////////////////////////////////////////////////////////////

    /**
     * Creates a new instance of ArbeitnehmerController
     */
    public ArbeitnehmerController() {

    }

    public void calculate() {
        //hier werden alle mathematischen Operationen ausgefuehrt
        try {
            bruttolohn = new BigDecimal(monatslohn);
            if (bruttolohn.compareTo(BigDecimal.ZERO) > 0) {
                feiertagsZuschlagBetrag = feiertagsZuschlag.multiply(new BigDecimal(feiertage));
                feiertagsEntschaedigungBetrag = bruttolohn.multiply(feiertagsEntschaedigung);
                ferienEntschaedigungBetrag = bruttolohn.multiply(ferienEntschaedigung);

                bruttolohn = bruttolohn.add(feiertagsZuschlagBetrag).add(feiertagsEntschaedigungBetrag).add(ferienEntschaedigungBetrag);

                bvgBetrag = bruttolohn.multiply(bvg);
                ahvAbzugArbeitnehmerBetrag = bruttolohn.multiply(ahvAbzugArbeitnehmer);
                alvZuschlagBetrag = bruttolohn.multiply(alvZuschlag);
                nbuBetrag = bruttolohn.multiply(nbu);
                ktgBetrag = bruttolohn.multiply(ktg);
                extraLohn = bruttolohn.divide(new BigDecimal(12));

                BigDecimal kindergeldKlein = BigDecimal.valueOf(Double.valueOf(anzahlKinderKlein));
                BigDecimal kindergeldGross = BigDecimal.valueOf(Double.valueOf(anzahlKinderGross));

                nettolohn = bruttolohn.subtract(ahvAbzugArbeitnehmerBetrag).subtract(alvZuschlagBetrag).subtract(nbuBetrag).subtract(ktgBetrag).add(kindergeldKlein).add(kindergeldGross).subtract(bvgBetrag);

                ArbeitnehmerDAO dao = new ArbeitnehmerDAO();
                int id = dao.getHighestId() + 1;

                double dnbu = nbuBetrag.doubleValue();
                double dktg = ktgBetrag.doubleValue();
                double dfeiertagsZuschlag = feiertagsZuschlagBetrag.doubleValue() + feiertagsEntschaedigungBetrag.doubleValue();
                double dbvg = bvgBetrag.doubleValue();
                double dmonatslohn = Double.parseDouble(monatslohn);
                double dextraLohn = extraLohn.doubleValue();
                double dahv = ahvAbzugArbeitnehmerBetrag.doubleValue();
                double dalv = alvZuschlagBetrag.doubleValue();
                double dkinderzulagen = kindergeldKlein.add(kindergeldGross).doubleValue();
                double dnettolohn = nettolohn.doubleValue();

                Arbeitnehmer arbeitnehmer = new Arbeitnehmer(id, dnbu, dktg, dfeiertagsZuschlag, dbvg, dmonatslohn, dextraLohn, dahv, dalv, dkinderzulagen, dnettolohn, false);

                dao.insert(arbeitnehmer);
            } else {
                JOptionPane.showMessageDialog(null, "Arme siech gang mal go racks mache. Nur positivi zahle.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Bitte nur Zahlen für die Angabe des Lohnes in CHF verwenden.");
        }
    }

    public String getMonatslohn() {
        return monatslohn;
    }

    public void setMonatslohn(String monatslohn) {
        this.monatslohn = monatslohn;
    }

    public String getAnzahlKinderKlein() {
        return anzahlKinderKlein;
    }

    public void setAnzahlKinderKlein(String anzahlKinderKlein) {
        this.anzahlKinderKlein = anzahlKinderKlein;
    }

    public String getAnzahlKinderGross() {
        return anzahlKinderGross;
    }

    public void setAnzahlKinderGross(String anzahlKinderGross) {
        this.anzahlKinderGross = anzahlKinderGross;
    }

    public BigDecimal getBruttolohn() {
        return bruttolohn;
    }

    public void setBruttolohn(BigDecimal bruttolohn) {
        this.bruttolohn = bruttolohn;
    }

    public BigDecimal getNettolohn() {
        return nettolohn;
    }

    public void setNettolohn(BigDecimal nettolohn) {
        this.nettolohn = nettolohn;
    }

    public BigDecimal getFeiertagsZuschlagBetrag() {
        return feiertagsZuschlagBetrag;
    }

    public void setFeiertagsZuschlagBetrag(BigDecimal feiertagsZuschlagBetrag) {
        this.feiertagsZuschlagBetrag = feiertagsZuschlagBetrag;
    }

    public BigDecimal getFeiertagsEntschaedigungBetrag() {
        return feiertagsEntschaedigungBetrag;
    }

    public void setFeiertagsEntschaedigungBetrag(BigDecimal feiertagsEntschaedigungBetrag) {
        this.feiertagsEntschaedigungBetrag = feiertagsEntschaedigungBetrag;
    }

    public BigDecimal getFerienEntschaedigungBetrag() {
        return ferienEntschaedigungBetrag;
    }

    public void setFerienEntschaedigungBetrag(BigDecimal ferienEntschaedigungBetrag) {
        this.ferienEntschaedigungBetrag = ferienEntschaedigungBetrag;
    }

    public BigDecimal getExtraLohn() {
        return extraLohn;
    }

    public void setExtraLohn(BigDecimal extraLohn) {
        this.extraLohn = extraLohn;
    }

    public BigDecimal getBvgBetrag() {
        return bvgBetrag;
    }

    public void setBvgBetrag(BigDecimal bvgBetrag) {
        this.bvgBetrag = bvgBetrag;
    }

    public BigDecimal getAhvAbzugArbeitnehmerBetrag() {
        return ahvAbzugArbeitnehmerBetrag;
    }

    public void setAhvAbzugArbeitnehmerBetrag(BigDecimal ahvAbzugArbeitnehmerBetrag) {
        this.ahvAbzugArbeitnehmerBetrag = ahvAbzugArbeitnehmerBetrag;
    }

    public BigDecimal getAlvZuschlagBetrag() {
        return alvZuschlagBetrag;
    }

    public void setAlvZuschlagBetrag(BigDecimal alvZuschlagBetrag) {
        this.alvZuschlagBetrag = alvZuschlagBetrag;
    }

    public BigDecimal getNbuBetrag() {
        return nbuBetrag;
    }

    public void setNbuBetrag(BigDecimal nbuBetrag) {
        this.nbuBetrag = nbuBetrag;
    }

    public BigDecimal getKtgBetrag() {
        return ktgBetrag;
    }

    public void setKtgBetrag(BigDecimal ktgBetrag) {
        this.ktgBetrag = ktgBetrag;
    }

    public String getFeiertage() {
        return feiertage;
    }

    public void setFeiertage(String feiertage) {
        this.feiertage = feiertage;
    }

    public boolean isKidsTrueFalse() {
        return kidsTrueFalse;
    }

    public void setKidsTrueFalse(boolean kidsTrueFalse) {
        this.kidsTrueFalse = kidsTrueFalse;
    }

}
