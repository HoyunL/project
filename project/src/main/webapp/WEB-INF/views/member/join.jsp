<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, user-scalable=yes">
    <meta name="format-detection" content="telephone=no, address=no, email=no">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <title>회원가입</title>
    <link rel="stylesheet" href="/project/resources/css/reset.css"/>
    <link rel="stylesheet" href="/project/resources/css/contents.css"/>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
	
    <script>
    	function goSave() {
    		if($('#email').val().trim() == '') {
    			alert('이메일을 입력해 주세요');
    			$('#email').focus();
    			return;
    		}
    		var reg_email = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
    		if(!reg_email.test($("#email").val())) {           
    			alert('이메일 형식이 올바르지 않습니다.');
    			return;
    		}
    		var isCon = true; //★★★★★★★★★★★★★★★★
    		$.ajax({
				url  : 'emailDupCheck.do',
				data : {email:$("#email").val()},
				async: false, // 순차적으로 진행.
				success:function(res) {
						if(res == 'true') {
							alert('입력한 이메일이 중복되었습니다. 다른 이메일을 입력해 주세요.');
							$("#email").val('');
							$("#email").focus();
							isCon = false; //★★★★★★★★★★★★★★★★
							//return; 의미없음.
						} 
					}
				});
    		if(!isCon) return; //★★★★★★★★★★★★★★★★
	    	if($("#pwd").val().trim() == '') {
				alert('비밀번호을 입력해 주세요');
				$('#pwd').focus();
				return;
			}
	    	var reg_pwd = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/;
	    	if( !reg_pwd.test($("#pwd").val()) ) {
	    		alert("비밀번호는 영문,숫자, 특수문자 조합으로 8자 이상 입력하세요.");
	    		return;
	    	}
	    	if($("#pwd").val() != $("#pwd_check").val()) {
	    		alert('비밀번호를 확인해 주세요');
	    		$('#pwd_check').focus();
	    		return;
	    	}
	    	if($("#name").val().trim() == '') {
	    		alert('이름를 확인해 주세요');
	    		$('#name').focus();
	    		return;
	    	}
	    	
			$("#frm").submit();
    	}
    	$(function () {
			$('#DupCheckBtn').click(function() {
				if($("#email").val().trim() == '' ) {
					alert('이메일을 입력해주세요');
					$('#email').focus();
				} else {
					$.ajax({
						url  : 'emailDupCheck.do',
						data : {email:$("#email").val()},
						success:function(res) {
							if(res == 'true') {
								alert('사용 불가');
							} else {
								alert('사용 가능');
						}
					}
				})
			}
		});
		$( "#birthday" ).datepicker({
                dateFormat: 'yy-mm-dd' //Input Display Format 변경
                ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
                ,showMonthAfterYear:true //년도 먼저 나오고, 뒤에 월 표시
                ,changeYear: true //콤보박스에서 년 선택 가능
                ,changeMonth: true //콤보박스에서 월 선택 가능                
                ,buttonImageOnly: true //기본 버튼의 회색 부분을 없애고, 이미지만 보이게 함
                ,buttonText: "선택" //버튼에 마우스 갖다 댔을 때 표시되는 텍스트                
                ,yearSuffix: "년" //달력의 년도 부분 뒤에 붙는 텍스트
                ,monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'] //달력의 월 부분 텍스트
                ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip 텍스트
                ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 부분 텍스트
                ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 부분 Tooltip 텍스트
                ,minDate: "-1M" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
                ,maxDate: "+1M" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)                
            });                    
	})
    </script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
    function zipcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    //document.getElementById("sample6_extraAddress").value = extraAddr;
                	addr += extraAddr;
                } 

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('zipcode').value = data.zonecode;
                document.getElementById("addr1").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("addr2").focus();
            }
        }).open();
    }
	</script>
</head>
<body>
    
        <div class="sub">
            <div class="size">
                <h3 class="sub_title">회원가입</h3>
                <form name="frm" id="frm" action="join.do" method="post" enctype="multipart/form-data">
                <table class="board_write">
                    <caption>회원가입</caption>
                    <colgroup>
                        <col width="20%" />
                        <col width="*" />
                    </colgroup>
                    <tbody>
                        <tr>
                            <th>*이메일</th>
                            <td>
                                <input type="text" name="email" id="email" class="inNextBtn" style="float:left;">
                                <span class="email_check"><a href="javascript:;"  class="btn bgGray" style="float:left; width:auto; clear:none;" id="DupCheckBtn">중복확인</a></span>
                            </td>
                        </tr>
                        <tr>
                            <th>*비밀번호</th>
                            <td><input type="password" name="pwd" id="pwd" style="float:left;"> <span class="ptxt">비밀번호는 숫자, 영문 조합으로 8자 이상으로 입력해주세요.</span> </td>
                        </tr>
                        <tr>
                            <th>*비밀번호<span>확인</span></th>
                            <td><input type="password" name="pwd_check" id="pwd_check" style="float:left;"></td>
                        </tr>
                        <tr>
                            <th>*이름</th>
                            <td><input type="text" name="name" id="name" style="float:left;"> </td>
                        </tr>
                        <tr>
                            <th>*성별</th>
                            <td>
                            <select name="gender" id="gender">
                            <option value="1">남성</option>
                            <option value="2">여성</option>
                            </select> 
                            </td>
                        </tr>
                        <tr>
                            <th>*생년월일</th>
                            <td><input type="text" name="birthday" id="birthday" style="float:left;" autocomplete="off"> </td>
                        </tr>
                        <tr>
                            <th>*휴대폰번호</th>
                            <td>
                                <input type="text" name="hp" id="hp" value=""  maxlength="15" style="float:left;">
                            </td>
                        </tr>
                        <tr>
                            <th rowspan="3">주소</th>
                            <td>
                                <input type="text" name="zipcode" id="zipcode" class="inNextBtn" style="float:left;" readonly>
                                <span class="email_check"><a href="javascript:zipcode();"  class="btn bgGray" style="float:left; width:auto; clear:none;">우편번호</a></span>
                            </td>
                            <tr>
	                            <td>
	                            	<input type="text" name="addr1" id="addr1" style="width: 80%" readonly="readonly">
	                            </td>
	                       </tr>
	                       <tr>
	       						<td>
	                            	<input type="text" name="addr2" id="addr2" style="width: 80%">
	                           	</td>
	                         </tr>
                        </tr>
                    </tbody>
                </table>
                        <input type="hidden" name="checkEmail" id="checkEmail" value="0"/>
                </form>
                <!-- //write--->
                <div class="btnSet clear">
                    <div><a href="javascript:;" class="btn" onclick="goSave();">가입</a> <a href="javascript:;" class="btn" onclick="history.back();">취소</a></div>
                </div>
            </div>
        </div>
        
</body>
</html>