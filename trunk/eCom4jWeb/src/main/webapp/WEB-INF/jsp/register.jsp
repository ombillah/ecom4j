<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
   <div class="center_content" style="height:1300px">
   	<form:form action="register.do" method="post" modelAttribute="customer">
        <div class="register" >
          <div style="color:#504B4B; font-size:20px; font-weight:bold; margin:10px">Create My Account</div>
          <div style="height:30px">&nbsp</div>
          <div class="register_hdr_block">Account Information</div>
          <div style="height:20px">&nbsp</div>
          <div class="errormsg">
		    <form:errors path="emailAddress" element="li"/>
		    <form:errors path="confirmEmailAddress" element="li" />
		    <form:errors path="password" element="li"/>
		    <form:errors path="confirmPassword" element="li"/>
		    <form:errors path="secretQuestion" element="li"/>
		    <form:errors path="secretAnswer" element="li"/>
		    <form:errors  path="" element="li" />
		  </div>
          <table class="register_table">
          	<tr>
          		<td width="200" align="right">Email Address *</td>
          		<td width="250" align="left"><form:input path="emailAddress" size="50" cssStyle="background:#F0F0F0"  /></td>
          		<td width="350" align="left" style="padding-right:0px;"><span style="color:red;font-weight:bold">Note:</span> We do not rent or sell your email address to any outside third parties. </td>
          	</tr>
          	<tr>
          		<td width="200" align="right">Confirm Email Address *</td>
          		<td width="250" align="left"><form:input path="confirmEmailAddress" size="50" cssStyle="background:#F0F0F0"  /></td>
          		<td width="350" align="left"></td>
          	</tr>
          	<tr>
          		<td style="padding:5px"></td>
          	</tr>
          	<tr>
          		<td width="200" align="right">Password *</td>
          		<td width="250" align="left"><form:password path="password" size="50" cssStyle="background:#F0F0F0"  /></td>
          		<td width="350" align="left" style="padding-right:0px;"><span style="color:red;font-weight:bold">Note:</span> Password must contains at least 8 characters, 1 digit, 1 uppercase and 1 lowercase letter. </td>
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
          </table>
          <div style="height:30px">&nbsp</div>
          <div class="register_hdr_block">User Information</div>
          <div style="height:20px">&nbsp</div>
           <div class="errormsg">
		    <form:errors path="firstName" element="li"/>
		    <form:errors path="lastName" element="li" />
		    <form:errors path="address" element="li"/>
		    <form:errors path="city" element="li"/>
		    <form:errors path="state" element="li"/>
		    <form:errors path="zipCode" element="li"/>
		  </div>
          <table class="register_table">
          	<tr>
          		<td width="200" align="right">First Name *</td>
          		<td width="250" align="left"><form:input path="firstName" size="50" cssStyle="background:#F0F0F0"  /></td>
          		<td width="350" align="left"></td>
          	</tr>
          	<tr>
          		<td width="200" align="right">Last Name *</td>
          		<td width="250" align="left"><form:input path="lastName" size="50" cssStyle="background:#F0F0F0"  /></td>
          		<td width="350" align="left"></td>
          	</tr>
          	<tr>
          		<td width="200" align="right">Street Address *</td>
          		<td width="250" align="left"><form:input path="address" size="50" cssStyle="background:#F0F0F0"  /></td>
          		<td width="350" align="left"></td>
          	</tr>
          	<tr>
          		<td width="200" align="right">Address 2</td>
          		<td width="250" align="left"><form:input path="address2" size="50" cssStyle="background:#F0F0F0"  /></td>
          		<td width="350" align="left"></td>
          	</tr>
          	<tr>
          		<td width="200" align="right">City *</td>
          		<td width="250" align="left"><form:input path="city" size="50" cssStyle="background:#F0F0F0"  /></td>
          		<td width="350" align="left"></td>
          	</tr>
          	<tr>
          		<td width="200" align="right">State *</td>
          		<td width="250" align="left">
					<form:select path="state" cssStyle="background:#F0F0F0" >
					  <form:option  value="">Select a State</form:option>
					  <form:options items="${states }"/>
					</form:select> 
				</td>
          		<td width="350" align="left"></td>
          	</tr>
          	<tr>
          		<td width="200" align="right">Zip Code*</td>
          		<td width="250" align="left"><form:input path="zipCode" size="10"  cssStyle="background:#F0F0F0"  /></td>
          		<td width="350" align="left"></td>
          	</tr>
          	<tr>
          		<td width="200" align="right">Phone Number</td>
          		<td width="250" align="left"><form:input path="phoneNumber" size="20" cssStyle="background:#F0F0F0"  /></td>
          		<td width="350" align="left"></td>
          	</tr>
          </table>
          <div style="height:30px">&nbsp</div>
          <div class="register_hdr_block">Verification</div>
          <div style="height:20px">&nbsp</div>
          <div class="errormsg">
		    <form:errors path="captcha" element="li"/>
		  </div>
          <table class="register_table">
          	<tr>
          		<td width="800" align="center" colspan="3">Please type the code from the image below*</td>
          	</tr>
          	<tr>
          		<td width="800" align="center" colspan="3">${reCaptcha}</td>
          	</tr>
          	<tr>
          		<td width="800" align="center" colspan="3"><input type="submit" class="register_hdr_block" style="width:175px" value="Create My Account" /></td>
          	</tr>
          </table>
        </div>
	</form:form>
</div>