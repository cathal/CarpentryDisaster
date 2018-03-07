package controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Customer;
import model.Material;


/**

 */
@WebServlet("/MaterialsServlet")
public class MaterialsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private MaterialDAO materialDAO;
	
    public MaterialsServlet() {
    	materialDAO = new MaterialDAO();
    }
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet in CarpentryDisaster");
		
		String action = request.getParameter("action");
		if (action == null) {
			action = "viewAll";
		}
		
		
		
		switch (action) {
		case "insertMaterial":
			insertMaterial(request, response);
			break;
		case "showInsertMaterialsForm":
			request.getRequestDispatcher("WEB-INF/view/insertMaterials.jsp").forward(request, response);
			break;	
		default: // viewAll
			getAllMaterials(request, response);
			break;
		}
	}
	
	protected void insertMaterial(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String item = request.getParameter("item");
		String description = request.getParameter("description");
		BigDecimal unitExcl = new BigDecimal(request.getParameter("excl"));
		BigDecimal totalExcl = new BigDecimal(request.getParameter("totalExcl"));
		BigDecimal totalIncl = new BigDecimal(request.getParameter("totalIncl"));
	}
	
	
	private void showInsertMaterialsForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		//int id = request.g("id");
		String description = request.getParameter("description");
		String item = request.getParameter("item");
		BigDecimal unitExcl = new BigDecimal(request.getParameter("excl"));
		BigDecimal totalExcl = new BigDecimal(request.getParameter("totalExcl"));
		BigDecimal totalIncl = new BigDecimal(request.getParameter("totalIncl"));
		
		
		Material materialToUpdate = new Material(0, description, item, unitExcl, totalExcl, totalIncl);
		
		materialDAO.updateMaterial(materialToUpdate);
				
		response.sendRedirect("MaterialsServlet?action=viewAll");
		
	}
	
	
	
	
	private void getAllMaterials(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Gets the list of Customers from the DAO (remember the DAO could
		 * get the Customers from a database/ list/ XML file.
		 */
		List<Material> listOfMaterials = materialDAO.getAllMaterials();
		/*Pass the listOfCustomers to the JSP as an attribute*/
		request.setAttribute("listOfMaterials", listOfMaterials);
		/* Open the JSP page*/
		RequestDispatcher dispatcher = request.getRequestDispatcher("\\WEB-INF\\view\\viewAllMaterials.jsp");
		dispatcher.forward(request, response);
	}

	
}