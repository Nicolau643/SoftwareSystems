<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--  No scriptlets!!! 
	  See http://download.oracle.com/javaee/5/tutorial/doc/bnakc.html 
-->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="model" class="presentation.web.model.InscreverAulaModel" scope="request"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/resources/app.css"> 
<title>Inscrever Aula</title>
</head>
<body>

<h2>Inscrever Aula</h2>

	<form action="inscreverAulaSubmit" method="post">
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
	    <div>
	    	<h5>Tipos de Inscricoes Disponiveis:</h5> 
	    	<select name="inscricao">
	    		<c:forEach var="inscricao" items="${model.tiposDeInscricao}">
	    			<c:choose>
	    				<c:when test="${model.inscricao == inscricao}">
							<option selected = "selected" value="${inscricao}">${inscricao}</option>
					    </c:when>
	    				<c:otherwise>
							<option value="${inscricao}">${inscricao}</option>
					    </c:otherwise>
	    			</c:choose>
	    		</c:forEach> 
	    	</select>
	    </div>
	    
	    
	    <input type="submit" name ="action" value="Procurar">
	 
	    <table>
	    	<tr>
	    		<th> Nome </th>
	    		<th> Horario </th>
	    		<th> Dias de Semana </th>
	    		<th> Instalacao </th>
	    		<th> Vagas </th>
	    	</tr>
	    	
	    	<c:forEach var="aula" items="${model.aulas}">
	    		
	    		<tr>
	    			<td> ${aula.nome} </td>
	    			<td> ${aula.horario} </td>
	    			<td> ${aula.diasSemana} </td>
	    			<td> ${aula.inst} </td>
	    			<td> ${aula.vagas} </td>
	    		 </tr>
	    	
	    	</c:forEach> 
	    
	    </table>
	    
	<br>
	<br>
	<div class="mandatory_field">
    	<label for="nomeAula">Introduza o nome da Aula:</label> 
    	<input type="text" name="nomeAula" size="50" value="${model.nomeAula}"/> 
    </div>
    
    <div class="mandatory_field">
    	<label for="numUtente">Introduza o numero de utente:</label> 
    	<input type="text" name="numUtente" size="50" value="${model.numUtente}"/> 
    </div>
    
    <div class="optional_field">
    	<label for="diaSemana">Caso seja seja Avulso, Introduza o dia de Semana:</label> 
    	<input type="text" name="diaSemana" size="50" value="${model.diaSemana}"/> 
    </div>

	<input type="submit" name="action" value="Inscrever Aula">
	    
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