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
        List<MemberHistory> memberList = new ArrayList<MemberHistory>();
        try{
            super.open();
            String sql = "select * from department as D join (select M.membername,M.departmentid,"
                + "B.equipmemtid,B.memberid,B.equipmemtname,B.status,B.borrowdate,"
                + "B.returndate,B.comment from member as M inner join "
                + "(select b.memberid,b.equipmemtid,e.equipmemtname,e.status,b.borrowdate,b.returndate,"
                + "b.comment from borrowequipment as b inner join equipment as e on b.equipmemtid = e.equipmemtid) "
                + "as B on M.memberid = B.memberid) as E on E.departmentid = D.departmentid;";
        PreparedStatement pstm = conn.prepareStatement(sql);
        System.out.println(sql);
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
            memberHistory.setComment(rs.getString(11));      
            
            memberList.add(memberHistory);
        }       
        System.out.println(sql);
    }catch (SQLException e) {
        e.printStackTrace();        
    } finally {
        super.close(conn);
    }
        return memberList;
    }    
}
