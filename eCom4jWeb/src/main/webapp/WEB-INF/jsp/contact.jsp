<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <div class="center_content">
      <div class="center_title_bar">Contact Us</div>
      <div class="prod_box_big">
        <div class="top_prod_box_big"></div>
        <div class="center_prod_box_big">
          <div class="contact_form">
           <form action="send.do" method="post">
	            <div class="form_row">
	              <label class="contact"><strong>Name:</strong></label><br/>
	              <input name="name" cssClass="contact_input" />
	            </div>
	            <div class="form_row">
	              <label class="contact"><strong>Email:</strong></label><br/>
	              <input name="email" cssClass="contact_input" />
	            </div>
	            <div class="form_row">
	              <label class="contact"><strong>Message:</strong></label><br/>
	              <textarea rows="5" cols="20" name="message" cssClass="contact_textarea" >
	              </textarea>
	            </div>
	            <div class="form_row"> <input type="submit" value="send" align="center"/></div>
           </form> 
          </div>
        </div>
      </div>
 </div>