function dealerlist()
{
	  var list = "";
      list += "<ul>";
      list += "<li>dealerDetails<\/li>";
      list += "<li>dealerBillDetails<\/li>";
      list += "<\/ul>";
      $(".menutwo")[0].innerHTML = list;
      $(".menutwo").toggle();
  

}