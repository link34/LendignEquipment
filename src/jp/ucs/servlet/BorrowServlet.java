package jp.ucs.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.ucs.DBException.DBException;
import jp.ucs.bo.EquipmentLogic;
import jp.ucs.model.Borrow;
import jp.ucs.model.Equipment;
import jp.ucs.model.Member;

/**
 * Servlet implementation class BorrowServlet
 */
@WebServlet("/BorrowServlet")
public class BorrowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {      
           
        EquipmentLogic equipmentLogic = new EquipmentLogic();
        Equipment equipment = new Equipment();
        HttpSession session = request.getSession();  
        Member member = (Member) session.getAttribute("loginUser");  
        String equipmentName = request.getParameter("equipmentName");
        String datepicker = request.getParameter("date");        
        List<Equipment> equipmentList;
        
        /**
         * 日付と機器名で検索 貸出の側
         */
        try {            
            if(datepicker.isEmpty() && equipmentName.isEmpty()) {
                equipmentList = equipmentLogic.getAllBorrow(member.getId());
                session.setAttribute("borrowList", equipmentList);
                request.getRequestDispatcher("/WEB-INF/jsp/employee/employeeBorrowList.jsp").forward(request, response);
            }else if(!equipmentName.isEmpty() && datepicker.isEmpty()) {                              
                String borrow = "borrow";
                equipmentList = equipmentLogic.getSearchEquipment(equipmentName,borrow,member.getId());
                session.removeAttribute("borrowList");
                session.setAttribute("borrowList", equipmentList);
                request.getRequestDispatcher("/WEB-INF/jsp/employee/employeeBorrowList.jsp").forward(request, response);
            }else if(!datepicker.isEmpty() || equipmentName.isEmpty()) {                    
                equipmentList = equipmentLogic.getSearchByDate(datepicker , member.getId());
                session.removeAttribute("borrowList");
                session.setAttribute("borrowList", equipmentList);
                request.getRequestDispatcher("/WEB-INF/jsp/employee/employeeBorrowList.jsp").forward(request, response);
            }
        } catch (NullPointerException | DBException | ParseException  e) {
            e.printStackTrace();
        }
               
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String equipmentId = request.getParameter("equipmentId");
        String borrowId = request.getParameter("borrowId");
        String changeId = request.getParameter("changeId");        
        String status = request.getParameter("status");
        String textarea = request.getParameter("textarea");        
        
        HttpSession session = request.getSession();
        EquipmentLogic equipmentLogic = new EquipmentLogic();
        List<Equipment> equipmentList;
        String employeeEquipList = "/WEB-INF/jsp/employee/employeeBorrowList.jsp";
        String adminEquipList = "/WEB-INF/jsp/admin/adminBorrow.jsp";
        /**
         * 貸出ボタン
         */
        if (equipmentId != null) {
            Borrow borrow = new Borrow();
            Date date = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String today = df.format(date);
            Member member = (Member) session.getAttribute("loginUser");
            borrow.setMemberID(member.getId());
            borrow.setEquipmentID(equipmentId);
            try {
                borrow.setBorrowDate(df.parse(today));
                equipmentLogic.execute(borrow);
                if (member.iskaiinn() == false) {
                    request.getRequestDispatcher(employeeEquipList).forward(request, response);
                }else {
                    request.getRequestDispatcher(adminEquipList).forward(request, response);
                }
                
            } catch (DBException | ParseException e) {
                e.printStackTrace();
            }            
        }
        
        /**
         * 変更ボタン
         */
        if (borrowId != null && changeId != null ) {
            try {
                Equipment equipment = new Equipment();            
                equipment.setBorrowId(Integer.parseInt(borrowId));
                equipment.setEquipmentId(changeId);
                equipment.setStatus(status);
                equipment.setComment(textarea);                    
                equipmentLogic.updateBorrowEquipment(equipment);
            } catch (NullPointerException | DBException | NumberFormatException | ParseException e) {            
                e.printStackTrace();
            }
        }
        
    }

}
