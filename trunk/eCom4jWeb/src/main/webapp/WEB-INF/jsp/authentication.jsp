<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script>


function switchToRegister() {
	$("#guest_checkout").css("display", "none");
	$("#register_box").css("display", "block");

}

function switchToGuest() {
	$("#register_box").css("display", "none");
	$("#guest_checkout").css("display", "block");

}

$(document).ready(function() {
	  $pathname = window.location.pathname;
	  if ($pathname.indexOf("checkout-register") > 0) {
		  $("#guest_checkout").css("display", "none");
		  $("#register_box").css("display", "block");
	  }
});
</script>

 <div class="center_content" style="height:600px;width:1000px;">
 	
 	<div style="font-size:20px; font-weight:bold; margin:10px">Checkout</div>
 	
 	<div class="login_existing" style="width:900px; height:25px; padding:10px 20px 10px 20px">
   		<div class="floatL">
   			<b>You have ${fn:length(sessionScope.shoppingCart.items)} items in your Cart</b> 
   			<a class="simple_anchor" href="viewcart.do" style="margin-left:10px">Modify your Cart</a> 
   		</div>
 		<div class="floatR">
   			<b>Order Total = </b>
   			<span class="price"> 
   				$ <fmt:formatNumber type="number" maxFractionDigits="3" value="${sessionScope.shoppingCart.total }" /> 
			</span> 
   		</div>
   	</div>
   	
 	<form action="j_spring_security_check" method="post"> 		
   		<div class="login_existing floatL">
   			<div class="register_hdr_block">Existing User</div>
   			<div style="height:20px"> &nbsp;</div>
   			<div style="padding-left:10px">
				<span class="standout_gray">If you already have an ecom4j account, please sign in below.</span>	
				<c:if test="${ param.invalidLogin eq true}">
       				<div class="errormsg" style="width:300px;margin:15px 0px 15px 0px">
       				 	<b>Invalid login. Please try again.</b>
					</div> 
   				</c:if>
				     	
				<div class="marginT">Email Address: </div>
				<div class="marginT"><input size="40" name="j_username" style="background:#F0F0F0"></div>
				 <div class="marginT">Password:</div>
				 <div class="marginT"><input size="40" name="j_password" style="background:#F0F0F0" type="password"></div>
				 <!-- <div class="marginT"><a href="/forgetPassword.do">Forgot your password?</a></div> -->
				 <br/>
				 <input type="submit"  class="gradient_button" value="Access My Account" />

	         </div>
   		</div>
   		</form>
   		<div class="login_existing floatL" style="width:550px;">
   			<div class="register_hdr_block">Guest / New User</div>
    		<div style="height:20px">&nbsp;</div>
   			<div id="guest_checkout" style="display:block">
   			  <form:form action="checkout-guest.do" method="post" modelAttribute="customer">
   				<div class="errormsg">
			    	<form:errors path="emailAddress" element="li"/>
			  	</div>
			  	<table class="register_table" style="width:550px">
		          	<tr>
		          		<td width="150" align="right">Email Address *</td>
		          		<td  align="left"><form:input path="emailAddress" size="50" cssStyle="background:#F0F0F0"  /></td>
		          	</tr>
		          	<tr>
		          		<td colspan="2" align="left"><span style="color:red;font-weight:bold">Note:</span> We do not rent or sell your email address to any outside third parties. </td>
		          	</tr>	          	
		          	<tr>
		          		<td align="center" colspan=2 >
		          			<input type="submit"  class="gradient_button" value="Checkout as guest" />
		          			<input type="button"  class="gradient_button" value="Create account" onClick="switchToRegister()" />
		          		</td>	          		
		          	</tr>	          	
	           </table>
	          </form:form>
   			</div>
   			<div id="register_box" style="display:none">
   				<form:form action="checkout-register.do" method="post" modelAttribute="customer">    		
    			
		          <div class="errormsg">
				    <form:errors path="emailAddress" element="li"/>
				    <form:errors path="confirmEmailAddress" element="li" />
				    <form:errors path="password" element="li"/>
				    <form:errors path="confirmPassword" element="li"/>
				    <form:errors path="secretQuestion" element="li"/>
				    <form:errors path="secretAnswer" element="li"/>
				    <form:errors  path="" element="li" />
				  </div>
		          <table class="register_table" style="width:550px">
		          	<tr>
		          		<td width="150" align="right">Email Address *</td>
		          		<td  align="left"><form:input path="emailAddress" size="50" cssStyle="background:#F0F0F0"  /></td>
		          	</tr>
		          	<tr>
		          		<td colspan="2" align="left"><span style="color:red;font-weight:bold">Note:</span> We do not rent or sell your email address to any outside third parties. </td>
		          	</tr>
		          	<tr>
		          		<td width="150" align="right">Confirm Email Address *</td>
		          		<td align="left"><form:input path="confirmEmailAddress" size="50" cssStyle="background:#F0F0F0"  /></td>
		          	</tr>
		          	<tr>
		          		<td style="padding:5px"></td>
		          	</tr>
		          	<tr>
		          		<td width="150" align="right">Password *</td>
		          		<td align="left"><form:password path="password" size="50" cssStyle="background:#F0F0F0"  /></td>
		          	</tr>
		          	<tr>
		          		<td colspan="2" width="350" align="left" style="padding-right:0px;"><span style="color:red;font-weight:bold">Note:</span> Password must contains at least 8 characters, 1 digit, 1 uppercase and 1 lowercase letter. </td>
		          	</tr>
		          	<tr>
		          		<td width="150" align="right">Confirm Password *</td>
		          		<td  align="left"><form:password path="confirmPassword" size="50" cssStyle="background:#F0F0F0"  /></td>
		          	</tr>
		          	<tr>
		          		<td style="padding:5px"></td>
		          	</tr>
		          	<tr>
		          		<td width="150" align="right">Security Question *</td>
		          		<td  align="left">
		          			<form:select path="secretQuestion" cssStyle="background:#F0F0F0" >
							  <form:option  value="What was your childhood nickname?">What was your childhood nickname?</form:option>
							  <form:option value="What street did you live on in third grade?">What street did you live on in third grade?</form:option>
							  <form:option value="In what city or town was your first job?">In what city or town was your first job?</form:option>
							  <form:option value="What school did you attend for sixth grade?">What school did you attend for sixth grade?</form:option>
							  <form:option value="In what city or town did your mother and father meet? ">In what city or town did your mother and father meet? </form:option>
							</form:select> 
						</td>
		          	</tr>
		          	<tr>
		          		<td colspan="2" width="350" align="left" style="padding-right:0px;"><span style="color:red;font-weight:bold">Note:</span> Security Question is used to reset password.</td>
		          	</tr>
		          	<tr>
		          		<td width="150" align="right">Security Answer *</td>
		          		<td  align="left"><form:input path="secretAnswer" size="50" cssStyle="background:#F0F0F0" /></td>
		          	</tr>
		          	<tr>
	          		<td align="center" colspan=2 >
	          			<input type="submit"  class="gradient_button" value="Create account" />
	          			<input type="button"  class="gradient_button" value="Checkout as guest" onClick="switchToGuest()" />
	          		</td>
	          		
	          	</tr>
		          </table>
		          
		        </form:form>
	    		
   			</div>
   			     
   		</div>  
</div>