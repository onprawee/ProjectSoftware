$(document).ready(function(){
		
	$(".remove").on("click", function(evt) {
		evt.preventDefault();
		removeFromCart($(this));
	});
	updateTotal();

});
//remove
function removeFromCart(link){
	console.log("remove");
	url = link.attr("href");
	
	$.ajax({
		type : "POST",
		url: url,
		beforeSend : function(xhr){
			xhr.setRequestHeader(csrfHeaderName, csrfValue);
		}
	}).done(function(response){
		console.log(response);
		alert(response);
		window.location.reload();
		
		rowNumber = link.attr("rowNumber");
		removeMenu(rowNumber)
		updateTotal();
		/*if(response.includes("removed")){
			$("remove").on(function(e){
				rowNumber = link.attr("rowNumber");
				removeMenu(rowNumber)
				updateTotal();
			});
		}	*/	 
	}).fail(function(){
		console.log("fail");
		alert("Delete Fail");
	});
}

//remove
function removeMenu(){
	rowId = $("row" + rowNumber);
	$("#" + rowId).remove();	
}