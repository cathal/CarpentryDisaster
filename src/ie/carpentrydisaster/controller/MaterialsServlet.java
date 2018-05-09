package ie.carpentrydisaster.controller;

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
import model.EmailAddress;
import model.Material;

/**
 * Servlet implementation class MaterialsServlet
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
		case "updateMaterials":
			updateMaterials(request, response);
			break;
		case "insertMaterials":
			insertMaterial(request, response);
			break;
		case "showInsertMaterialsForm":
			showInsertMaterialsForm(request, response);
			
			break;	
		case "delete":
			deleteMaterials(request, response);
			break;
		case "showUpdateForm":
			showUpdateForm(request, response);
			break;
		default: // viewAll
			getAllMaterials(request, response);
			break;
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void showInsertMaterialsForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int customerId = Integer.parseInt(request.getParameter("customerId"));
		request.setAttribute("customerId", customerId);
		
		request.getRequestDispatcher("WEB-INF/view/insertMaterials.jsp").forward(request, response);
		
	}
	private void updateMaterials(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int materialId = Integer.parseInt(request.getParameter("materialId"));
		
		int customerId = Integer.parseInt(request.getParameter("customerId"));
		String item= request.getParameter("item");
		String description = request.getParameter("description");
		BigDecimal unitExcl = new BigDecimal(request.getParameter("unitExcl"));
		BigDecimal totalExcl = new BigDecimal(request.getParameter("totalExcl"));
		BigDecimal totalIncl = new BigDecimal(request.getParameter("totalIncl"));
		
		Material materialToUpdate = new Material(materialId, description, item, unitExcl, totalExcl, totalIncl, customerId);
		
		materialDAO.updateMaterial(materialToUpdate);
		
		request.setAttribute("listOfMaterials", materialDAO.getAllMaterials());
		request.setAttribute("customerId", customerId);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("\\WEB-INF\\view\\viewAllMaterials.jsp");
		dispatcher.forward(request, response);
		
		//response.sendRedirect("MaterialsServlet?action=viewAll");
	}
	protected void insertMaterial(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String item = request.getParameter("item");
		String description = request.getParameter("description");
		BigDecimal unitExcl = new BigDecimal(request.getParameter("unitExcl"));
		BigDecimal totalExcl = new BigDecimal(request.getParameter("totalExcl"));
		BigDecimal totalIncl = new BigDecimal(request.getParameter("totalIncl"));
		int customerId = Integer.parseInt(request.getParameter("customerId"));
		//Create a Material object
		Material insertMaterial = new Material(0, description, item, unitExcl, totalExcl, totalIncl, customerId);
		materialDAO.insertMaterials(insertMaterial);
		List<Material> listOfMaterials = materialDAO.getAllMaterials();
		/*Pass the listOfCustomers to the JSP as an attribute*/
		request.setAttribute("listOfMaterials", listOfMaterials);
		request.setAttribute("customerId", customerId);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("\\WEB-INF\\view\\viewAllMaterials.jsp");
		dispatcher.forward(request, response);
		//response.sendRedirect("MaterialsServlet?action=viewAll");
		}
	
	private void showUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Get the customerID, which was sent as a parameter from the
		 * update link in the viewCustomers.jsp
		 */
		int materialId = Integer.parseInt(request.getParameter("materialId"));
		int customerId = Integer.parseInt(request.getParameter("customerId"));
		//System.out.println("customerId "+customerId);
		/* the update form needs a Customer object so that it can display
		 * all the Customers details
		 */
		Material material = materialDAO.getMaterialbyId(materialId);
		
		//List<EmailAddress> emails;
		//for loop??
		//EmailAddress email= customerDao.getEmailbyId(customerId);
		/* Pass that customer onto the JSP using request.setAttribute() */
		request.setAttribute("material", material);
		request.setAttribute("customerId", customerId);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("\\WEB-INF\\view\\updateMaterials.jsp");
		dispatcher.forward(request, response);
		
	}
	
	private void deleteMaterials(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get the customerId for deletion
		int materialId = Integer.parseInt(request.getParameter("materialId"));
		int customerId = Integer.parseInt(request.getParameter("customerId"));
		
		// Send that customerId to the DAO to delete the customer
		materialDAO.deleteMaterial(materialId);
		// request done! Book deleted, now show all materials
		//response.sendRedirect("MaterialsServlet?action=viewAll");
		List<Material> listOfMaterials = materialDAO.getAllMaterials();
		request.setAttribute("customerId", customerId);
		request.setAttribute("listOfMaterials", listOfMaterials);
		/* Open the JSP page*/
		RequestDispatcher dispatcher = request.getRequestDispatcher("\\WEB-INF\\view\\viewAllMaterials.jsp");
		dispatcher.forward(request, response);
	}
	
	private void getAllMaterials(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Gets the list of Customers from the DAO (remember the DAO could
		 * get the Customers from a database/ list/ XML file.
		 */
		int customerId = Integer.parseInt(request.getParameter("customerId"));
		
		List<Material> listOfMaterials = materialDAO.getAllMaterials();
		System.out.println("-------------"+listOfMaterials);
		/*Pass the listOfCustomers to the JSP as an attribute*/
		request.setAttribute("listOfMaterials", listOfMaterials);
		request.setAttribute("customerId", customerId);
		/* Open the JSP page*/
		RequestDispatcher dispatcher = request.getRequestDispatcher("\\WEB-INF\\view\\viewAllMaterials.jsp");
		dispatcher.forward(request, response);
	}

	
}