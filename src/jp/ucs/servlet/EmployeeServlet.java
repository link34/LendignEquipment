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
import jp.ucs.bo.MemberLogic;
import jp.ucs.model.MemberHistory;

/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        MemberLogic memberLogic = new MemberLogic();        
        HttpSession session = request.getSession();        
        String equipmentName = request.getParameter("equipmentName");
        String departmentName = request.getParameter("departmentName");
        String datepicker = request.getParameter("date");
        String forward = "/WEB-INF/jsp/admin/adminEmployeeHistoryList.jsp";        
        List<MemberHistory> employeeList;
        
        /**
         * 日付と機器名で検索 貸出の側
         */
        try {
            if (datepicker.isEmpty() && equipmentName.isEmpty() && departmentName.isEmpty()) {
                try {
                    employeeList = memberLogic.getAllMember();
                    session.setAttribute("employeeList", employeeList);
                } catch (NullPointerException | DBException e) {
                    e.printStackTrace();
                }
               
            } else if (!equipmentName.isEmpty() && datepicker.isEmpty() && departmentName.isEmpty()) {
                String equip = "equipment";
                employeeList = memberLogic.getSearchEmployee(equipmentName, equip);
                //                session.removeAttribute("employeeList");
                session.setAttribute("employeeList", employeeList);
                
            } else if (!datepicker.isEmpty() && equipmentName.isEmpty() && departmentName.isEmpty()) {
                String date = "date";
                employeeList = memberLogic.getSearchEmployee(datepicker, date);
                session.removeAttribute("employeeList");
                session.setAttribute("employeeList", employeeList);
               
            } else if (!departmentName.isEmpty() && datepicker.isEmpty() && equipmentName.isEmpty()) {
                String department = "department";
                employeeList = memberLogic.getSearchEmployee(departmentName, department);
                session.removeAttribute("employeeList");
                session.setAttribute("employeeList", employeeList);
            }
        } catch (NullPointerException | DBException e) {
            e.printStackTrace();
        }
        
//        /**
//         * 削除ボタン
//         */
//        try {
//            equipmentLogic.deleteByEmployee(memberId.trim());
//            System.out.println(memberId.trim());
//           
//        } catch (NullPointerException | DBException e) {
//            // TODO 自動生成された catch ブロック
//            e.printStackTrace();
//        }
        
       request.getRequestDispatcher(forward).forward(request, response);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        // TODO Auto-generated method stub
    }

}
