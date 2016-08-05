package jp.ucs.bo;

import java.text.ParseException;
import java.util.List;

import jp.ucs.DBException.DBException;
import jp.ucs.dao.EquipmentDao;
import jp.ucs.model.Borrow;
import jp.ucs.model.Equipment;

public class EquipmentLogic {
    EquipmentDao equipmentDao = new EquipmentDao();
    /**
     * 全体の機器一覧を見る。機器一覧に未使用を表示。
     * @return
     * @throws DBException
     */
    public List<Equipment> getAllEquipment() throws DBException{
        List<Equipment> equipmentList = equipmentDao.findAllEquipment();
        return equipmentList;        
    }
    /**
     * 機器名で検索
     * @param equipmentName
     * @return
     * @throws DBException
     */
    public List<Equipment> getSearchEquipment(String equipmentName,String name,String memberId) throws DBException{
        List<Equipment> equipmentList = equipmentDao.searchByName(equipmentName,name,memberId);
        return equipmentList;        
    }
    /**
     * 機器ＩＤで探して、機器変更のベージに表示
     * @param equipmentId
     * @return
     * @throws DBException
     */
    public Equipment getSearchEquipmentId(String equipmentId) throws DBException{
       Equipment equipment = equipmentDao.searchById(equipmentId);
        return equipment;        
    }
    /**
     * 自分の貸出した機器一覧を表示
     * @param memberId
     * @return
     * @throws DBException
     */
    public List<Equipment> getAllBorrow(String memberId) throws DBException{
        List<Equipment> equipmentList = equipmentDao.findAllBorrow(memberId);
        return equipmentList;        
    }
    /**
     * 機器の貸出ボタンINSERTとUPDATE文
     * @param borrow
     * @return
     * @throws DBException
     */
    public Borrow execute(Borrow borrow) throws DBException {
        equipmentDao.insertBorrow(borrow);
        return borrow;                
    }
    
//    public Equipment findEquipment(List<Equipment> equipmentList, String equipmentId) {
//
//        // 講座一覧から講座IDと合うものがあればその講座を返却する。
//        for (Equipment equipment : equipmentList) {
//
//            if (equipment.getEquipmentId() == equipmentId) {
//
//                return equipment;
//            }
//        }
//        return null;
//    }
    
    public Equipment updateBorrowEquipment(Equipment equipment) throws DBException, ParseException {
        equipmentDao.updateByStatus(equipment);        
        return equipment;
    }
    public List<Equipment> getSearchByDate(String datepicker , String memberId) throws DBException, ParseException {
        List<Equipment> equipmentList = equipmentDao.searchByDate(datepicker , memberId);
        return equipmentList;   
    }
    public Equipment addEquipment(Equipment equipment) throws DBException {
        equipmentDao.insertEquipment(equipment);
        return equipment;        
    }
    public boolean deleteByEmployee(String memberId) throws DBException {
        equipmentDao.deleteByID(memberId);       
        return true;
    }
    public List<Equipment> getAllCategory() throws DBException {
        List<Equipment> equipmentList = equipmentDao.findAllCategory();
        return equipmentList;
    }
}
