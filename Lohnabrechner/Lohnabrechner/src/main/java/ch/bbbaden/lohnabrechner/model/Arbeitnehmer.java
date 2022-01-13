package ch.bbbaden.lohnabrechner.model;

import java.io.Serializable;

/**
 *
 * @author Metehan
 */
public class Arbeitnehmer implements Serializable{
    private int id;
    private double NBU;
    private double KTG;
    private double SonnFeiertagszuschlag;
    private double BVG_Pensionsekasse;
    private double monatslohn;
    private double dreizehnMLAuszahlung;
    private double AHV;
    private double ALV;
    private double kinderzulage;
    private double nettolohn;
    private boolean isAdminArbeitnehmer;

    public Arbeitnehmer(int id, double NBU, double KTG, double SonnFeiertagszuschlag, double BVG_Pensionsekasse, double monatslohn, double dreizehnMLAuszahlung, double AHV, double ALV, double kinderzulage, double nettolohn, boolean isAdminArbeitnehmer) {
        this.id = id;
        this.NBU = NBU;
        this.KTG = KTG;
        this.SonnFeiertagszuschlag = SonnFeiertagszuschlag;
        this.BVG_Pensionsekasse = BVG_Pensionsekasse;
        this.monatslohn = monatslohn;
        this.dreizehnMLAuszahlung = dreizehnMLAuszahlung;
        this.AHV = AHV;
        this.ALV = ALV;
        this.kinderzulage = kinderzulage;
        this.nettolohn = nettolohn;
        this.isAdminArbeitnehmer = isAdminArbeitnehmer;
    }
    
    

    public boolean getIsAdminArbeitnehmer() {
        return isAdminArbeitnehmer;
    }

    public void setIsAdminArbeitnehmer(boolean isAdminArbeitnehmer) {
        this.isAdminArbeitnehmer = isAdminArbeitnehmer;
    }

    public double getNettolohn() {
        return nettolohn;
    }

    public void setNettolohn(double nettolohn) {
        this.nettolohn = nettolohn;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public double getNBU() {
        return NBU;
    }

    public void setNBU(double NBU) {
        this.NBU = NBU;
    }

    public double getKTG() {
        return KTG;
    }

    public void setKTG(double KTG) {
        this.KTG = KTG;
    }

    public double getSonnFeiertagszuschlag() {
        return SonnFeiertagszuschlag;
    }

    public void setSonnFeiertagszuschlag(double SonnFeiertagszuschlag) {
        this.SonnFeiertagszuschlag = SonnFeiertagszuschlag;
    }

    public double getBVG_Pensionsekasse() {
        return BVG_Pensionsekasse;
    }

    public void setBVG_Pensionsekasse(double BVG_Pensionsekasse) {
        this.BVG_Pensionsekasse = BVG_Pensionsekasse;
    }

    public double getMonatslohn() {
        return monatslohn;
    }

    public void setMonatslohn(double monatslohn) {
        this.monatslohn = monatslohn;
    }

    public double getDreizehnMLAuszahlung() {
        return dreizehnMLAuszahlung;
    }

    public void setDreizehnMLAuszahlung(double dreizehnMLAuszahlung) {
        this.dreizehnMLAuszahlung = dreizehnMLAuszahlung;
    }

    public double getAHV() {
        return AHV;
    }

    public void setAHV(double AHV) {
        this.AHV = AHV;
    }

    public double getALV() {
        return ALV;
    }

    public void setALV(double ALV) {
        this.ALV = ALV;
    }

    public double getKinderzulage() {
        return kinderzulage;
    }

    public void setKinderzulage(double kinderzulage) {
        this.kinderzulage = kinderzulage;
    }
}
