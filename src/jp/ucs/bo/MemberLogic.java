package jp.ucs.bo;

import java.util.List;

import jp.ucs.DBException.DBException;
import jp.ucs.dao.MemberDao;
import jp.ucs.model.MemberHistory;

public class MemberLogic {
    MemberDao memberDao = new MemberDao();
    
    public List<MemberHistory> getAllMember() throws DBException {
        List<MemberHistory> memberList = memberDao.findAllMember();
        return memberList;         
    }

}
