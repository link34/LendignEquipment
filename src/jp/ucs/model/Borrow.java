package jp.ucs.model;

import java.io.Serializable;
import java.util.Date;
/**
 * 貸出Ｂｅａｎクラス。
 * @author aungkowin
 *
 */
public class Borrow implements Serializable{
    private String equipmentID;
    private String memberID;
    private Date borrowDate;
    private Date ReturnDate;
    private String comment;
    /**
     * コンストラクター
     */
    public Borrow() {
    }
    /**
     * コンストラクター
     * @param equipmentID
     * @param memberID
     * @param borrowDate
     * @param returnDate
     * @param comment
     */
    public Borrow(String equipmentID, String memberID, Date borrowDate, Date returnDate, String comment) {
        this.equipmentID = equipmentID;
        this.memberID = memberID;
        this.borrowDate = borrowDate;
        ReturnDate = returnDate;
        this.comment = comment;
    }
    public String getEquipmentID() {
        return equipmentID;
    }
    public void setEquipmentID(String equipmentID) {
        this.equipmentID = equipmentID;
    }
    public String getMemberID() {
        return memberID;
    }
    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }
    public Date getBorrowDate() {
        return borrowDate;
    }
    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }
    public Date getReturnDate() {
        return ReturnDate;
    }
    public void setReturnDate(Date returnDate) {
        ReturnDate = returnDate;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ReturnDate == null) ? 0 : ReturnDate.hashCode());
        result = prime * result + ((borrowDate == null) ? 0 : borrowDate.hashCode());
        result = prime * result + ((comment == null) ? 0 : comment.hashCode());
        result = prime * result + ((equipmentID == null) ? 0 : equipmentID.hashCode());
        result = prime * result + ((memberID == null) ? 0 : memberID.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Borrow other = (Borrow) obj;
        if (ReturnDate == null) {
            if (other.ReturnDate != null)
                return false;
        } else if (!ReturnDate.equals(other.ReturnDate))
            return false;
        if (borrowDate == null) {
            if (other.borrowDate != null)
                return false;
        } else if (!borrowDate.equals(other.borrowDate))
            return false;
        if (comment == null) {
            if (other.comment != null)
                return false;
        } else if (!comment.equals(other.comment))
            return false;
        if (equipmentID == null) {
            if (other.equipmentID != null)
                return false;
        } else if (!equipmentID.equals(other.equipmentID))
            return false;
        if (memberID == null) {
            if (other.memberID != null)
                return false;
        } else if (!memberID.equals(other.memberID))
            return false;
        return true;
    }
}
