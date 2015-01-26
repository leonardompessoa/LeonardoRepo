package br.com.leonardo.estudos.restservlet.servlet;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import br.com.leonardo.estudos.restservlet.model.Pessoa;
import br.com.leonardo.estudos.restservlet.model.Pessoas;

/**
 * Servlet implementation class PessoaListServlet
 */
@WebServlet({"/Pessoas", "/Pessoas/*"})
public class PessoaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static JAXBContext jaxbContext;
	private static Marshaller marshaller;
	private static Unmarshaller unmarshaller;
	
	static {
		try {
			jaxbContext = JAXBContext.newInstance(Pessoas.class, Pessoa.class);
			marshaller = jaxbContext.createMarshaller();
			unmarshaller = jaxbContext.createUnmarshaller();
		} catch (JAXBException e) {
			
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestPath = request.getPathInfo();
		String cpf = null;
		if(requestPath != null) {
			cpf = requestPath.substring(1);
		}
		
		Pessoas pessoas = new PessoaRepo().find(cpf);
		try {
			marshaller.marshal(pessoas, response.getOutputStream());
		} catch (JAXBException e) {
			response.sendError(500, e.getMessage());
			e.printStackTrace();
		}
		response.setContentType("text/xml");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestPath = request.getPathInfo();
		if(requestPath != null) {
			response.sendError(400);
		}
		Pessoa pessoa = new Pessoa();
		BufferedReader reader = request.getReader();
		try {
			pessoa = (Pessoa) unmarshaller.unmarshal(reader);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		if(! new PessoaRepo().insert(pessoa)) {
			response.sendError(400, "Código já existe no sistema");
		} 
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
}
