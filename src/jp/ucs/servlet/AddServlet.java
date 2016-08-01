package jp.ucs.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ucs.DBException.DBException;
import jp.ucs.bo.EquipmentLogic;
import jp.ucs.model.Equipment;

/**
 * Servlet implementation class AddServlet
 */
@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String equipmentId = request.getParameter("equipmentId");
		String equipmentName = request.getParameter("equipmentName");
		String categoryId = request.getParameter("categoryId");
		Equipment equipment = new Equipment();
	
		EquipmentLogic equipmentLogic = new EquipmentLogic();
		equipment.setEquipmentId(equipmentId);
		equipment.setEquipmentName(equipmentName);
		equipment.setCategoryId(categoryId);		
        System.out.println(equipment.toString());
        System.out.println(equipment.getCategoryId());
		try {
            equipmentLogic.addEquipment(equipment);
//            request.getRequestDispatcher("/WEB-INF/jsp/admin/adminBorrow.jsp").forward(request, response);;
        } catch (DBException e) {            
            e.printStackTrace();
        }
		
	}

}
