package com.example.coursefinalservlet;

import com.example.coursefinalservlet.dao.EmpresaDao;
import com.example.coursefinalservlet.model.Empresa;
import com.example.coursefinalservlet.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(urlPatterns = "/altera-empresa")
public class AlteraEmpresaServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(request.getParameter(""));
        try {
            System.out.println("id::: " + request.getParameter("id"));
            Long id = Long.valueOf(request.getParameter("id"));
            EntityManager entityManager = JPAUtil.getEntityManager();
            EmpresaDao empresaDao = new EmpresaDao(entityManager);

            Empresa empresa = empresaDao.getEmpresaById(id);
            if (empresa != null) {
                empresa.setNome(request.getParameter("empresa"));
                empresa.setCnpj(request.getParameter("cnpj"));
                empresa.setDataCadastro(new Date());
                entityManager.getTransaction().begin();
                empresaDao.atualiza(empresa);
                entityManager.getTransaction().commit();
                entityManager.close();
                response.sendRedirect("/mostrar-empresas");
            }

        } catch (NumberFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
