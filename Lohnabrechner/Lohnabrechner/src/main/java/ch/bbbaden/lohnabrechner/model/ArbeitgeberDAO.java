package ch.bbbaden.lohnabrechner.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * von der SecureApp inspiriert im Modul 183
 * @author Metehan
 */
public class ArbeitgeberDAO {

    public ArbeitgeberDAO() {
        if (!DbAccess.tableExists("arbeitgeber")) {
            createInitialArbeitnehmer();
        }
    }
    
    private void createInitialArbeitnehmer(){
        final String sql = "CREATE TABLE IF NOT EXISTS arbeitgeber ("
                + " id INTEGER PRIMARY KEY,"
                + " nbu DOUBLE NOT NULL,"
                + " ktg DOUBLE NOT NULL,"
                + " Sonn-/Feiertagszuschlag DOUBLE NOT NULL,"
                + " BVG/Pensionskasse DOUBLE NOT NULL,"
                + " ahv DOUBLE NOT NULL,"
                + " alv DOUBLE NOT NULL,"
                + " bu DOUBLE NOT NULL,"
                + " fak DOUBLE NOT NULL,"
                + " verwaltungskosten_ahv DOUBLE NOT NULL,"
                + " fl DOUBLE NOT NULL,"
                + " verwaltungskosten_fl DOUBLE NOT NULL,"
                + " kinderzulage DOUBLE NOT NULL,"
                + " total DOUBLE NOT NULL,"
                + " is_admin_arbeitgaber INTEGER DEFAULT 0 NOT NULL"
                + ");";
        try (Statement statement = DbAccess.getConnection().createStatement()){
            statement.execute(sql);
        }catch (SQLException e) {
            Logger.getLogger(DbAccess.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public List<Arbeitgeber> getAll(){
        final String sql = "SELECT id, nbu, ktg, Sonn-/Feiertagszuschlag, BVG/Pensionskasse, ahv, alv, bu, fak, verwaltungskosten_ahv, fl, verwaltungskosten_fl, kinderzulage, total, is_admin_arbeitgeber FROM arbeitgeber ORDER BY id ASC";
        ArrayList<Arbeitgeber> allArbeitgeber = new ArrayList<>();
        
        try (Statement stmt = DbAccess.getConnection().createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                allArbeitgeber.add(new Arbeitgeber(rs.getInt("id"), rs.getDouble("nbu"), rs.getDouble("ktg"), rs.getDouble("Sonn-Feiertagszuschlag"), rs.getDouble("BVG/Pensionskasse"), rs.getDouble("ahv"), rs.getDouble("alv"), rs.getDouble("bu"), rs.getDouble("fak"), rs.getDouble("verwaltungskosten_ahv"), rs.getDouble("fl"), rs.getDouble("verwaltungskosten_fl"), rs.getDouble("kinderzulage"), rs.getDouble("total"), rs.getBoolean("is_admin_arbeitgeber")));
            }
        } catch (SQLException e) {
            Logger.getLogger(ArbeitgeberDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return allArbeitgeber;
    }
    
    public int insert(Arbeitgeber arbeitgeber) {
        final String sql = "INSERT INTO arbeitgeber (id, nbu, ktg, Sonn-/Feiertagszuschlag, BVG/Pensionskasse, ahv, alv, bu, fak, verwaltungskosten_ahv, fl, verwaltungskosten_fl, kinderzulage, total, is_admin_arbeitgeber) VALUES ('" + arbeitgeber.getId() + "','" + arbeitgeber.getNBU() + "','" + arbeitgeber.getKTG() + "','" + arbeitgeber.getSonn_Feiertagszuschlag() + "','" + arbeitgeber.getBVG_Pensionskasse() + "','" + arbeitgeber.getAHV() + "','" + arbeitgeber.getALV() + "','" + arbeitgeber.getBU() + "','" + arbeitgeber.getFAK() + "','" + arbeitgeber.getVerwaltungskostenAHV() + "','" + arbeitgeber.getFL() + "','" + arbeitgeber.getVerwaltungskostenFL() + "','" + arbeitgeber.getKinderzulage() + "','" + arbeitgeber.getTotal() + "','" + (arbeitgeber.getIsAdminArbeitgeber() ? "1" : "0") + ")";
        int id = 0;

        try (Statement stmt = DbAccess.getConnection().createStatement()) {
            stmt.execute(sql);
            id = stmt.getGeneratedKeys().getInt(1);
        } catch (SQLException e) {
            Logger.getLogger(ArbeitgeberDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return id;
    }
    
    public void edit(Arbeitgeber arbeitgeber) {
        final String sql = "UPDATE arbeitgeber SET "
                + "nbu='"+arbeitgeber.getNBU()+"',"
                + "ktg='"+arbeitgeber.getKTG()+"',"
                + "Sonn-Feiertagszuschlag='"+arbeitgeber.getSonn_Feiertagszuschlag()+"','"
                + "BVG/Pensionskasse='"+arbeitgeber.getBVG_Pensionskasse()+"','"
                + "ahv='"+arbeitgeber.getAHV()+"','"
                + "alv='"+arbeitgeber.getALV()+"','"
                + "bu='"+arbeitgeber.getBU()+"','"
                + "fak='"+arbeitgeber.getFAK()+"','"
                + "verwaltungskosten_ahv='"+arbeitgeber.getVerwaltungskostenAHV()+"','"
                + "fl='"+arbeitgeber.getFL()+"','"
                + "verwaltungskosten_fl='"+arbeitgeber.getVerwaltungskostenFL()+"','"
                + "kinderzulage='"+arbeitgeber.getKinderzulage()+"','"
                + "total='"+arbeitgeber.getTotal()+"','"
                + "is_admin_arbeitgeber="+(arbeitgeber.getIsAdminArbeitgeber() ? "1" : "0")
                + " WHERE id = "+arbeitgeber.getId();

        try (Statement stmt = DbAccess.getConnection().createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            Logger.getLogger(ArbeitgeberDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void delete(Arbeitgeber arbeitgeber) {
        final String sql = "DELETE FROM arbeitnehmer WHERE id = "+arbeitgeber.getId();

        try (Statement stmt = DbAccess.getConnection().createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            Logger.getLogger(ArbeitgeberDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public Arbeitgeber getById(long id) {
        final String sql = "SELECT id, nbu, ktg, Sonn-/Feiertagszuschlag, BVG/Pensionskasse, ahv, alv, bu, fak, verwaltungskosten_ahv, fl, verwaltungskosten_fl, kinderzulage, total, is_admin_arbeitgeber FROM arbeitgeber WHERE id = "+id;

        Arbeitgeber arbeitgeber = null;
        
        try (Statement stmt = DbAccess.getConnection().createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            if(rs.next()) {
                arbeitgeber = new Arbeitgeber(rs.getInt("id"), rs.getDouble("nbu"), rs.getDouble("ktg"), rs.getDouble("Sonn-Feiertagszuschlag"), rs.getDouble("BVG/Pensionskasse"), rs.getDouble("ahv"), rs.getDouble("alv"), rs.getDouble("bu"), rs.getDouble("fak"), rs.getDouble("verwaltungskosten_ahv"), rs.getDouble("fl"), rs.getDouble("verwaltungskosten_fl"), rs.getDouble("kinderzulage"), rs.getDouble("total"), rs.getBoolean("is_admin_arbeitgeber"));
            }            
        } catch (SQLException e) {
            Logger.getLogger(ArbeitgeberDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return arbeitgeber;
    }
}
