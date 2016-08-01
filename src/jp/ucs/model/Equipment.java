package jp.ucs.model;

import java.io.Serializable;
import java.util.Date;
/**
 * 機器のBean クラス
 * @author aungkowin
 *
 */

public class Equipment implements Serializable {
    private int borrowId;
    private String equipmentId;
    private String equipmentName;
    private String categoryId;
    private String categoryName;
    private Date borrowDate;
    private Date returnDate;
    private String status;
    private String comment;
    /**
     * コンストラクター
     */
    public Equipment() {        
    }
    
    /**
     * コンストラクター
     * @param borrowId
     * @param equipmentId
     * @param equipmentName
     * @param categoryName
     * @param borrowDate
     * @param returnDate
     * @param status
     * @param comment
     */
    public Equipment(int borrowId,String equipmentId, String equipmentName,String categoryName, Date borrowDate,
            Date returnDate, String status, String comment) {
        this.setBorrowId(borrowId);
        this.equipmentId = equipmentId;
        this.equipmentName = equipmentName;
        this.categoryName = categoryName;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.status = status;
        this.comment = comment;
    }
    public String getEquipmentId() {
        return equipmentId;
    }
    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }
    public String getEquipmentName() {
        return equipmentName;
    }
    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }
    public String getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public Date getBorrowDate() {
        return borrowDate;
    }
    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }
    public Date getReturnDate() {
        return returnDate;
    }
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public int getBorrowId() {
        return borrowId;
    }
    public void setBorrowId(int borrowId) {
        this.borrowId = borrowId;
    }
}
