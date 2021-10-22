// Quantity
function add2CartBtn(e){
	console.log("correct");
	menuId = e.attr( "menuid");
	addToCart(menuId);
}
function addToCart(menuId){
	quantity = $("#quantity" + menuId).val();
	console.log(menuId, quantity);
	url = contextPath + "Cart/add/" + menuId + "/" + quantity;
	
	$.ajax({
		type : "POST",
		url: url,
		beforeSend : function(xhr){
			xhr.setRequestHeader(csrfHeaderName, csrfValue);
		}
	}).done(function(response){
		console.log(response);
		/*$("#modalTitle").Text("Cart");
		$("#modalBady").Text("response");
		$("#myModal").modal();*/
		alert(response);
	}).fail(function(){
		console.log("fail");
		/*
		$("#modalTitle").Text("Cart");
		$("#modalBady").Text("Error while adding product to cart");
		$("#myModal").modal();*/
		alert("Fail");
	})
	
}