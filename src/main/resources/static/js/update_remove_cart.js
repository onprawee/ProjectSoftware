$(document).ready(function(){
	$(".minusButton").on("click", function(evt) {
		evt.preventDefault();
		decreaseQuantity($(this));
	});
	
	$(".plusButton").on("click", function(evt) {
		evt.preventDefault();
		increaseQuantity($(this));
	});

});


// Minus
function decreaseQuantity(link){
		
		menuId = link.attr("pid");
		qtyInput = $("#quantity" + menuId);
		
		newQty = parseInt(qtyInput.val()) - 1;
		if(newQty <= 10){
			qtyInput.val(newQty);
			updateQuantity(menuId,qtyInput.val());
		} 
}
// Plus
function increaseQuantity(link){
		menuId = link.attr("pid");
		qtyInput = $("#quantity" + menuId);
		
		newQty = parseInt(qtyInput.val()) + 1;
		if(newQty <= 10){
			qtyInput.val(newQty);
			updateQuantity(menuId,qtyInput.val());
		}
}
// Update Quantity
function updateQuantity(menuId,quantity){
	url = contextPath + "Cart/update/" + menuId + "/" + quantity;
	console.log(quantity);
	//url = link.attr("href");
	$.ajax({
		type : "POST",
		url: url,
		beforeSend : function(xhr){
			xhr.setRequestHeader(csrfHeaderName, csrfValue);
		}
	}).done(function(newsubtotal){
		updateSubtotal(newsubtotal,menuId);
		updateTotal();
		/*console.log(newsubtotal);
		alert(response);*/
	}).fail(function(){
		console.log("fail");
		alert("Update Fail");
	});
}
//update Suptotal 
function updateSubtotal(newSubtotal,menuId){
	$("#subtotal" + menuId).text(newSubtotal);
}
//Update Total 
function updateTotal(){
	total = 0;
	
	$(".menuSubtotal").each(function(index, element){
		total = total + parseFloat(element.innerHTML);
	});
	
	$("#totalAmount").text("ราคารวมทั้งหมด " + total + " บาท");
}

