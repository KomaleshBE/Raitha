           $(document).ready(function(){
                  $("#buttonCancel").on("click",function(){
                   window.location=moduleURL;
             });
             $("#fileImage").change(function(){
             fileSize=this.files[0].size;             
             if(fileSize>1048576){
               this.setCustomValidity("you must choose an image less than 1MB!");
               this.reportValidity();
             }
             else{
             this.setCustomValidity("");
             showImageThumbnail(this);
             }           
                          
             });
           });
  
  function showImageThumbnail(fileInput){
         var file=fileInput.files[0];
         var reader=new FileReader();
         reader.onload=function(e){
         $("#thumbnail").attr("src", e.target.result);
         };
         
         reader.readAsDataURL(file);
         }
         function checkPasswordMatch(confirmPassword){
			 if(confirmPassword.value!=$("password").val()){
				 confirmPassword.setCustomValidity("passwords do not match!");
				 
			 }else{
				 confirmPassword.setCustomValidity("")
			 }
		 }