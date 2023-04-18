<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/nova-empresa" var="link-form"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Cadastro</title>
    <link rel="stylesheet" href="sytle.css">
</head>
<body>

<form action="/altera-empresa" method="POST">

    <label for="empresa">Nome</label>
    <input type="text" id="empresa" name="empresa" value="${empresa.nome}"></br>

    <label for="cnpj">CNPJ</label>
    <input type="text" id="cnpj" name="cnpj" value="${empresa.cnpj}"></br>

    <input type="text" name="id" hidden="hidden" value="${empresa.id}"></br>

    <input type="submit" value="cadastrar" class="btn__form">
</form>
</body>
</html>