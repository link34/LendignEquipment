package jp.ucs.bo;

import java.util.List;

import jp.ucs.DBException.DBException;
import jp.ucs.dao.MemberDao;
import jp.ucs.model.Member;
import jp.ucs.model.MemberHistory;

public class MemberLogic {
    MemberDao memberDao = new MemberDao();
    List<MemberHistory> memberList;
    public List<MemberHistory> getAllMember() throws DBException {
        memberList = memberDao.findAllMember();
        return memberList;         
    }

    public List<MemberHistory> getAllDepartemt() throws DBException {
        memberList = memberDao.findAllDepartment();
        return memberList; 
    }

    public Member addMember(Member member) throws DBException {
      memberDao.insertMember(member); 
      return member;
    }

    public List<MemberHistory> getSearchEmployee(String name, String fieldName) throws DBException {
        memberList = memberDao.findByEmployee(name,fieldName);
        return memberList;
    }

    public MemberHistory getMemberwithId(String borrowId) throws DBException {
        MemberHistory memberHistory = memberDao.searchMemberById(borrowId);
        return memberHistory;
    }
}
