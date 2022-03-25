function addPerson(){
var str = $(".add-form").serialize();
$.ajax({
    type:"POST",
    data:str,
    url:"/save",
    success: function(result){
    console.log("Added new person successfully!");
     $("#peopleTable").html(result);
    }
});
}

function searchPerson(){
var keyword =$("#keyword").val();

$.ajax({
    type:"GET",
    url:"/search?keyword="+keyword,
    success: function(result){
        console.log(keyword);
        $("#peopleTable").html(result);
    }
});
}

function deleteRow(id){

$.ajax({
    url: 'http://localhost:8084/delete/'+id,
    type: 'DELETE',
    success: function(result) {
        $("#peopleTable").html(result);
    }
});
}
