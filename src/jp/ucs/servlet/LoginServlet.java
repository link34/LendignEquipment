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
import jp.ucs.bo.LoginLogic;
import jp.ucs.bo.MemberLogic;
import jp.ucs.model.Equipment;
import jp.ucs.model.Member;
import jp.ucs.model.MemberHistory;



/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;   

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    String memberId = request.getParameter("memberId");
		String pass = request.getParameter("pass");
		
		LoginLogic loginLogic = new LoginLogic();
		Member member = new Member(memberId,pass);
		boolean result = loginLogic.execute(member);
		if (result) {
		    HttpSession session = request.getSession();
            session.setAttribute("loginUser", member);
            if (!member.iskaiinn()) {                
                request.getRequestDispatcher("/WEB-INF/jsp/employee/employeemenu.jsp").forward(request, response);
            }else{
                request.getRequestDispatcher("/WEB-INF/jsp/admin/adminmenu.jsp").forward(request, response);
            }
        }else {
            request.setAttribute("Msg", "入力情報が間違っています。");
            request.getRequestDispatcher("/").forward(request, response);
        }
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String employeeEquipList = "/WEB-INF/jsp/employee/employeeEquipmentList.jsp" ;	    
	    String employeeBorrowList = "/WEB-INF/jsp/employee/employeeBorrowList.jsp" ; 
        String adminequipList = "/WEB-INF/jsp/admin/adminBorrow.jsp" ;
        String adminborrowList = "/WEB-INF/jsp/admin/adminBorrowList.jsp" ;
        String adminEmployeeHistory = "/WEB-INF/jsp/admin/adminEmployeeHistoryList.jsp";
	    String action = request.getParameter("action");	   
	    HttpSession session = request.getSession();
	    Member member = (Member) session.getAttribute("loginUser");
	    EquipmentLogic equipmentLogic = new EquipmentLogic();
	    MemberLogic memberLogic = new MemberLogic();
	    List<Equipment> equipmentList;	    
	    
	    
	    if (action.equals("equipmentList")) {
	        try {
                equipmentList = equipmentLogic.getAllEquipment();
                session.setAttribute("equipmentList", equipmentList);
                if (!member.iskaiinn()) {
                    request.getRequestDispatcher(employeeEquipList).forward(request, response);
                }else {
                    request.getRequestDispatcher(adminequipList).forward(request, response);
                }
            } catch (DBException e) {
                e.printStackTrace();
            }
	        
        }else if (action.equals("borrowList")) {
            try {                
                String memberId = member.getId();
                equipmentList = equipmentLogic.getAllBorrow(memberId);
                session.setAttribute("borrowList", equipmentList);
                if (!member.iskaiinn()) {
                    request.getRequestDispatcher(employeeBorrowList).forward(request, response);
                }else {
                    request.getRequestDispatcher(adminborrowList).forward(request, response);
                }
            } catch (DBException e) {
                e.printStackTrace();
            }                
        }else if (action.equals("adminBorrow")) {
            try {
                equipmentList = equipmentLogic.getAllEquipment();
                session.setAttribute("equipmentList", equipmentList);
            } catch (DBException e) {
                e.printStackTrace();
            }            
            request.getRequestDispatcher(adminequipList).forward(request, response);
        }else if (action.equals("employeeHistory")) {
            try {
                List<MemberHistory> employeeList = memberLogic.getAllMember();                
                session.setAttribute("employeeList", employeeList);
            } catch (DBException e) {               
                e.printStackTrace();
            }
            request.getRequestDispatcher(adminEmployeeHistory).forward(request, response);
        }else if (action.equals("logout")){
            session.removeAttribute("loginUser");
            session.invalidate();     
            request.getRequestDispatcher("/").forward(request, response);
        }
	}

}
