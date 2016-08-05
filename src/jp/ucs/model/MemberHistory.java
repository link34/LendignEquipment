package jp.ucs.model;

import java.io.Serializable;
import java.util.Date;

public class MemberHistory implements Serializable {
    private int borrowId;
    private String memberId; 
    private String memberName;
    private String depertmentId;
    private String depertmentName;
    private String equipmentId;
    private String equipmentName;
    private String categoryId;
    private String categoryName;
    private Date borrowDate;
    private Date returnDate;
    private String status;
    private String comment;
    
    public MemberHistory() {
    }    
    public String getDepertmentId() {
        return depertmentId;
    }
    public void setDepertmentId(String depertmentId) {
        this.depertmentId = depertmentId;
    }
    public String getDepertmentName() {
        return depertmentName;
    }
    public void setDepertmentName(String depertmentName) {
        this.depertmentName = depertmentName;
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
    public String getMemberName() {
        return memberName;
    }
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
    public String getMemberId() {
        return memberId;
    }
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
    public int getBorrowId() {
        return borrowId;
    }
    public void setBorrowId(int borrowId) {
        this.borrowId = borrowId;
    }
    
    
}
