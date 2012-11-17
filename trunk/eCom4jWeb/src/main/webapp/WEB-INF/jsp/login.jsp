<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 <div class="center_content" style="height:600px">
 	<div style="color:#504B4B; font-size:20px; font-weight:bold; margin:10px">Login to My Account</div>
 	<form action="j_spring_security_check" method="post">
 		<div class="login_box" >
 		
    		<div class="login_existing floatL">
    			<div class="register_hdr_block">Existing User</div>
    			<div style="height:20px">&nbsp;</div>
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
					 <div class="marginT"><a href="/forgetPassword.do">Forgot your password?</a></div>
					 <br/>
					 	<button type="submit" class="gray">
	                    	<span><span><span ><br/>Access My Account</span></span></span>
	                	</button>
		         </div>
    		</div>
    		
    		<div class="login_existing floatR">
    			<div class="register_hdr_block">New User</div>
    			<div style="height:20px">&nbsp;</div>
    			<div style="padding-left:10px">
					<span class="standout_gray">By creating a new account<br/> you will be able to:</span>		          	
					<ul  type="square" class="register_benefits">
						<li>Enjoy a quick checkout, by having your address and other information already on file.</li>
						<li>Check your order status.</li>
						<li>View your Order History to review previous transactions.</li>
						<li>Review purchased products.</li>
						<li>And much more...!</li>
						
					</ul>
					 <div class="margin center">
					 	<button type="button" class="gray" onclick="window.location='register.do'">
	                    	<span><span><span ><br/>Create My Account</span></span></span>
	                	</button>
					 </div>
		         </div>
    		</div>
    	</div>         
  	</form>
</div>