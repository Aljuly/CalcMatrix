package com.calcmatrix.jsp.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;


@WebServlet(urlPatterns = "/CalcMatrix", loadOnStartup = 1) 
public class MainCalc extends HttpServlet  {

	private static final long serialVersionUID = 1L;
	
    private ServletContext context;
	private TaskData taskData;
	private Map<Integer, InputDescription> tasks; 
	
	@Override
	public void init(ServletConfig config) throws ServletException {
	    try {
	        taskData = (new JsonCreator("\\WebFormDesc.json")).getInputdata();
            tasks = taskData.getInputDataDesc();
        } catch (IOException e) {
            e.printStackTrace();
        }
		context = config.getServletContext();
	}	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("CalcMatrix.jsp").forward(req, resp);
		req.setAttribute("taskData", tasks);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Calculator calc = new Calculator();
		
		// get received JSON data from request
        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String json = "";
        if(br != null){
            json = br.readLine();
        }
        
        // initiate jackson mapper
        ObjectMapper mapper = new ObjectMapper();
        calc.setInputdata(mapper.readValue(br, InputData.class));
        
        // Set response type to JSON
        resp.setContentType("application/json");   
        
        // Send calculationresult to the client
        mapper.writeValue(resp.getOutputStream(), calc.getOutputdata());
	}
	
}
