<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <div class="center_content">
	  <p class="alreadyMember"><strong>Congratulations, You Order has been Confirmed.</strong></p>
	  <p>&nbsp;</p>
	  <div class="orderHistory" >
        <p><strong><span class="alreadyMember">Orders History</span>:</strong></p>
	    <br/>
        <table width="96%" border="1" cellspacing="1">
          <tr bgcolor="#02779D">
            <th width="11%">ORDERID</th>
            <th>STATUS</th>
            <th>ORDER DATE </th>
            <th>Total Price</th>
          </tr>
          <c:forEach items="${orderHistory}" var="order">
            <tr>
              <td><c:out value="${orderID}" /></td>
              <td><c:out value="${status}" /></td>
              <td><c:out value="${orderDate}" /></td>
              <td>$
                <c:out value="${totalCost}" /></td>
            </tr>
          </c:forEach>
        </table>
      </div>
 </div>