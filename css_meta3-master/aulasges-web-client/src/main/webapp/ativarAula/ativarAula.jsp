<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--  No scriptlets!!! 
	  See http://download.oracle.com/javaee/5/tutorial/doc/bnakc.html 
-->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="model" class="presentation.web.model.AtivarAulaModel" scope="request"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/resources/app.css"> 
<title>Ativar Aula</title>
</head>
<body>

	<h2>Ativar Aula</h2>

	<form action="ativarAulaSubmit" method="post">
	
	<div>
    	<h5>Instalacoes Disponiveis:</h5> 
    	<select name="instalacao">
    		<c:forEach var="instalacao" items="${model.instalacoes}">
    			<c:choose>
    				<c:when test="${model.instalacao == instalacao.nome}">
						<option selected = "selected" value="${instalacao.nome}">${instalacao.nome}</option>
				    </c:when>
    				<c:otherwise>
						<option value="${instalacao.nome}">${instalacao.nome}</option>
				    </c:otherwise>
    			</c:choose>
    		</c:forEach> 
    	</select>
    </div>
    
    <div class="mandatory_field">
    	<label for="nomeAula">Introduza o nome da Aula:</label> 
    	<input type="text" name="nomeAula" size="50" value="${model.nomeAula}"/> 
    </div>
    
    <div class="mandatory_field">
    	<label for="dataIni">Introduza a data de inicio(yyyy-mm-dd):</label> 
    	<input type="text" name="dataIni" size="50" value="${model.dataIni}"/> 
    </div>
    
     <div class="mandatory_field">
    	<label for="dataFim">Introduza a data de fim(yyyy-mm-dd):</label> 
    	<input type="text" name="dataFim" size="50" value="${model.dataFim}"/> 
    </div>
    
    <div class="mandatory_field">
    	<label for="maxAlunos">Introduza o maximo de alunos na aula:</label> 
    	<input type="text" name="maxAlunos" size="10" value="${model.maxAlunos}"/> 
    </div>
    
    <input type="submit" value="Ativar Aula">
    </form>
    
    <c:if test="${model.hasMessages}">
	<p>Mensagens</p>
	<ul>
	<c:forEach var="mensagem" items="${model.messages}">
		<li>${mensagem} 
	</c:forEach>
	</ul>
</c:if>

</body>
</html>