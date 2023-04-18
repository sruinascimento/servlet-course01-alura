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

@WebServlet("/mostrar-empresa")
public class MostraEmpresaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EmpresaDao empresaDao = new EmpresaDao(entityManager);
        try {
            Long id = Long.valueOf(req.getParameter("id"));
            Empresa empresa = empresaDao.getEmpresaById(id);
            req.setAttribute("empresa", empresa);
            req.getRequestDispatcher("/altera-empresa.jsp")
                    .forward(req, resp);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }

    }
}
