package jp.ucs.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import jp.ucs.DBException.DBException;
import jp.ucs.model.Borrow;
import jp.ucs.model.Equipment;

/**
 * DATABASEの変更、入力、検索、削除する。
 * @author aungkowin
 *
 */
public class EquipmentDao extends DAOBase {

    /**
     * 全体の機器一覧を見る。機器一覧に未使用を表示。
     * @return
     * @throws DBException
     */
    public List<Equipment> findAllEquipment() throws DBException {

        List<Equipment> equipmentList = new ArrayList<Equipment>();

        try {
            super.open();
            String sql = "select equipmemtid,equipmemtname,category.categoryid,category.categoryname,status "
                    + "from Equipment "
                    + "join category "
                    + "on Equipment.categoryid = category.categoryid where status ='未使用' order by equipmemtid asc;";
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            Equipment equipment;
            while (rs.next()) {
                equipment = new Equipment();
                equipment.setEquipmentId(rs.getString(1));
                equipment.setEquipmentName(rs.getString(2));
                equipment.setCategoryId(rs.getString(3));
                equipment.setCategoryName(rs.getString(4));
                equipment.setStatus(rs.getString(5));
                equipmentList.add(equipment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.close(conn);
        }

        return equipmentList;
    }

    /**
     * 自分の貸出した機器一覧を表示
     * @param memberId
     * @return
     * @throws DBException
     */
    public List<Equipment> findAllBorrow(String memberId) throws DBException {

        List<Equipment> equipmentList = new ArrayList<Equipment>();

        try {
            super.open();

            String sql = "select equi.equipmemtid,equi.equipmemtname,category.categoryid,"
                    + "category.categoryname,equi.status,equi.borrowdate,equi.comment,equi.memberid,equi.borrowId "
                    + "from (select borrowEquipment.borrowId,borrowEquipment.memberId,borrowEquipment.equipmemtid,"
                    + "Equipment.equipmemtname,"
                    + "Equipment.status,Equipment.categoryid,borrowdate,comment "
                    + "from Equipment join borrowEquipment "
                    + "on borrowEquipment.equipmemtid = Equipment.equipmemtid) as equi "
                    + "join category on equi.categoryid = category.categoryid where memberid='" + memberId.trim()
                    + "' "
                    + "and equi.status='使用中' order by equi.borrowId asc;";
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            Equipment equipment;
            while (rs.next()) {
                equipment = new Equipment();
                equipment.setEquipmentId(rs.getString(1));
                equipment.setEquipmentName(rs.getString(2));
                equipment.setCategoryId(rs.getString(3));
                equipment.setCategoryName(rs.getString(4));
                equipment.setStatus(rs.getString(5));
                equipment.setBorrowDate(rs.getDate(6));
                equipment.setComment(rs.getString(7));
                equipment.setBorrowId(rs.getInt(9));
                equipmentList.add(equipment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.close(conn);
        }

        return equipmentList;
    }

    /**
     * 機器名で検索
     * @param equipmentName
     * @return
     * @throws DBException
     */
    public List<Equipment> searchByName(String equipmentName, String name, String memberId) throws DBException {
        List<Equipment> equipmentList = new ArrayList<Equipment>();
        String sql = "";
        try {
            super.open();
            if (name.equals("equipment")) {
                sql = "select equipmemtid,equipmemtname,category.categoryid,category.categoryname,status from equipment "
                        + "join category on equipment.categoryid = category.categoryid "
                        + "where equipmemtname LIKE '%" + equipmentName + "%';";

                PreparedStatement pstm = conn.prepareStatement(sql);

                ResultSet rs = pstm.executeQuery();
                Equipment equipment;
                while (rs.next()) {
                    equipment = new Equipment();
                    equipment.setEquipmentId(rs.getString(1));
                    equipment.setEquipmentName(rs.getString(2));
                    equipment.setCategoryId(rs.getString(3));
                    equipment.setCategoryName(rs.getString(4));
                    equipment.setStatus(rs.getString(5));
                    equipmentList.add(equipment);
                }
            } else if (name.equals("borrow")) {
                sql = "select equi.equipmemtid,equi.equipmemtname,equi.categoryid,equi.categoryname,equi.status,borrowdate,comment,"
                        + "borrowid from borrowequipment inner join "
                        + "(select equipmemtid,equipmemtname,category.categoryid,category.categoryname,status from equipment "
                        + " inner join category on equipment.categoryid = category.categoryid) as equi "
                        + "on borrowequipment.equipmemtid = equi.equipmemtid "
                        + "where borrowequipment.memberid = '"
                        + memberId
                        + "' and equipmemtname LIKE '%"
                        + equipmentName + "%';";

                PreparedStatement pstm = conn.prepareStatement(sql);

                ResultSet rs = pstm.executeQuery();
                Equipment equipment;
                while (rs.next()) {
                    equipment = new Equipment();
                    equipment.setEquipmentId(rs.getString(1));
                    equipment.setEquipmentName(rs.getString(2));
                    equipment.setCategoryId(rs.getString(3));
                    equipment.setCategoryName(rs.getString(4));
                    equipment.setStatus(rs.getString(5));
                    equipment.setBorrowDate(rs.getDate(6));
                    equipment.setComment(rs.getString(7));
                    equipment.setBorrowId(rs.getInt(8));
                    equipmentList.add(equipment);
                }
            }else if(name.equals("employee")){
                
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return equipmentList;
    }

    /**
     * 機器ＩＤで探して、機器変更のベージに表示
     * @param equipmentId
     * @return
     * @throws DBException
     */
    public Equipment searchById(String equipmentId) throws DBException {
        Equipment equipment = new Equipment();
        try {
            super.open();
            String sql = "select equi.equipmemtid,equi.equipmemtname,category.categoryid,"
                    + "category.categoryname,equi.status,equi.borrowdate,equi.comment,equi.memberid,equi.borrowId "
                    + "from (select borrowEquipment.borrowId,borrowEquipment.memberId,borrowEquipment.equipmemtid,"
                    + "Equipment.equipmemtname,"
                    + "Equipment.status,Equipment.categoryid,borrowdate,comment "
                    + "from Equipment join borrowEquipment "
                    + "on borrowEquipment.equipmemtid = Equipment.equipmemtid) as equi "
                    + "join category on equi.categoryid = category.categoryid where equipmemtid ='"
                    + equipmentId.trim() + "' order by equi.borrowId asc;";

            PreparedStatement pstm = conn.prepareStatement(sql);            
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                equipment = new Equipment();
                equipment.setEquipmentId(rs.getString(1));
                equipment.setEquipmentName(rs.getString(2));
                equipment.setCategoryId(rs.getString(3));
                equipment.setCategoryName(rs.getString(4));
                equipment.setStatus(rs.getString(5));
                equipment.setBorrowDate(rs.getDate(6));
                equipment.setComment(rs.getString(7));
                equipment.setBorrowId(rs.getInt(9));
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return equipment;
    }

    /**
     * 機器の貸出ボタンINSERTとUPDATE文
     * @param borrow
     * @return
     * @throws DBException
     */
    public boolean insertBorrow(Borrow borrow) throws DBException {
        super.open();
        try {
            Date date = borrow.getBorrowDate();
            java.sql.Date today = new java.sql.Date(date.getTime());
            //INSERT文の準備
            String sql = "INSERT INTO borrowequipment(memberid,equipmemtid,borrowdate) "
                    + "VALUES('" + borrow.getMemberID().trim() + "','" + borrow.getEquipmentID().trim() + "','" + today
                    + "');"
                    + "Update equipment set status = '使用中' where equipmemtid ='" + borrow.getEquipmentID().trim() + "';";

            PreparedStatement pStmt = conn.prepareStatement(sql);
            System.out.println(sql);
            //INSERT文を実行
            int result = pStmt.executeUpdate();
            if (result != 1) {
                return false;
            }
        } catch (SQLException e) {
            throw new DBException("既に貸出されています。");
        } finally {
            super.close(conn);
        }
        return true;
    }

    /**
     * 機器の変更ボタン
     * @param equipment 機器
     * @return
     * @throws DBException
     * @throws ParseException
     */
    public Equipment updateByStatus(Equipment equipment) throws DBException, ParseException {
        try {
            super.open();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = df.format(Calendar.getInstance().getTime());
            Date date = df.parse(dateString);
            java.sql.Date returnDate = new java.sql.Date(date.getTime());
            String sql = "";
            if (equipment.getStatus().equals("返却")) {
                sql = "Update equipment set status='" + equipment.getStatus() + "' "
                        + "where equipmemtid='" + equipment.getEquipmentId().trim() + "';"
                        + "Update borrowEquipment set comment='" + equipment.getComment() + "'"
                        + " ,returndate='" + returnDate + "' where borrowid=" + equipment.getBorrowId() + ";";
            } else {
                sql = "Update borrowEquipment set comment='" + equipment.getComment() + "' "
                        + "where borrowid=" + equipment.getBorrowId() + ";";
            }
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.executeUpdate();
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        } finally {
            super.close(conn);
        }
        return equipment;
    }

    /**
     * 日付で検索
     * @param datepicker
     * @return
     * @throws DBException
     * @throws ParseException
     */
    public List<Equipment> searchByDate(String datepicker, String memberId) throws DBException, ParseException {
        List<Equipment> equipmentList = new ArrayList<Equipment>();
        try {
            super.open();
            String sql = "select equi.equipmemtid,equi.equipmemtname,equi.categoryid,equi.categoryname,equi.status,borrowdate,comment,"
                    + "borrowid from borrowequipment inner join "
                    + "(select equipmemtid,equipmemtname,category.categoryid,category.categoryname,status from equipment "
                    + " inner join category on equipment.categoryid = category.categoryid) as equi "
                    + "on borrowequipment.equipmemtid = equi.equipmemtid "
                    + "where borrowequipment.memberid = '" + memberId + "' and borrowdate ='" + datepicker + "';";

            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            Equipment equipment;
            while (rs.next()) {
                equipment = new Equipment();
                equipment.setEquipmentId(rs.getString(1));
                equipment.setEquipmentName(rs.getString(2));
                equipment.setCategoryId(rs.getString(3));
                equipment.setCategoryName(rs.getString(4));
                equipment.setStatus(rs.getString(5));
                equipment.setBorrowDate(rs.getDate(6));
                equipment.setComment(rs.getString(7));
                equipment.setBorrowId(rs.getInt(8));
                equipmentList.add(equipment);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return equipmentList;
    }

    public boolean insertEquipment(Equipment equipment) throws DBException {
        try {
            super.open();
            String sql = "insert into equipment(equipmemtid,equipmemtName,status,categoryid)  values(?,?,?,?);";

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, equipment.getEquipmentId());
            pstm.setString(2, equipment.getEquipmentName());
            pstm.setString(3, "未使用");
            pstm.setString(4, equipment.getCategoryId().trim());
            //INSERT文を実行
            int result = pstm.executeUpdate();
            System.out.println(sql);
            if (result != 1) {
                return false;
            }
        } catch (SQLException e) {
            throw new DBException("既に登録されています。");
        } finally {
            super.close(conn);
        }
        return true;
    }

    public boolean deleteByID(String memberId) throws DBException {

        try {

            String sql = "delete from member where id = ?";
            System.out.println(sql);
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, memberId.trim());
            int result = pStmt.executeUpdate();
            if (result != 1) {
                throw new DBException("ID" + "(" + memberId.trim() + ")に該当するデータはありません。");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("DB例外が発生しました。");
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new DBException("DB例外が発生しました。");
                }
            }
        }
        return true;
    }

    public List<Equipment> findAllCategory() throws DBException {
        List<Equipment> equipmentList = new ArrayList<Equipment>();
        try {
            super.open();
            String sql = "select * from category;";

            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            Equipment equipment;
            while (rs.next()) {
                equipment = new Equipment();
                
                equipment.setCategoryId(rs.getString(1));
                equipment.setCategoryName(rs.getString(2));
                
                equipmentList.add(equipment);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return equipmentList;    
    }
}
