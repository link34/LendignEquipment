package jp.ucs.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.ucs.DBException.DBException;
import jp.ucs.model.Member;
import jp.ucs.model.MemberHistory;

public class MemberDao extends DAOBase {
    List<MemberHistory> memberList = new ArrayList<MemberHistory>();
    
    public boolean findLogin(Member member) throws DBException {
        try {
            super.open();
            String sql = "select iskaiinn from member where memberid='" + member.getId() + "' "
                    + "and pass='" + member.getPass() + "';";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                member.setIskaiinn(rs.getBoolean(1));
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            super.close(conn);
        }

        return false;
    }

    public List<MemberHistory> findAllMember() throws DBException {
        
        try {
            super.open();
            String sql = "select * from department as D join (select M.membername,M.departmentid,"
                    + "B.equipmemtid,B.memberid,B.equipmemtname,B.status,B.borrowdate, "
                    + "B.returndate,B.borrowid,B.comment from member as M inner join "
                    + "(select b.borrowid,b.memberid,b.equipmemtid,e.equipmemtname,e.status,b.borrowdate,b.returndate,"
                    + "b.comment from borrowequipment as b inner join equipment as e on b.equipmemtid = e.equipmemtid) "
                    + "as B on M.memberid = B.memberid) as E on E.departmentid = D.departmentid;";
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            MemberHistory memberHistory;
            while (rs.next()) {
                memberHistory = new MemberHistory();
                memberHistory.setDepertmentId(rs.getString(1));
                memberHistory.setDepertmentName(rs.getString(2));
                memberHistory.setMemberName(rs.getString(3));
                memberHistory.setEquipmentId(rs.getString(5));
                memberHistory.setMemberId(rs.getString(6));
                memberHistory.setEquipmentName(rs.getString(7));
                memberHistory.setStatus(rs.getString(8));
                memberHistory.setBorrowDate(rs.getDate(9));
                memberHistory.setReturnDate(rs.getDate(10));
                memberHistory.setBorrowId(rs.getInt(11));
                memberHistory.setComment(rs.getString(12));

                memberList.add(memberHistory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.close(conn);
        }
        return memberList;
    }

    public List<MemberHistory> findAllDepartment() throws DBException {
        

        try {
            super.open();
            String sql = "select * from department;";
            PreparedStatement pstm = conn.prepareStatement(sql);

            MemberHistory memberHistory;
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                memberHistory = new MemberHistory();
                memberHistory.setDepertmentId(rs.getString(1));
                memberHistory.setDepertmentName(rs.getString(2));
                memberList.add(memberHistory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.close(conn);
        }
        return memberList;
    }

    public boolean insertMember(Member member) throws DBException {
        
        try {
            super.open();
            String sql = "insert into member values(?,?,?,?,?);";
            PreparedStatement pstm = conn.prepareStatement(sql);
            
            pstm.setString(1, member.getId());
            pstm.setString(2, member.getPass());
            pstm.setString(3, member.getMemberName());
            pstm.setBoolean(4, member.iskaiinn());
            pstm.setString(5, member.getDepertmentId());
          //INSERT文を実行
            int result = pstm.executeUpdate();            
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

    public List<MemberHistory> findByEmployee(String name, String fieldName) throws DBException {
        
        String sql = "select * from department as D join (select M.membername,M.departmentid,"
                + "B.equipmemtid,B.memberid,B.equipmemtname,B.status,B.borrowdate, "
                + "B.returndate,B.borrowid,B.comment from member as M inner join "
                + "(select b.borrowid,b.memberid,b.equipmemtid,e.equipmemtname,e.status,b.borrowdate,b.returndate,"
                + "b.comment from borrowequipment as b inner join equipment as e on b.equipmemtid = e.equipmemtid) "
                + "as B on M.memberid = B.memberid) as E on E.departmentid = D.departmentid ";
        
        try {
            super.open();
            if (fieldName.equals("equipment")) {
                sql += "where equipmemtname LIKE '%" + name + "%';";
            }else if(fieldName.equals("date")){
                sql += "where borrowdate='"+ name +"';";
            }else if(fieldName.equals("department")){
                sql += "where departmentname LIKE '%" + name + "%';";
            }else if(fieldName.equals("member")){
                sql += "where borrowid = '"+ name.trim() +"';";
            }
            
            PreparedStatement pstm = conn.prepareStatement(sql);            
            ResultSet rs = pstm.executeQuery();
            MemberHistory memberHistory;
            while (rs.next()) {
                memberHistory = new MemberHistory();
                memberHistory.setDepertmentId(rs.getString(1));
                memberHistory.setDepertmentName(rs.getString(2));
                memberHistory.setMemberName(rs.getString(3));
                memberHistory.setEquipmentId(rs.getString(5));
                memberHistory.setMemberId(rs.getString(6));
                memberHistory.setEquipmentName(rs.getString(7));
                memberHistory.setStatus(rs.getString(8));
                memberHistory.setBorrowDate(rs.getDate(9));
                memberHistory.setReturnDate(rs.getDate(10));
                memberHistory.setBorrowId(rs.getInt(11));
                memberHistory.setComment(rs.getString(12));

                memberList.add(memberHistory);
            }     
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.close(conn);
        }
        return memberList;
    }

    public MemberHistory searchMemberById(String borrowId) throws DBException {
        MemberHistory memberHistory = null;
        try {
            super.open();
            String sql = "select * from department as D join (select M.membername,M.departmentid,"
                    + "B.equipmemtid,B.memberid,B.equipmemtname,B.status,B.borrowdate, "
                    + "B.returndate,B.borrowid,B.comment from member as M inner join "
                    + "(select b.borrowid,b.memberid,b.equipmemtid,e.equipmemtname,e.status,b.borrowdate,b.returndate,"
                    + "b.comment from borrowequipment as b inner join equipment as e on b.equipmemtid = e.equipmemtid) "
                    + "as B on M.memberid = B.memberid) as E on E.departmentid = D.departmentid "
                    + "where borrowid = '"+ borrowId.trim() +"';";                           
            
            PreparedStatement pstm = conn.prepareStatement(sql);            
            ResultSet rs = pstm.executeQuery();
            
            while (rs.next()) {
                memberHistory = new MemberHistory();
                memberHistory.setDepertmentId(rs.getString(1));
                memberHistory.setDepertmentName(rs.getString(2));
                memberHistory.setMemberName(rs.getString(3));
                memberHistory.setEquipmentId(rs.getString(5));
                memberHistory.setMemberId(rs.getString(6));
                memberHistory.setEquipmentName(rs.getString(7));
                memberHistory.setStatus(rs.getString(8));
                memberHistory.setBorrowDate(rs.getDate(9));
                memberHistory.setReturnDate(rs.getDate(10));
                memberHistory.setBorrowId(rs.getInt(11));
                memberHistory.setComment(rs.getString(12));

                memberList.add(memberHistory);
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.close(conn);
        }
        return memberHistory;
    }
}
