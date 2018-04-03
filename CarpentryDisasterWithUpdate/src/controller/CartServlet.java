package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cart;
import model.Customer;
import model.Material;

/**
 * new change
 * Servlet implementation class Cart
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
private CartDAO cartDao;
	
    public CartServlet() {
    	cartDao = new CartDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action == null) {
			action = "viewAll";
		}
		
		switch (action) {
		
		case "addCartMaterial":
			addCartMaterial(request, response);
			break;
		case "deleteCartMaterial":
			deleteCartMaterial(request, response);
			break;
		
		default: // viewAll
			getAllCartMaterials(request, response);
			break;
		}
	}

	private void addCartMaterial(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		
		int customerId = Integer.parseInt(request.getParameter("customerId"));
		int materialId = Integer.parseInt(request.getParameter("materialId"));
		
		System.out.println("customerId " + customerId);
		System.out.println("materialId " + materialId);
		
		System.out.println("In addCartMaterial");
	
		List<Cart> listOfCartMaterials = cartDao.cartMaterialExists(customerId, materialId);
		
		if(listOfCartMaterials.isEmpty())
		{
			Cart newCartMaterial = new Cart(0, customerId, materialId, 1);
			cartDao.insertCartMaterial(newCartMaterial);
		}
		else
		{
			Cart updateCartMaterial = cartDao.getCartObjectById(customerId, materialId);
			int quantity = updateCartMaterial.getQuantity();
			updateCartMaterial.setQuantity(quantity +1);
			cartDao.updateCart(updateCartMaterial);
		}
		
		//List<Cart> allCartMaterials = cartDao.getAllCartMaterials();
		List<Cart> allCartMaterials = cartDao.getAllCartMaterialsForCust(customerId);
		//System.out.println("******************"+allCartMaterials+"***********************");
		MaterialDAO mat=new MaterialDAO();
		List<Material> allMaterials = mat.getAllMaterials();
		
		CustomerDAO customerDao = new CustomerDAO();
		Customer customer = customerDao.getCustomerbyId(customerId);
		
		
		request.getSession().setAttribute("customer",customer);
		request.getSession().setAttribute("allMaterials",allMaterials);
		request.getSession().setAttribute("allCartMaterials",allCartMaterials);
		request.getRequestDispatcher("WEB-INF/view/viewMaterialsCart.jsp").forward(request, response);
		//response.sendRedirect("CartServlet?action=addCartMaterial&customerId="+customerId+"&materialId="+materialId);
	}
	
private void deleteCartMaterial(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		
		int customerId = Integer.parseInt(request.getParameter("customerId"));
		int materialId = Integer.parseInt(request.getParameter("materialId"));
		
		System.out.println("customerId " + customerId);
		System.out.println("materialId " + materialId);
		
		System.out.println("In deleteCartMaterial");
		
		Cart updateCartMaterialQuantity = cartDao.getCartObjectById(customerId, materialId);
		int quantity = updateCartMaterialQuantity.getQuantity();
		
		if((quantity - 1) == 0)
		{
			cartDao.deleteCartMaterial(updateCartMaterialQuantity);
		}
		else
		{
			updateCartMaterialQuantity.setQuantity(quantity -1);
			cartDao.updateCart(updateCartMaterialQuantity);
		}
		
		
		
		List<Cart> allCartMaterials = cartDao.getAllCartMaterialsForCust(customerId);
		System.out.println("******************"+allCartMaterials+"***********************");
		MaterialDAO mat=new MaterialDAO();
		List<Material> allMaterials = mat.getAllMaterials();

		
		request.getSession().setAttribute("allMaterials",allMaterials);
		request.getSession().setAttribute("allCartMaterials",allCartMaterials);
		request.getRequestDispatcher("WEB-INF/view/viewMaterialsCart.jsp").forward(request, response);
	}
	
	private void getAllCartMaterials(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		List<Cart> listOfCartMaterials = cartDao.getAllCartMaterials();
		/*Pass the listOfCartMaterials to the JSP as an attribute*/
		request.setAttribute("listOfCartMaterials", listOfCartMaterials);
		/*Open the JSP Page*/
		RequestDispatcher dispatcher = request.getRequestDispatcher("\\WEB-INF\\view\\viewMaterialsCart.jsp");
		dispatcher.forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
