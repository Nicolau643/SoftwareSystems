<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--  No scriptlets!!! 
	  See http://download.oracle.com/javaee/5/tutorial/doc/bnakc.html 
-->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="model" class="presentation.web.model.CriarAulaModel" scope="request"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/resources/app.css"> 
<title>Criar Aula</title>
</head>
<body>
<h2>Criar Aula</h2>

<form action="criarAulaSubmit" method="post">
	
	
	<div>
    	<h5>Modalidades Disponiveis:</h5> 
    	<select name="modalidade">
    		<c:forEach var="modalidade" items="${model.modalidades}">
    			<c:choose>
    				<c:when test="${model.modalidade == modalidade.name}">
						<option selected = "selected" value="${modalidade.name}">${modalidade.name}</option>
				    </c:when>
    				<c:otherwise>
						<option value="${modalidade.name}">${modalidade.name}</option>
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
    	<label for="dias">Introduza os dias de semana(Ex:Segunda,Terça):</label> 
    	<input type="text" name="dias" size="50" value="${model.dias}"/> 
    </div>
    <div class="mandatory_field">
    	<label for="horaIni">Introduza a hora de inicio(hh:mm):</label> 
    	<input type="text" name="horaIni" size="50" value="${model.horaIni}"/> 
    </div>
    <div class="mandatory_field">
    	<label for="duracao">Introduza a Duracao</label> 
    	<input type="text" name="duracao" size="50" value="${model.duracao}"/> 
    </div>

	<input type="submit" value="Criar Aula">
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