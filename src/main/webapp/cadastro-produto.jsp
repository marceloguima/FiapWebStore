<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html lang="pt-br">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<%@include file="header.jsp" %>
<div class="container w-50">
    <div class="mt-5 ms-5 me-5">
        <div class="card mb-3">
            <div class="text-center card-header">
                CADASTRO DE PRODUTO
            </div>

            <c:if test="${not empty mensagem}">
                <div class="alert alert-success ms-2 me-2 m-auto">${mensagem}</div>
            </c:if>

            <c:if test="${not empty erro}">
                <div class="alert alert-danger ms-2 me-2 m-auto">${erro}</div>
            </c:if>

            <div CLASS="bg-body">
                <form action="produtos" method="post" >
                    <div class="form-group">
                        <label for="id-nome" class="ms-2">Nome</label>
                        <input type="text" class="form-control" id="id-nome" name="nome">
                    </div>
                    <div class="form-group">
                        <label for="id-valor" class="ms-2">Preço</label>
                        <input class="form-control" type="number" id="id-valor" name="preco">
                    </div>
                    <div class="form-group">
                        <label for="id-fabricação" class="ms-2">Data de fabricação</label>
                        <input class="form-control" type="date" id="id-fabricação" name="fabricação">
                    </div>
                    <div class="form-group">
                         <label for="id-quantidade" class="ms-2">Quantidade</label>
                        <input type="number" class="form-control" id="id-quantidade" name="quantidade">
                    </div>
                    <input type="submit" value="Salvar" class="btn btn-primary mt-3">
                </form>
            </div>
        </div>
    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>