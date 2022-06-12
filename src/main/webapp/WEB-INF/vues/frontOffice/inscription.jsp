	<%@ include file="header.jsp" %>
	
		<h3>Inscription</h3>
		
		<form action="${pageContext.request.contextPath}/User" method="post">
			<span class="text-danger">${requestScope.erreur_champ.erreurs['civility']}</span>
			<div class="form-check">
				<input type="radio" class="form-check-input" name="civility" id="femme" value="Femme">
    			<label class="form-check-label" for="femme">Femme</label>
			</div>
			<div class="form-check">
				<input type="radio" class="form-check-input" name="civility" id="homme" value="Homme">
    			<label class="form-check-label" for="homme">Homme</label>
			</div>
			
			<div class="form-group">
				<label for="">Prénom</label>
				<span class="text-danger">${requestScope.erreur_champ.erreurs['prenom']}</span>
				<input type="text" name="prenom" class="form-control" />
			</div>
			
			<div class="form-group">
				<label for="">Nom</label>
				<span class="text-danger">${requestScope.erreur_champ.erreurs['nom']}</span>
				<input type="text" name="nom" class="form-control" />
			</div>
			
			<div class="form-group">
				<label for="">Pseudo</label>
				<span class="text-danger">${requestScope.erreur_champ.erreurs['pseudo']}</span>
				<input type="text" name="pseudo" class="form-control" />
			</div>
			
			<div class="form-group">
				<label for="">Mot de passe</label>
				<span class="text-danger">${requestScope.erreur_champ.erreurs['mdp']}</span>
				<input type="text" name="mdp" class="form-control" />
			</div>
			
			<div class="form-group">
				<label for="">Email</label>
				<span class="text-danger">${requestScope.erreur_champ.erreurs['email']}</span>
				<input type="email" name="email" class="form-control" value="hk@gmail.com"/>
			</div>
			
			<div class="mt-3">
				<input type="submit" class="btn btn-success" name="action" value="Inscription" />
				<input type="reset" class="btn btn-danger" />
			</div>
			
		</form>
		<%@ include file="footer.jsp" %>
