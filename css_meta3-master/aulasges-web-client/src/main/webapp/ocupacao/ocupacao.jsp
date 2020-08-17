<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--  No scriptlets!!! 
	  See http://download.oracle.com/javaee/5/tutorial/doc/bnakc.html 
-->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="model" class="presentation.web.model.OcupacaoModel" scope="request"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/resources/app.css"> 
<title>Ocupacao</title>
</head>
<body>

<h2>Ocupacao</h2>

	<form action="ocupacaoSubmit" method="get">
	
		<div class="mandatory_field">
	    	<label for="nomeInst">Introduza o nome da Instalacao:</label> 
	    	<input type="text" name="nomeInst" size="50" value="${model.nomeInst}"/> 
	    </div>
	    
	    <div class="mandatory_field">
	    	<label for="data">Introduza a data pretendida (yyyy-mm-dd):</label> 
	    	<input type="text" name="data" size="50" value="${model.data}"/> 
	    </div>
	    
	   <input type="submit" value="Procurar">
	
		 <table>
		    	<tr>
		    		<th> Nome </th>
		    		<th> Horario </th>
		    		<th> Duracao </th>
		    		
		    	</tr>
		    	
		    	<c:forEach var="aula" items="${model.aulas}">
		    		
		    		<tr>
		    			<td> ${aula.nome} </td>
		    			<td> ${aula.horario} </td>
		    			<td> ${aula.duracao} </td>
		    			
		    		 </tr>
		    	
		    	</c:forEach> 
		    
		 </table>
		 
	</form>

</body>
</html>