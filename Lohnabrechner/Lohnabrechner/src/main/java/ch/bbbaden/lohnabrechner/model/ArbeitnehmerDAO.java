package ch.bbbaden.lohnabrechner.model;

import ch.bbbaden.lohnabrechner.model.DbAccess;
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
public class ArbeitnehmerDAO {

    public ArbeitnehmerDAO() {
        if (!DbAccess.tableExists("arbeitnehmer")) {
            createInitialArbeitnehmer();
        }
    }
    
    private void createInitialArbeitnehmer(){
        final String sql = "CREATE TABLE IF NOT EXISTS arbeitnehmer ("
                + " id INTEGER PRIMARY KEY,"
                + " nbu DOUBLE NOT NULL,"
                + " ktg DOUBLE NOT NULL,"
                + " Sonn-/Feiertagszuschlag DOUBLE NOT NULL,"
                + " BVG/Pensionskasse DOUBLE NOT NULL,"
                + " monatslohn DOUBLE NOT NULL,"
                + " 13. Monatslohnauszahlung DOUBLE NOT NULL,"
                + " ahv DOUBLE NOT NULL,"
                + " alv DOUBLE NOT NULL,"
                + " kinderzulage DOUBLE NOT NULL,"
                + " nettolohn DOUBLE NOT NULL,"
                + " is_admin_arbeitnehmer INTEGER DEFAULT 0 NOT NULL"
                + ");";
        try (Statement statement = DbAccess.getConnection().createStatement()){
            statement.execute(sql);
        }catch (SQLException e) {
            Logger.getLogger(DbAccess.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public List<Arbeitnehmer> getAll(){
        final String sql = "SELECT id, nbu, ktg, Sonn-/Feiertagszuschlag, BVG/Pensionskasse, monatslohn, 13. Monatslohnauszahlung, ahv, alv, kinderzulage, nettolohn is_admin_arbeitnehmer FROM arbeitnehmer ORDER BY id ASC";
        ArrayList<Arbeitnehmer> allArbeitnehmer = new ArrayList<>();
        
        try (Statement stmt = DbAccess.getConnection().createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                allArbeitnehmer.add(new Arbeitnehmer(rs.getInt("id"), rs.getDouble("nbu"), rs.getDouble("ktg"), rs.getDouble("Sonn-Feiertagszuschlag"), rs.getDouble("BVG/Pensionskasse"), rs.getDouble("monatslohn"), rs.getDouble("13. Monatslohnauszahlung"), rs.getDouble("ahv"), rs.getDouble("alv"), rs.getDouble("kinderzulage"), rs.getDouble("nettolohn"), rs.getBoolean("is_admin_arbeitnehmer")));
            }
        } catch (SQLException e) {
            Logger.getLogger(ArbeitnehmerDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return allArbeitnehmer;
    }
    
    public int insert(Arbeitnehmer arbeitnehmer) {
        final String sql = "INSERT INTO arbeitnehmer (id, nbu, ktg, Sonn-/Feiertagszuschlag, BVG/Pensionskasse, monatslohn, 13. Monatslohnauszahlung, ahv, alv, kinderzulage, nettolohn is_admin_arbeitnehmer) VALUES ('" + arbeitnehmer.getID() + "','" + arbeitnehmer.getNBU() + "','" + arbeitnehmer.getKTG() + "','" + arbeitnehmer.getSonnFeiertagszuschlag() + "','" + arbeitnehmer.getBVG_Pensionsekasse() + "','" + arbeitnehmer.getMonatslohn() + "','" + arbeitnehmer.getDreizehnMLAuszahlung() + "','" + arbeitnehmer.getAHV() + "','" + arbeitnehmer.getALV() + "','" + arbeitnehmer.getKinderzulage() + "','" + arbeitnehmer.getNettolohn() + "','" + (arbeitnehmer.getIsAdminArbeitnehmer() ? "1" : "0") + ")";
        int id = 0;

        try (Statement stmt = DbAccess.getConnection().createStatement()) {
            stmt.execute(sql);
            id = stmt.getGeneratedKeys().getInt(1);
        } catch (SQLException e) {
            Logger.getLogger(ArbeitnehmerDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return id;
    }
    
    public int getHighestId() throws SQLException {
        final String sql = "SELECT max (id) FROM arbeitnehmer";
        int id = 0;
        Statement stmt = DbAccess.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        id = rs.getInt(id);
        return id;
    }
    
    public void edit(Arbeitnehmer arbeitnehmer) {
        final String sql = "UPDATE arbeitnehmer SET "
                + "nbu='"+arbeitnehmer.getNBU()+"',"
                + "ktg='"+arbeitnehmer.getKTG()+"',"
                + "Sonn-Feiertagszuschlag='"+arbeitnehmer.getSonnFeiertagszuschlag()+"','"
                + "BVG/Pensionskasse='"+arbeitnehmer.getBVG_Pensionsekasse()+"','"
                + "monatslohn='"+arbeitnehmer.getMonatslohn()+"','"
                + "13. Monatslohnauszahlung='"+arbeitnehmer.getDreizehnMLAuszahlung()+"','"
                + "ahv='"+arbeitnehmer.getAHV()+"','"
                + "alv='"+arbeitnehmer.getALV()+"','"
                + "kinderzulage='"+arbeitnehmer.getKinderzulage()+"','"
                + "nettolohn='"+arbeitnehmer.getNettolohn()+"','"
                + "is_admin_arbeitnehmer="+(arbeitnehmer.getIsAdminArbeitnehmer() ? "1" : "0")
                + " WHERE id = "+arbeitnehmer.getID();

        try (Statement stmt = DbAccess.getConnection().createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            Logger.getLogger(ArbeitnehmerDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void delete(Arbeitnehmer arbeitnehmer) {
        final String sql = "DELETE FROM arbeitnehmer WHERE id = "+arbeitnehmer.getID();

        try (Statement stmt = DbAccess.getConnection().createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            Logger.getLogger(ArbeitnehmerDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public Arbeitnehmer getById(long id) {
        final String sql = "SELECT id, nbu, ktg, Sonn-/Feiertagszuschlag, BVG/Pensionskasse, monatslohn, 13. Monatslohnauszahlung, ahv, alv, kinderzulage, nettolohn is_admin_arbeitnehmer FROM arbetinehmer WHERE id = "+id;

        Arbeitnehmer arbeitnehmer = null;
        
        try (Statement stmt = DbAccess.getConnection().createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            if(rs.next()) {
                arbeitnehmer = new Arbeitnehmer(rs.getInt("id"), rs.getDouble("nbu"), rs.getDouble("ktg"), rs.getDouble("Sonn-Feiertagszuschlag"), rs.getDouble("BVG/Pensionskasse"), rs.getDouble("monatslohn"), rs.getDouble("13. Monatslohnauszahlung"), rs.getDouble("ahv"), rs.getDouble("alv"), rs.getDouble("kinderzulage"), rs.getDouble("nettolohn"), rs.getBoolean("is_admin_arbeitnehmer"));
            }            
        } catch (SQLException e) {
            Logger.getLogger(ArbeitnehmerDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return arbeitnehmer;
    }
}
