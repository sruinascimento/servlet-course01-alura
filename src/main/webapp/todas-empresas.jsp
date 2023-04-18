<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Empresas</h1>

<c:if test="${not empty empresa and not empty cnpj}">
    Empresa: ${empresa} cadastrada com sucesso!
    CNPJ: ${cnpj}
</c:if>

<ul>
    <c:forEach items="${empresas}" var="empresa">
        <li>
            Nome : ${empresa.nome}, CNPJ: ${empresa.cnpj}, Data de Cadastro - <fmt:formatDate
                value="${empresa.dataCadastro}"/>
            <a href="/mostrar-empresa?id=${empresa.id}">atualizar</a>
            <a href="/remover-empresa?id=${empresa.id}">remove</a>
        </li>
    </c:forEach>
</ul>
</body>
</html>
