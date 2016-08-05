package jp.ucs.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ucs.DBException.DBException;
import jp.ucs.bo.EquipmentLogic;
import jp.ucs.bo.MemberLogic;
import jp.ucs.model.Equipment;
import jp.ucs.model.Member;

/**
 * Servlet implementation class AddServlet
 */
@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        String employeeId = request.getParameter("employeeId");
        String employeeName = request.getParameter("employeeName");
        String depertmentId = request.getParameter("depertmentId");
        String pass = request.getParameter("pass");
        boolean iskaiinn = Boolean.parseBoolean(request.getParameter("cboAdmin"));

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
        } catch (NullPointerException | DBException e) {
            e.printStackTrace();
        }

        MemberLogic memberLogic = new MemberLogic();

        try {
            Member member = new Member();
            member.setId(employeeId.trim());
            member.setMemberName(employeeName);
            member.setDepertmentId(depertmentId.trim());
            member.setPass(pass);
            member.setIskaiinn(iskaiinn ? false : true);

            memberLogic.addMember(member);
        } catch (NullPointerException | DBException e) {

            e.printStackTrace();
        }

    }

}
