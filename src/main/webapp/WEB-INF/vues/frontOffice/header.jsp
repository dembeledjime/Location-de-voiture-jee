<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
	
	<link rel="stylesheet" href="utils/css/style.css">
	<title>Projet Sira</title>
</head>
<body>

	<header class="text-center text-white">
		<h1><a href="${pageContext.request.contextPath}/Home">Bienvenue à bord</a></h1>
		<h2>Location de véhicule 24/24 7j/7j</h2>
		<nav>
			<c:choose>
				<c:when test="${!empty sessionScope.user}">
					<a href="${pageContext.request.contextPath}/User?action=inscription" class="btn btn-success">Compte</a>
					<c:if test="${sessionScope.user.statut == 'admin'}">
						<a href="${pageContext.request.contextPath}/User?action=gestionM" class="btn btn-success">Membres</a>
						<a href="${pageContext.request.contextPath}/Gestion_agence?action=gestionA" class="btn btn-success">Agences</a>
						<a href="${pageContext.request.contextPath}/User?action=inscription" class="btn btn-success">Véhicules</a>
						<a href="${pageContext.request.contextPath}/User?action=inscription" class="btn btn-success">Commandes</a>	
					</c:if>
					<a href="${pageContext.request.contextPath}/User?action=deconnexion" class="btn btn-danger">Deconnexion</a>
				</c:when>
				<c:otherwise>
					<a href="${pageContext.request.contextPath}/User?action=inscription" class="btn btn-success">Inscription</a>
					<a href="${pageContext.request.contextPath}/User?action=connexion" data-bs-toggle="modal" data-bs-target="#exampleModal" class="btn btn-success">
						Connexion
					</a>
				</c:otherwise>
			</c:choose>
		</nav>
	</header>
	<main class="container-fluid">
	
	
	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Connexion</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	       	<form action="${pageContext.request.contextPath}/User" method="post">
			
				<div class="form-group">
					<label for="">Pseudo</label>
					<span class="text-danger">${requestScope.erreur_champ.erreurs['pseudo']}</span>
					<input type="text" name="pseudo" class="form-control" value="toto" />
				</div>
				
				<div class="form-group">
					<label for="">Mot de passe</label>
					<span class="text-danger">${requestScope.erreur_champ.erreurs['mdp']}</span>
					<input type="text" name="mdp" class="form-control" value="ilci"/>
				</div>
						
			      <div class="modal-footer">
			        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
			        <button type="submit" class="btn btn-primary" name="action" value="connexion">Log</button>
			      </div>
			</form>
	      </div>
	    </div>
	  </div>
	</div>
		