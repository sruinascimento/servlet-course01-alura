package com.example.coursefinalservlet;

import com.example.coursefinalservlet.dao.EmpresaDao;
import com.example.coursefinalservlet.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/remover-empresa")
public class RemoveEmpresaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        try {
            EntityManager entityManager = JPAUtil.getEntityManager();
            EmpresaDao empresaDao = new EmpresaDao(entityManager);

            Long id = Long.valueOf(req.getParameter("id"));

            entityManager.getTransaction().begin();
            empresaDao.remove(id);
            entityManager.getTransaction().commit();

            resp.sendRedirect("/mostrar-empresas");

        } catch (NumberFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
