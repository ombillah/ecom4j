<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
  <script>
  
  		function displayUpdateForm(display, hide1, hide2) {
			var displayDiv = $("#" + display);
			if (display == 'update_security') {
				$("#secretAnswer").val("");
			}
			$(displayDiv).css('display', 'block');
			$("#" + hide1).css('display', 'none');
			$("#" + hide2).css('display', 'none');
		}
		
		function updateFormAction(url) {
			var form = document.forms[0];			
			form.action = url;
			form.submit();
		}
		
		function updatePassword() {
			var height = $(".center_content").css("height");
			$("#ajax_box").css("height", height);
			$("#ajax_box").show();

			// get the form values
			var oldPassword = $('#oldPassword').val();
			var password = $('#password').val();
			var confirmPassword = $('#confirmPassword').val();
			var recaptcha_challenge_field = $('#recaptcha_challenge_field').val();
			var recaptcha_response_field = $('#recaptcha_response_field').val();
			$.ajax({
			    type: "POST",
			    url: "updatepassword.do",
			    data: "password=" + password 
			        + "&confirmPassword=" + confirmPassword
			        + "&oldPassword=" + oldPassword
			    	+ "&recaptcha_challenge_field=" + recaptcha_challenge_field
			    	+ "&recaptcha_response_field=" + recaptcha_response_field,
			    success: function(response){
			    	$('#pasword_error').empty();
			    	$('#success_msg').empty();
			        var message = "";
			       if (response.validation != null) {
			    	   $.each(response.validation, function(key, value) {			    		      
			    			message += "<li>" + value + "</li>"; 
			    		 });
			    	   $('#pasword_error').html(message);
			    	   Recaptcha.reload();
			    	   $("#ajax_box").hide();
			       }
			       if (response.success != null) {
			    	   setTimeout(function() {
			    		   $('#success_msg').html(response.success);
				    	   $('#update_password input[type="text"]').val('');
					       $('#update_password input[type="password"]').val('');
				    	   $("#ajax_box").hide();
				    	   $('#update_password').toggle();
				    	   $(window).scrollTop($('#success_msg').position().top);
				    	}, 1000);    
			       }
			       
			    },
			    error: function(e){
			    	$('#password_error').html("update failed. please try again!");
			        Recaptcha.reload();
			        $("#ajax_box").hide();
			    }
			});
		}
		
		function updateProfile() {
			var height = $(".center_content").css("height");
			$("#ajax_box").css("height", height);
			$("#ajax_box").show();
			$('#success_msg').html("");
			// get the form values
			var emailAddress = $('#emailAddress').val();
			var oldEmailAddress = $('#oldEmailAddress').val();
			var firstName = $('#firstName').val();
			var lastName = $('#lastName').val();
			var address = $('#address').val();
			var address2 = $('#address2').val();
			var city = $('#city').val();
			var state = $('#state').val();
			var zipCode = $('#zipCode').val();
			var phoneNumber = $('#phoneNumber').val();
			 
			$.ajax({
			    type: "POST",
			    url: "updateprofile.do",
			    data: "emailAddress=" + emailAddress
			    	+ "&oldEmailAddress=" + oldEmailAddress 
			        + "&firstName=" + firstName
			        + "&lastName=" + lastName
			        + "&address=" + address
			        + "&address2=" + address2
			        + "&city=" + city
			        + "&state=" + state
			        + "&zipCode=" + zipCode
			        + "&phoneNumber=" + phoneNumber,
			    success: function(response){
			    	$('#profile_error').empty();
			    	$('#success_msg').empty();
			        var message = "";
			       if (response.validation != null) {
			    	   $.each(response.validation, function(key, value) {			    		      
			    			message += "<li>" + value + "</li>"; 
			    		 });
			    	   $('#profile_error').html(message);
			    	   $("#ajax_box").hide();
			       }
			       if (response.success != null) {
			    	   $('#success_msg').html(response.success);
			    	   $('#emailAddressLabel').html(emailAddress);
			    	   $('#firstNameLabel').html(firstName);
			    	   $('#lastNameLabel').html(lastName);
			    	   $('#addressLabel').html(address);
			    	   $('#address2Label').html(address2);
			    	   $('#cityLabel').html(city);
			    	   $('#stateLabel').html(state);
			    	   $('#zipCodeLabel').html(zipCode);
			    	   $('#phoneNumberLabel').html(phoneNumber);
			    	   
			    	   setTimeout(function() {
				    	   $("#ajax_box").hide();
				    	   $('#update_information').toggle();
				    	   $(window).scrollTop($('#success_msg').position().top);
				    	}, 1000);
			       }
			       
			       
			    },
			    error: function(e){
			    	$('#profile_error').html("update failed. please try again!");
			        $("#ajax_box").hide();
			    }
			});
		}
		
		function updateSecretAnswer() {
			var height = $(".center_content").css("height");
			$("#ajax_box").css("height", height);
			$("#ajax_box").show();

			// get the form values
			var secretAnswer = $('#secretAnswer').val();
			var secretQuestion = $('#secretQuestion').val();
			$.ajax({
			    type: "POST",
			    url: "updatesecurityquestion.do",
			    data: "secretAnswer=" + secretAnswer
			    + "&secretQuestion=" + secretQuestion,
			    success: function(response){
			    	$('#sec_answer_error').empty();
			    	$('#success_msg').empty();
			        var message = "";
			       if (response.validation != null) {
			    	   $.each(response.validation, function(key, value) {			    		      
			    			message += "<li>" + value + "</li>"; 
			    		 });
			    	   $('#sec_answer_error').html(message);
			    	   $("#ajax_box").hide();
			       }
			       if (response.success != null) {
			    	   setTimeout(function() {
			    		   $('#success_msg').html(response.success);
				    	   $("#ajax_box").hide();
				    	   $('#update_security').toggle();
				    	   $(window).scrollTop($('#success_msg').position().top);
				    	}, 1000);    
			       }
			       
			    },
			    error: function(e){
			    	$('#sec_answer_error').html("update failed. please try again!");
			        Recaptcha.reload();
			        $("#ajax_box").hide();
			    }
			});
		}
   </script>

   <div class="center_content">
   		  <div id="ajax_box" class="ajax_box" style="display:none">
   		  	<div class="ajax_box" style="background: url(images/ajax-loader.gif) no-repeat center center;"></div>
   		  </div>
	  	  <div style="color:#504B4B; font-size:20px; font-weight:bold; margin:10px">My Account Page</div>
	      <div style="height:30px">&nbsp</div>
	  	  <div class="register_hdr_block">Account Information</div>
	      <div style="height:20px">&nbsp</div>
	      
	      <div style="height:250px; width:550px; margin-left:20px" >
	      	<div class="floatL">
	      	   <div id = "success_msg" class="errormsg">
		    	
			  </div>
		      <table class="register_table " style="width:350px; border-spacing:0;border-collapse:collapse; margin-top:10px" border=1>
		      		<tr>
		          		<th colspan="2" align="left" style="padding:5px"><span style="color:#504B4B; font-size:14px; font-weight:bold;">Personal Information</span></td>
		          	</tr>
		          	<tr>
		          		<td width="125" align="left">Email Address:</td>
		          		<td width="225" align="left"><label id="emailAddressLabel"><sec:authentication property="principal.customer.emailAddress" /></label></td>
		          	</tr>
		          	<tr>
		          		<td width="125" align="left">Full Name:</td>
		          		<td width="225" align="left"><label id="firstNameLabel"><sec:authentication property="principal.customer.firstName" /> <sec:authentication property="principal.customer.lastName" /></label></td>
		          	</tr>
		          	<tr>
		          		<td width="125" align="left">Address 1:</td>
		          		<td width="225" align="left"><label id="lastNameLabel"><sec:authentication property="principal.customer.address" /></label></td>
		          	</tr>
		          	<tr>
		          		<td width="125" align="left">Address 2:</td>
		          		<td width="225" align="left"><label id="address2Label"><sec:authentication property="principal.customer.address2" /></label></td>
		          	</tr>
		          	<tr>
		          		<td width="125" align="left">City:</td>
		          		<td width="225" align="left"><label id="cityLabel"><sec:authentication property="principal.customer.city" /></label></td>
		          	</tr>
		          	<tr>
		          		<td width="125" align="left">State:</td>
		          		<td width="225" align="left"><label id="stateLabel"><sec:authentication property="principal.customer.state" /></label></td>
		          	</tr>
		          	<tr>
		          		<td width="125" align="left">Zip Code:</td>
		          		<td width="225" align="left"><label id="zipCodeLabel"><sec:authentication property="principal.customer.zipCode" /></label></td>
		          	</tr>
		          	<tr>
		          		<td width="125" align="left">Phone Number:</td>
		          		<td width="225" align="left"><label id="phoneNumberLabel"><sec:authentication property="principal.customer.phoneNumber" /></label></td>
		          	</tr>
		      </table>
	     	</div>
	     	<div class="floatR" style="height:200px; margin-left:25px">
	     		<div style="height:40px">&nbsp</div>
	     		<div style="color:#504B4B; font-size:14px; font-weight:bold;">Account Options</div>
	     		<div style="height:20px">&nbsp</div>
	     		<div><a href="#" class="link" onClick="displayUpdateForm('update_information', 'update_password', 'update_security')">Change Personal Information</a></div>
	     		<div><a href="#" class="link" onClick="displayUpdateForm('update_password', 'update_information', 'update_security')">Change Password</a></div>
	     		<div><a href="#" class="link" onClick="displayUpdateForm('update_security', 'update_information', 'update_password')">Change Security Question</a></div>
	     	</div>
	   </div>
	   <div style="height:20px">&nbsp</div>
	    <hr class="divider" />
	   <div style="height:20px">&nbsp</div>
	   
		   <div id="update_information" style="display:none">
		   <form:form action="updateprofile.do" method="post" modelAttribute="customer">
		      <div id = "profile_error" class="errormsg">
		    	
			  </div>
	          <table class="register_table" style="width:550px">
	          	<tr>
	          		<td width="200" align="right">Email Address *</td>
	          		<td width="250" align="left">
	          			<form:input path="emailAddress" size="50" cssStyle="background:#F0F0F0" />
	          			<form:hidden path="oldEmailAddress" size="50" value="${customer.emailAddress}" />
	          		</td>
	          	</tr>
	          	<tr>
	          		<td width="200" align="right">First Name *</td>
	          		<td width="250" align="left"><form:input path="firstName" size="50" cssStyle="background:#F0F0F0"  /></td>
	          	</tr>
	          	<tr>
	          		<td width="200" align="right">Last Name *</td>
	          		<td width="250" align="left"><form:input path="lastName" size="50" cssStyle="background:#F0F0F0"  /></td>
	          	</tr>
	          	<tr>
	          		<td width="200" align="right">Street Address *</td>
	          		<td width="250" align="left"><form:input path="address" size="50" cssStyle="background:#F0F0F0"  /></td>
	          	</tr>
	          	<tr>
	          		<td width="200" align="right">Address 2</td>
	          		<td width="250" align="left"><form:input path="address2" size="50" cssStyle="background:#F0F0F0"  /></td>
	          	</tr>
	          	<tr>
	          		<td width="200" align="right">City *</td>
	          		<td width="250" align="left"><form:input path="city" size="50" cssStyle="background:#F0F0F0"  /></td>
	          	</tr>
	          	<tr>
	          		<td width="200" align="right">State *</td>
	          		<td width="250" align="left">
						<form:select path="state" cssStyle="background:#F0F0F0" >
						  <form:option  value="">Select a State</form:option>
						  <form:options items="${states }"/>
						</form:select> 
					</td>
	          	</tr>
	          	<tr>
	          		<td width="200" align="right">Zip Code *</td>
	          		<td width="250" align="left"><form:input path="zipCode" size="10"  cssStyle="background:#F0F0F0"  /></td>
	          	</tr>
	          	<tr>
	          		<td width="200" align="right">Phone Number</td>
	          		<td width="250" align="left"><form:input path="phoneNumber" size="20" cssStyle="background:#F0F0F0"  /></td>
	          	</tr>
	          	<tr>
	    			<td width="800" align="center" colspan="2"><input type="button"  class="gradient_button" value="Update" onClick="updateProfile()" /></td>
	    		</tr>
	          </table>
	          </form:form>
	      </div>
	      <div id="update_password" style="display:none">
	      	 <form:form action="updatepassword.do" method="post" modelAttribute="customer">
		      <div id="pasword_error" class="errormsg">
		      	
			  </div>
	          <table class="register_table">
	          	<tr>
	          		<td width="200" align="right">Current Password *</td>
	          		<td width="250" align="left"><form:password path="oldPassword" size="50" cssStyle="background:#F0F0F0"  /></td>
	          		<td width="350" align="left" ></td>
	          	</tr>
	          	<tr>
	          		<td width="200" align="right">Password *</td>
	          		<td width="250" align="left"><form:password path="password" size="50" cssStyle="background:#F0F0F0"  /></td>
	          		<td width="350" align="left" style="padding-right:0px;"><span style="color:red;font-weight:bold">Note:</span> Password must be at least 8 characters long, and contains one digit and one number. </td>
	          	</tr>
	          	<tr>
	          		<td width="200" align="right">Confirm Password *</td>
	          		<td width="250" align="left"><form:password path="confirmPassword" size="50" cssStyle="background:#F0F0F0"  /></td>
	          		<td width="350" align="left" ></td>
	          	</tr>
	          	<tr>
	          		<td style="padding:5px"></td>
	          	</tr>
	          	<tr>
	          		<td width="200" align="right"></td>
	          		<td width="600" align="left" colspan="2">Please type the code from the image below*</td>
	          	</tr>
	          	<tr>
	          		<td width="200" align="right"></td>
	          		<td width="600" align="left" colspan="2">${reCaptcha}</td>
	          	</tr>
	          	<tr>
	          		<td width="200" align="right"></td>
	          		<td width="300" align="center"><input type="button" class="gradient_button" value="Update" onClick="updatePassword()"/></td>
	          		<td width="200" align="right"></td>
	          	</tr>
	          </table>
	        </form:form>
	      </div>
	      <div id="update_security" style="display:none">
	      	<form:form action="updatesecurityquestion.do" method="post" modelAttribute="customer">
		      <div id="sec_answer_error" class="errormsg">
		      	
			  </div>
	          <table class="register_table">
	          	<tr>
	          		<td width="200" align="right">Security Question *</td>
	          		<td width="250" align="left">
	          			<form:select path="secretQuestion" cssStyle="background:#F0F0F0" >
						  <form:option  value="What was your childhood nickname?">What was your childhood nickname?</form:option>
						  <form:option value="What street did you live on in third grade?">What street did you live on in third grade?</form:option>
						  <form:option value="In what city or town was your first job?">In what city or town was your first job?</form:option>
						  <form:option value="What school did you attend for sixth grade?">What school did you attend for sixth grade?</form:option>
						  <form:option value="In what city or town did your mother and father meet? ">In what city or town did your mother and father meet? </form:option>
						</form:select> 
					</td>
	          		<td width="350" align="left" style="padding-right:0px;"><span style="color:red;font-weight:bold">Note:</span> Security Question is used to reset password.</td>
	          	</tr>
	          	<tr>
	          		<td width="200" align="right">Security Answer *</td>
	          		<td width="250" align="left"><form:input path="secretAnswer" size="50" cssStyle="background:#F0F0F0" /></td>
	          		<td width="350" align="left" ></td>
	          	</tr>
	          	<tr>
	         		<td width="800" align="center" colspan="2"><input type="button" class="gradient_button" value="Update" onClick="updateSecretAnswer()" /></td>
	         	</tr>
	          </table>
	          </form:form>
	      </div>
      
  </div>