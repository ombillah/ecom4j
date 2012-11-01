<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="center_content">
	<div class="register">
		<p>
			Welcome!!<br /> Start you FREE membership now
		</p>
		<p>Just fill out this simple form and click 'Continue Checkout'</p>
		<p>to start taking advantage of our services.</p>
		<br />
		<c:if test="${!empty fieldErrors || !empty actionErrors}">
			<div class="errormsg">
				<ul>
					<c:forEach items="${fieldErrors}" var="fieldError">
						<c:forEach items="${fieldError.value}" var="error">
							<li>${error}</li>
						</c:forEach>
					</c:forEach>
					<c:forEach items="${actionErrors}" var="actionError">
						<li>${actionError}</li>
					</c:forEach>
				</ul>
			</div>
		</c:if>
		<form action="preCheckoutRegister.do" method="post">
			User Name:<br /> <input size="30" name="userName" /><br /> Password:<br />
			<input type="password" size="30" name="password" /><br /> <span
				class="passwordlength">password must be at least 6 characters</span><br />
			Confirm Password:<br /> <input type="password" size="30"
				name="confirmPassword" /> <span class="passwordlength"><br />
				password must be at least 6 characters</span><br /> First Name:<br /> <input
				size="30" name="firstName" /><br /> Last Name:<br /> <input
				size="30" name="lastName" /><br /> Email Address:<br />
			<iput size="40" name="email" />
			<br />
			<br />
			<br /> <u><strong><em>Shipping Address:</em></strong></u><br />
			<br /> Street Address:<br /> <input size="60" name="shipStreet" /><br />
			City:<br /> <input size="20" name="shipCity" /><br />
			State/Province:<br /> <input size="20" name="shipState" /><br /> Zip
			Code:<br /> <input size="10" name="shipZip" /><br /> <br /> <u><strong><em>Billing
						Address:</em></strong></u><br /> Street Address:<br /> <input size="60"
				name="bilStreet" /><br /> City:<br /> <input size="20"
				name="bilCity" /><br /> State/Province:<br /> <input size="10"
				name="bilState" /><br /> Zip Code:<br /> <input size="10"
				name="bilZip" /><br />
			<br /> <input type="submit" value="Continue Chekout" align="left" /><br />
		</form>
	</div>
	<div class="precheckoutlogin">
		<span class="alreadyMember">Already a member?<br /> <br />
		</span>
		<div id="authentication">
			<form action="preCheckoutLogin.do" method="post">
				<strong>User Name:<br />
				</strong> <input name="uname" /><br /> <strong>Password:</strong><br /> <input
					type="password" name="pwd" /><br /> <a href="forgetPassword.do">forgot
					your password?</a><br />
				<br /> <input type="submit" align="left" value="Continue Chekout" />
			</form>
		</div>
	</div>
</div>