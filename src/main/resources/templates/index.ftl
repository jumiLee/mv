<!DOCTYPE html>
<html>
<head>
	<title>Test Page</title>
</head>
<body>
  <section>
    <header><h1>MV System TEST PAGE</h1></header>
  </section>
    <main>
	<#assign user_account = 15>
	user_account : ${user_account} <br>
	[공통] <br>
	<a href="./selectUserAttend.do?user_account=${user_account}" target="_blank">출석조회 (unity) </a> <br>
	<a href="./userAttend.do?user_account=${user_account}" target="_blank">출석조회 (web) </a> <br>
	<a href="./registerUserAttend.do?user_account=${user_account}" target="_blank">출석등록</a> <br>
	
	<a href="./register.do?email=1234&pwd=1234&nickname=1234" target="_blank">사용자 생성</a> <br>
	<a href="./loginChk.do?email=&pwd=" target="_blank">로그인 체크</a> <br>
	<a href="./getUserInitialInfo.do?user_account=${user_account}" target="_blank">사용자 로그인 후 정보 조회  </a> <br>
	
	
	<a href="./createUserCharacter.do?user_account=${user_account}" target="_blank">캐릭터 생성</a> <br>
	<a href="./selectCharacterList.do?user_account=${user_account}" target="_blank">캐릭터 조회</a> <br>
	<a href="selectCharacterShapeInfo.do?user_account=${user_account}&char_id=1&char_sn=1" target="_blank">캐릭터 shape 조회</a> <br>
	<a href="./modifyUserCharacter.do?user_account=${user_account}&char_id=&user_char_sn=&char_cust_info=" target="_blank">캐릭터 정보 수정 </a> <br>
	<a href="./changeMainCharacter.do?user_account=${user_account}&char_id=&user_char_sn=" target="_blank">메인캐릭터변경 </a> <br>
	<a href="./modifyCharacterShape.do?user_account=${user_account}&char_id=1&user_char_sn=1&char_shape_info=" target="_blank">캐릭터 Shape 정보 수정 </a> <br>
	<a href="./saveUserMap.do?user_account=${user_account}&char_id=1&user_char_sn=1&map_type=1&map_data=" target="_blank">캐릭터 Map Data저장  </a> <br>
	<a href="./selectUserMap.do?user_account=${user_account}&char_id=1&user_char_sn=1&map_type=1" target="_blank">캐릭터 Map Data 조회   </a> <br>
	
	<a href="./selectItemLisByCategory.do?user_account=${user_account}&item_category=10" target="_blank">상점 아이템 조회</a> <br>
	<a href="./buyAndEquipItem.do?user_account=${user_account}&item_id=&char_id=&user_char_sn=&item_id_array=" target="_blank">상점 아이템 구매 </a> <br>
	<a href="./getMyItem.do?user_account=${user_account}&item_id=&char_id=&user_char_sn=&item_id_array=" target="_blank">나의 아이템조회(장착
	포함) </a> <br>
	<a href="./equipItem.do?job_code=&user_account=${user_account}&char_id=&user_char_sn=&item_id=&item_uniqueID=" target="_blank">아이템 장착  </a> <br>
	<a href="./equipItemAll.do?job_code=&user_account=${user_account}&char_id=&user_char_sn=&item_uniqueID_ary=&item_category=&item_type=" target="_blank">아이템 장착(일괄) </a> <br>
	
	<a href="./selectReceiveBox.do?job_code=&user_account=${user_account}" target="_blank">메세지 박스 조회 </a> <br>
	<a href="./mgmtReceiveBox.do?job_code=1&user_account=${user_account}&receive_sn=" target="_blank">메세지 박스 받기  </a> <br>
	
	
	<a href="./geMyFriendList.do?user_account=${user_account}" target="_blank">친구 리스트 조회 </a> <br>
	
	<a href="./selectCashList.do?user_account=${user_account}&device_type=1&payment_type=1" target="_blank">Cash 리스트 조회 </a> <br>
	
	<a href="./requestPayment.do?user_account=${user_account}&&cash_id=1&device_type=1&payment_type=1" target="_blank">결제요청  </a> <br>
	<a href="./updatePayment.do?user_account=${user_account}&&cash_id=1&trade_no=5&order_no=5&trade_res_cd=0&trade_res_msg=sucess&trade_res_key=5ONLYGODWILLMAKEAWAY" target="_blank">결제결과 업데이트 </a> <br>
	<!-- 
	<a href="./user_main.jsp?user_account=${user_account}" target="_blank">Main</a> <br>
	<a href="./ma_error.jsp" target="_blank">Error page</a> <br>
	
	
	<form name="f" action="user_char_create.jsp" method="post">
		user_account<input type="text" name="user_account">
		user_nickname<input type="text" name="user_nickname">
		mon_id<input type="text" name="mon_id">
		<input type="submit">
	</form> 
	 -->
	</main>
