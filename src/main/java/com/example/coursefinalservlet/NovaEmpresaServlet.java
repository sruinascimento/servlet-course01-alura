package com.example.coursefinalservlet;

import com.example.coursefinalservlet.dao.EmpresaDao;
import com.example.coursefinalservlet.model.Empresa;
import com.example.coursefinalservlet.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/nova-empresa")
public class NovaEmpresaServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Cadastrando nova empresa");
        String nomeEmpresa = request.getParameter("empresa");
        String cnpj = request.getParameter("cnpj");
        System.out.println("Params: ");
        System.out.println("NOme " + nomeEmpresa);
        System.out.println("CNPJ " + cnpj);
        Empresa empresa = new Empresa(nomeEmpresa, cnpj);
        System.out.println(empresa);
        EntityManager entityManager = JPAUtil.getEntityManager();
        EmpresaDao empresaDao = new EmpresaDao(entityManager);
        if (!cnpj.equals("") && !nomeEmpresa.equals("")) {
            entityManager.getTransaction().begin();
            empresaDao.save(empresa);
            entityManager.getTransaction().commit();
        }
//        try {
//            request.getRequestDispatcher("/empresas").forward(request, response);
        try {
            request.setAttribute("empresa", empresa.getNome());
            request.setAttribute("cnpj", empresa.getCnpj());
            response.sendRedirect("/mostrar-empresas");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        } catch (ServletException | IOException e) {
//            throw new RuntimeException(e);
//        }
    }

}
