/**
 * 
 */
/**
 * 
 */
 const shopname=document.querySelector("#name");
 const description=document.querySelector("#description");
 const address=document.querySelector("#address");
 const form=document.querySelectorAll("form")
 //check validate input
 function showError(input,message)
 {
     let parent=input.parentElement;
     let small=parent.querySelector('small');
   
    input.classList.add("error");
    small.innerHTML=message;
     
 }
 
 function showSuccess(input)
 {
     let parent=input.parentElement;
     let small=parent.querySelector('small');
   
    input.classList.remove("error");
    small.innerHTML='';
     
 }
 
 function checkEmptyError(ListInput) {
     let isEmptyError=false;
 
     ListInput.forEach(input => {
         input.value=input.value.trim()
 
         if(!input.value)
         {
             isEmptyError=true;
             showError(input,'Không được để trống');
         }
         else{
             showSuccess(input);
         }
     });
     return isEmptyError;
 }
 
 function checkLengthError(input,min,max){
     input.value=input.value.trim();
     if(input.value.length <min){
         showError(input,`Phải có ít nhất ${min} kí tự`);
         return true;
     }
     if(input.value.length >max){
         showError(input,`Độ dài không vượt quá ${max} kí tự`);
         return true;
     }
     
     return false;
 
 }
 
 
/*form.addEventListener("submit",function(e)
         {
 //		    e.preventDefault();
 
             let isEmptyError=checkEmptyError([oldPass,pass,repass])
             let isLenghtPassword=checkLengthError(pass,5,20);
             let isMatchpass=checkMatchPasswordError(pass,repass);
             if(isEmptyError  || isLenghtPassword|| isMatchpass)
             {
                 e.preventDefault();
             }
         })*/

    form.forEach(item => {
        item.addEventListener("submit",function(e) {
            //e.preventDefault();
 
             let isEmptyError=checkEmptyError([shopname,description,address])
             let isLenghtshopname=checkLengthError(shopname,5,100);
             let isLenghtdescription=checkLengthError(description,5,300);
             let isLenghtaddress=checkLengthError(address,5,100);


             if(isEmptyError  || isLenghtshopname|| isLenghtdescription ||isLenghtaddress)
             {
                 e.preventDefault();
             }
        })
    })
    
    
  //call api
  
 function addShop()
 {
	 fetch("/api/managerProduct/registerShop",
	 {
		 method:"post",
		 body:JSON.stringify({
			 "shopName":document.getElementById("nameShop").value,
			 "shopImage":document.getElementById("imageShop").value,
			 "shopDecription":document.getElementById("descriptionShop").value,
			 "shopAddress":document.getElementById("addressShop").value,
			 "user":{
				 "uid":document.getElementById("idUser").value
			 },
			 "isActive":1,
			 "isDelete":0
		 }),
		 headers: {
				"Content-Type": "application/json"
			}
	 }).then(function(resp)
	 {
		 return resp.json();
	 }).then(function(data)
	 {
		 console.log(data);
		 location.reload()
	 })
 }