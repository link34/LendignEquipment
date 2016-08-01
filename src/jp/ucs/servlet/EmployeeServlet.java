package jp.ucs.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ucs.DBException.DBException;
import jp.ucs.bo.EquipmentLogic;

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
	    String memberId = request.getParameter("memberId");
	    EquipmentLogic equipmentLogic = new EquipmentLogic();
	    System.out.println(memberId);
	    System.out.println(memberId.trim());
	    /**
         * 削除ボタン
         */
        try {
            equipmentLogic.deleteByEmployee(memberId.trim());
            System.out.println(memberId.trim());
            request.getRequestDispatcher("/WEB-INF/jsp/admin/adminEmployeeHistoryList.jsp").forward(request, response);
        } catch (NullPointerException | DBException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
