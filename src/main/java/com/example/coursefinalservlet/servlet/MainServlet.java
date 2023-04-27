package com.example.coursefinalservlet.servlet;

import com.example.coursefinalservlet.model.ActionServlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.example.coursefinalservlet.model.Acao.*;

@WebServlet("/root")
public class MainServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) {
        String parametroAcao = request.getParameter("acao");


        HttpSession session = request.getSession();
        boolean usuarioNaoLogado = session.getAttribute("usuarioLogado") == null;
        boolean acaoProtegida = !(LOGIN_FORM.toString().equals(parametroAcao) || LOGIN_USUARIO.toString().equals(parametroAcao));

        if(acaoProtegida && usuarioNaoLogado) {
            try {
                response.sendRedirect("/root?acao=" + LOGIN_FORM);
                return;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        String className = "com.example.coursefinalservlet.action." + parametroAcao;
        String redirect = null;

        try {
            Class<?> classe = Class.forName(className);
            Object obj = classe.newInstance();
            ActionServlet action = (ActionServlet) obj;
            redirect = (String) action.execute(request, response);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        String[] typeAndAdress = separatedString(redirect);

        if ("forward".equals(typeAndAdress[0])) {
            try {
                request.getRequestDispatcher("WEB-INF/view/" + typeAndAdress[1]).forward(request, response);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
        }
        if ("redirect".equals(typeAndAdress[0])) {
            try {
                response.sendRedirect(typeAndAdress[1]);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


//        String redirect = "";
//        if (LISTA_EMPRESA.toString().equals(parametroAcao)) {
//            redirect = new ListaEmpresa().execute(request, response);
//        }
//
//        if (REMOVE_EMPRESA.toString().equals(parametroAcao)) {
//            redirect =  new RemoveEmpresa().execute(request, response);
//        }
//
//        if (MOSTRA_EMPRESA.toString().equals(parametroAcao)) {
//            redirect = new MostraEmpresa().execute(request, response);
//        }
//
//
//        if (ALTERA_EMPRESA.toString().equals(parametroAcao)) {
//            redirect = new AlteraEmpresa().execute(request, response);
//        }
//
//        if(NOVA_EMPRESA.toString().equals(parametroAcao)) {
//            redirect = new NovaEmpresa().execute(request, response);
//        }
//
//        if (CADASTRO_EMPRESA_FORM.toString().equals(parametroAcao)) {
//            redirect = new CadastroEmpresaForm().execute(request, response);
//        }


    }

    private static String[] separatedString(String sentence) {
        return sentence.split(":");
    }

}
