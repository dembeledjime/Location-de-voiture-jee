	<%@ include file="../frontOffice/header.jsp" %>
	
	
	<div class="px-3">
		<table class="table table-striped table-hover">
			<tr class="table-dark">
				<th>Id</th>
				<th>Titre</th>
				<th>Adresse</th>
				<th>Code postal</th>
				<th>Ville</th>
				<th>Description</th>
				<th>Photo</th>
				<th>Actions</th>
			</tr>
			
			<c:forEach items="${requestScope.agences}" var="agence">
				<tr>
					<td> <c:out value="${agence.id_agence}" /> </td>
					<td> <c:out value="${agence.titre}" /> </td>
					<td> <c:out value="${agence.adresse}" /> </td>
					<td> <c:out value="${agence.cp}" /> </td>
					<td> <c:out value="${agence.ville}" /> </td>
					<td> <c:out value="${agence.description}" /> </td>
					<td> 
						<img class="img-fluid" style="width: 25%" src="utils/images/agences/<c:out value="${agence.photo}" />" alt="${agence.photo}" />
					</td>
					<td> 
						<a href="${pageContext.request.contextPath}/Gestion_agence?action=update&id=${agence.id_agence}">update</a>
						<a href="${pageContext.request.contextPath}/Gestion_agence?action=delete&id=${agence.id_agence}">Delete</a>
					</td>
				</tr>
			</c:forEach>
			
		</table>
		
		<form action="${pageContext.request.contextPath}/Gestion_agence" method="post" class="row" enctype="multipart/form-data">
		
			<input type="hidden" name="id_agence" class="form-control" value="<c:out value='${requestScope.agence.id_agence}' />"/>
			<div class="form-group col-4">
				<label for="">Titre</label>
				<span class="text-danger">${requestScope.erreur_champ.erreurs['titre']}</span>
				<input type="text" name="titre" class="form-control" value="<c:out value='${requestScope.agence.titre}'/>"/>
			</div>
			
			<div class="form-group col-4">
				<label for="">Adresse</label>
				<span class="text-danger">${requestScope.erreur_champ.erreurs['adresse']}</span>
				<input type="text" name="adresse" class="form-control" value="<c:out value='${requestScope.agence.adresse}'/>" />
			</div>
			
			<div class="form-group col-1">
				<label for="">Code postal</label>
				<span class="text-danger">${requestScope.erreur_champ.erreurs['cp']}</span>
				<input type="text" name="cp" class="form-control"  value="<c:out value='${requestScope.agence.cp}'/>"/>
			</div>
			
			<div class="form-group col-6">
				<label for="">Description</label>
				<span class="text-danger">${requestScope.erreur_champ.erreurs['email']}</span>
				<textarea name="description" class="form-control" cols="30" rows="10"><c:out value='${requestScope.agence.description}'/></textarea>
			</div>
			
			<div class="form-group col-6">
				<label for="">Ville</label>
				<span class="text-danger">${requestScope.erreur_champ.erreurs['ville']}</span>
				<input type="text" name="ville" class="form-control"  value="<c:out value='${requestScope.agence.ville}'/>"/>
			</div>
			
			<div class="form-group col-6">
				<label for="">Photo</label>
				<span class="text-danger">${requestScope.erreur_champ.erreurs['email']}</span>
				<input type="file" name="photo" class="form-control" />
				<input type="hidden" name="oldPhoto" value="${agence.photo}" class="form-control" />
				<img class="img-fluid" style="width: 25%" 	src="utils/images/agences/<c:out value="${agence.photo}" />" alt="${agence.photo}" />
			</div>
			
			<div class="mt-3">
				<input type="submit" class="btn btn-success" name="action" value="Ajouter" />
				<input type="reset" class="btn btn-danger" />
			</div>
			 
		</form>
	</div>
	<%@ include file="../frontOffice/footer.jsp" %>
	
	