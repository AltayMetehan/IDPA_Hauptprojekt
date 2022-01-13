package ch.bbbaden.lohnabrechner.model;

import java.io.Serializable;

/**
 *
 * @author Metehan
 */
public class Arbeitgeber implements Serializable{
    private int id;
    private double NBU;
    private double KTG;
    private double Sonn_Feiertagszuschlag;
    private double BVG_Pensionskasse;
    private double AHV;
    private double ALV;
    private double BU;
    private double FAK;
    private double VerwaltungskostenAHV;
    private double FL;
    private double VerwaltungskostenFL;
    private double kinderzulage;
    private double total;
    private boolean isAdminArbeitgeber;

    public Arbeitgeber(int id, double NBU, double KTG, double Sonn_Feiertagszuschlag, double BVG_Pensionskasse, double AHV, double ALV, double BU, double FAK, double VerwaltungskostenAHV, double FL, double VerwaltungskostenFL, double kinderzulage, double total, boolean isAdminArbeitgeber) {
        this.id = id;
        this.NBU = NBU;
        this.KTG = KTG;
        this.Sonn_Feiertagszuschlag = Sonn_Feiertagszuschlag;
        this.BVG_Pensionskasse = BVG_Pensionskasse;
        this.AHV = AHV;
        this.ALV = ALV;
        this.BU = BU;
        this.FAK = FAK;
        this.VerwaltungskostenAHV = VerwaltungskostenAHV;
        this.FL = FL;
        this.VerwaltungskostenFL = VerwaltungskostenFL;
        this.kinderzulage = kinderzulage;
        this.total = total;
        this.isAdminArbeitgeber = isAdminArbeitgeber;
    }
    
    

    public boolean getIsAdminArbeitgeber() {
        return isAdminArbeitgeber;
    }

    public void setIsAdminArbeitgeber(boolean isAdminArbeitgeber) {
        this.isAdminArbeitgeber = isAdminArbeitgeber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public double getSonn_Feiertagszuschlag() {
        return Sonn_Feiertagszuschlag;
    }

    public void setSonn_Feiertagszuschlag(double Sonn_Feiertagszuschlag) {
        this.Sonn_Feiertagszuschlag = Sonn_Feiertagszuschlag;
    }

    public double getBVG_Pensionskasse() {
        return BVG_Pensionskasse;
    }

    public void setBVG_Pensionskasse(double BVG_Pensionskasse) {
        this.BVG_Pensionskasse = BVG_Pensionskasse;
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

    public double getBU() {
        return BU;
    }

    public void setBU(double BU) {
        this.BU = BU;
    }

    public double getFAK() {
        return FAK;
    }

    public void setFAK(double FAK) {
        this.FAK = FAK;
    }

    public double getVerwaltungskostenAHV() {
        return VerwaltungskostenAHV;
    }

    public void setVerwaltungskostenAHV(double VerwaltungskostenAHV) {
        this.VerwaltungskostenAHV = VerwaltungskostenAHV;
    }

    public double getFL() {
        return FL;
    }

    public void setFL(double FL) {
        this.FL = FL;
    }

    public double getVerwaltungskostenFL() {
        return VerwaltungskostenFL;
    }

    public void setVerwaltungskostenFL(double VerwaltungskostenFL) {
        this.VerwaltungskostenFL = VerwaltungskostenFL;
    }

    public double getKinderzulage() {
        return kinderzulage;
    }

    public void setKinderzulage(double kinderzulage) {
        this.kinderzulage = kinderzulage;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
}
