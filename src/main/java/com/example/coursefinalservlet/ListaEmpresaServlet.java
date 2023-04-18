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
import java.util.List;

@WebServlet("/mostrar-empresas")
public class ListaEmpresaServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EmpresaDao empresaDao = new EmpresaDao(entityManager);
        List<Empresa> empresas = empresaDao.getEmpresas();
        System.out.println(empresas);
        req.setAttribute("empresas", empresas);
        try {
            req.getRequestDispatcher("/todas-empresas.jsp").forward(req, resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
