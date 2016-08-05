package jp.ucs.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.ucs.DBException.DBException;
import jp.ucs.bo.EquipmentLogic;
import jp.ucs.bo.MemberLogic;
import jp.ucs.model.Equipment;
import jp.ucs.model.MemberHistory;

/**
 * Servlet implementation class PageChangeServlet
 */
@WebServlet("/PageChangeServlet")
public class PageChangeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String equipmentId = request.getParameter("equipmentId");    
        String borrowId = request.getParameter("borrowId"); 
        EquipmentLogic equipmentLogic = new EquipmentLogic();
        MemberLogic memberLogic = new MemberLogic();
        Equipment equipment = new Equipment();   
        MemberHistory memberHistory = new MemberHistory();
        List<Equipment> equipmentList = new ArrayList<Equipment>();
        List<MemberHistory> memberList = new ArrayList<MemberHistory>();
        HttpSession session = request.getSession();
        
        if (action.equals("popup")) {
            try {
                equipment = equipmentLogic.getSearchEquipmentId(equipmentId.trim());
                session.setAttribute("equipment", equipment);
                request.getRequestDispatcher("/WEB-INF/jsp/employee/employeeEditBorrow.jsp").forward(request, response);
            } catch (NullPointerException | DBException e) {
                e.printStackTrace();
            }
        } else if (action.equals("add")) {
            try {
                equipmentList = equipmentLogic.getAllEquipment();
                session.setAttribute("equipmentList", equipmentList);
                request.getRequestDispatcher("/WEB-INF/jsp/admin/adminAddEquipment.jsp").forward(request, response);
            } catch (NullPointerException | DBException e) {
                e.printStackTrace();
            }
        } else if (action.equals("borrowChange")) {
            try {
                equipmentList = equipmentLogic.getAllEquipment();
                session.setAttribute("equipmentList", equipmentList);
                request.getRequestDispatcher("/WEB-INF/jsp/admin/adminEditBorrow.jsp").forward(request, response);
            } catch (NullPointerException | DBException e) {
                e.printStackTrace();
            }
        } else if (action.equals("employeeChange")) {
            try {
                memberHistory = memberLogic.getMemberwithId(borrowId);                
                session.setAttribute("member", memberHistory);
                
                equipmentList = equipmentLogic.getAllCategory();
                session.setAttribute("equipmentList", equipmentList);
                
                request.getRequestDispatcher("/WEB-INF/jsp/admin/adminEditEmployee.jsp").forward(request, response);
            } catch (NumberFormatException | NullPointerException | DBException e) {
                e.printStackTrace();
            }
        } else if (action.equals("equipChange")) {
            try {
                equipmentList = equipmentLogic.getAllEquipment();
                session.setAttribute("equipmentList", equipmentList);
                request.getRequestDispatcher("/WEB-INF/jsp/admin/adminEditEquipment.jsp").forward(request, response);
            } catch (NullPointerException | DBException e) {
                e.printStackTrace();    
            }
        } else if (action.equals("addEmployee")) { 
            try {
              //部門名
                memberList = memberLogic.getAllDepartemt();
                session.setAttribute("departmentList", memberList);
              //カテゴリ名
                equipmentList = equipmentLogic.getAllCategory();
                session.setAttribute("equipmentList", equipmentList);
            } catch (DBException e) {
               e.printStackTrace();
            }            
                request.getRequestDispatcher("/WEB-INF/jsp/admin/adminAddEmployee.jsp").forward(request, response);            
        }
    }
}
