package controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.mapping.Set;

import model.Customer;
import model.EmailAddress;

@WebServlet("/CustomersServlet")
public class CustomersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private CustomerDAO customerDao;
	
    public CustomersServlet() {
    	customerDao = new CustomerDAO();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet in CarpentryDisaster");
		
		String action = request.getParameter("action");
		if (action == null) {
			action = "viewAll";
		}
		
		switch (action) {
		case "showCustomerSearchForm":
			request.getRequestDispatcher("WEB-INF/view/customerSearch.jsp").forward(request, response);
			break;
		case "searchForCustomer":
			searchForCustomer(request, response);
			break;
		case "showInsertCustomerForm":
			request.getRequestDispatcher("WEB-INF/view/insertCustomer.jsp").forward(request, response);
			break;
		case "insertCustomer":
			insertCustomer(request, response);
			break;
		default: // viewAll
			viewAll(request, response);
			break;
		}
	}
	
	protected void searchForCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String search = request.getParameter("search");
		String searchType = request.getParameter("select");
		
		/*Gets the list of customers from the DAO*/
		List<Customer> listOfCustomers = customerDao.search(search, searchType);
		
		/* Pass the listOfCustomers to the JSP as an attribute*/
		request.setAttribute("listOfCustomers",listOfCustomers);
		/* Open the viewAll customers JSP page*/
		RequestDispatcher dispatcher = request.getRequestDispatcher(
				"\\WEB-INF\\view\\viewAll.jsp");
		dispatcher.forward(request, response);
		
	}

	protected void viewAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In viewAll()");
		request.setAttribute("listOfCustomers", customerDao.getAllCustomers());
		request.getRequestDispatcher("WEB-INF/view/viewAll.jsp").forward(request, response);
	}

	protected void insertCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstname = request.getParameter("firstname");
		String surname = request.getParameter("surname");
		String phoneNumber = request.getParameter("phonenumber");
		String address = request.getParameter("address");
		String [] emails = request.getParameter("emails").split("[\\s,]+");
		HashSet<EmailAddress> setOfEmails = new HashSet<>();
		
		 /*The loop goes through the String array of emails (each one
		 * is a String). To add to the HashSet, I need each email address
		 * which is a String to be an EmailAddress object, therefore
		 * I have passed the email String into the EmailAddress
		 * constructor and added that to the HashSet  */
		for (String email : emails) {
			/* You could use a constructor with no id (if you have one) or use
			 * the constructor that takes all parameters and pass in 0 for the id,
			 * if you omitted the id, it would default to 0 anyway. */
			 
			setOfEmails.add(new EmailAddress(0, email));
		}
		
		String description = request.getParameter("description");
		String recommendedBy = request.getParameter("recommendedBy");
		String year = request.getParameter("year");
		
		
		 //Create a Customer object 
		Customer c = new Customer(0, firstname, surname, phoneNumber, address, setOfEmails, description, recommendedBy, year);
		
		
		
		/* Send the Person object to the DAO */
		if (customerDao.customerExists(c)) {
			request.setAttribute("message", 
					c.getFirstName() + " is already in the database");
			request.getRequestDispatcher("WEB-INF/view/insertCustomer.jsp").forward(request, response);
		} else {
			customerDao.insertCustomer(c);
			/* After a Person is inserted, view them all! */
			response.sendRedirect("CustomersServlet?action=viewAll");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
//	Set<EmailAddress> marysEmails = new HashSet<>();
//	EmailAddress email1 = new EmailAddress(0, "mary@murphy.ie");
//	EmailAddress email2 = new EmailAddress(0, "marymurp@me.com");
//	// Add the EmailAddress objects to the HashSet
//	marysEmails.add(email1);
//	marysEmails.add(email2);
//	
//	Person p = new Person(0, "Mary", "Murphy", "0839876541", marysEmails);
//	dao.insertPerson(p);
//	response.getWriter().println(p);
}