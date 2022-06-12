	<%@ include file="../frontOffice/header.jsp" %>
	
	
	<div class="px-3">
		<table class="table table-striped table-hover">
			<tr class="table-dark">
				<th>Id</th>
				<th>Civilité</th>
				<th>Prénom</th>
				<th>Nom</th>
				<th>Pseudo</th>
				<th>Email</th>
				<th>Statut</th>
				<th>Date_enregistrement</th>
				<th>Actions</th>
			</tr>
			
			<c:forEach items="${requestScope.users}" var="user">
				<tr>
					<td> <c:out value="${user.id_membre}" /> </td>
					<td> <c:out value="${user.civility}" /> </td>
					<td> <c:out value="${user.prenom}" /> </td>
					<td> <c:out value="${user.nom}" /> </td>
					<td> <c:out value="${user.pseudo}" /> </td>
					<td> <c:out value="${user.email}" /> </td>
					<td> <c:out value="${user.statut}" /> </td>
					<td> <c:out value="${user.date_enregistrement}" /> </td>
					<td> 
						<a href="${pageContext.request.contextPath}/User?action=update&id=${user.id_membre}">update</a>
						<a href="${pageContext.request.contextPath}/User?action=delete&id=${user.id_membre}">Delete</a>
					</td>
				</tr>
			</c:forEach>
			
		</table>
		
		<form action="${pageContext.request.contextPath}/User" method="post" class="row">
			<input type="hidden" name="admin" value="admin" />
			<input type="hidden" name="id_membre" class="form-control" value="<c:out value='${requestScope.user.id_membre}'/>"/>
			<div class="form-group col-4">
				<label for="">Prénom</label>
				<span class="text-danger">${requestScope.erreur_champ.erreurs['prenom']}</span>
				<input type="text" name="prenom" class="form-control" value="<c:out value='${requestScope.user.prenom}'/>"/>
			</div>
			
			<div class="form-group col-4">
				<label for="">Nom</label>
				<span class="text-danger">${requestScope.erreur_champ.erreurs['nom']}</span>
				<input type="text" name="nom" class="form-control" value="<c:out value='${requestScope.user.nom}'/>" />
			</div>
			
			<div class="col-4">
				<p>Civilité</p>
				<span class="text-danger">${requestScope.erreur_champ.erreurs['civility']}</span>
				<div class="form-check form-check-inline">
					<input ${requestScope.user.civility == 'Femme' ? 'checked' : ''} type="radio" class="form-check-input" name="civility" id="femme" value="Femme">
		   			<label class="form-check-label" for="femme">Femme</label>
				</div>
				<div class="form-check form-check-inline">
					<input ${requestScope.user.civility == 'Homme' ? 'checked' : ''} type="radio" class="form-check-input" name="civility" id="homme" value="Homme">
		   			<label class="form-check-label" for="homme">Homme</label>
				</div>
			</div>
			
			<div class="form-group col-6">
				<label for="">Pseudo</label>
				<span class="text-danger">${requestScope.erreur_champ.erreurs['pseudo']}</span>
				<input type="text" name="pseudo" class="form-control"  value="<c:out value='${requestScope.user.pseudo}'/>"/>
			</div>
			
			<div class="form-group col-6">
				<label for="">Mot de passe</label>
				<span class="text-danger">${requestScope.erreur_champ.erreurs['mdp']}</span>
				<input type="text" name="mdp" class="form-control"  value="<c:out value='${requestScope.user.mdp}'/>"/>
			</div>
			
			<div class="form-group col-6">
				<label for="">Email</label>
				<span class="text-danger">${requestScope.erreur_champ.erreurs['email']}</span>
				<input type="email" name="email" class="form-control" value="hk@gmail.com" value="<c:out value='${requestScope.user.email}'/>"/>
			</div>
			
			<div class="col-4">
				<p>Statut</p>
				<span class="text-danger">${requestScope.erreur_champ.erreurs['statut']}</span>
				<div class="form-check form-check-inline">
					<input ${requestScope.user.statut == 'client' ? 'checked' : ''} type="radio" class="form-check-input" name="statut" id="client" value="client">
		   			<label class="form-check-label" for="client">Client</label>
				</div>
				<div class="form-check form-check-inline">
					<input ${requestScope.user.statut == 'admin' ? 'checked' : ''} type="radio" class="form-check-input" name="statut" id="admin" value="admin">
		   			<label class="form-check-label" for="admin">Admin</label>
				</div>
			</div>
			
			<div class="mt-3">
				<input type="submit" class="btn btn-success" name="action" value="Inscription" />
				<input type="reset" class="btn btn-danger" />
			</div>
			
		</form>
	</div>
	<%@ include file="../frontOffice/footer.jsp" %>
	
	