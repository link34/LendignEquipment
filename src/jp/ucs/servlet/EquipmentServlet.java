package jp.ucs.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.ucs.DBException.DBException;
import jp.ucs.bo.EquipmentLogic;
import jp.ucs.model.Equipment;
import jp.ucs.model.Member;

@WebServlet("/EquipmentServlet")
public class EquipmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String employeeEquipList = "/WEB-INF/jsp/employee/employeeEquipmentList.jsp" ; 
        String adminequipList = "/WEB-INF/jsp/admin/adminBorrow.jsp" ; 
        String forward ="";
        String equipmentName = request.getParameter("equipementName");
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("loginUser");  
        EquipmentLogic equipmentLogic = new EquipmentLogic();
        List<Equipment> equipmentList;
        
        /**
         * 機器一覧から検索機器側。
         */
        try {            
            if (equipmentName.isEmpty()) {
                equipmentList = equipmentLogic.getAllEquipment();
                session.setAttribute("equipmentList", equipmentList);
                if (!member.iskaiinn()) {
                    forward = employeeEquipList;
                }else {
                    forward = adminequipList;
                }
                
            } else {                              
                String equipment = "equipment";
                equipmentList = equipmentLogic.getSearchEquipment(equipmentName,equipment,member.getId());
                session.setAttribute("equipmentList", equipmentList);
                if (!member.iskaiinn()) {
                    forward = employeeEquipList;
                }else {
                    forward = adminequipList;
                }
            }            
        } catch (NullPointerException | DBException  e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher(forward).forward(request, response);

    }
}
