package jp.ucs.bo;

import jp.ucs.DBException.DBException;
import jp.ucs.dao.MemberDao;
import jp.ucs.model.Member;

public class LoginLogic {
    public boolean execute(Member member){
        MemberDao memberDao = new MemberDao();
        try {
            if (memberDao.findLogin(member)) {
                return true;
            }
        } catch (DBException e) {            
            e.printStackTrace();
        }
        
        return false;
        
    }
}
