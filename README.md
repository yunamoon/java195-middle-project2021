# :lab_coat: spring boot project : 쇼핑몰 195 
195 쇼핑몰은 spring boot를 이용하여 제작한 의류 쇼핑몰 입니다. 
<br>
<br>
# 개발 환경
- spring boot , java , html , css , javascript , jquery , ajax , oracle DB 
<br>
<br>
# 프로젝트 상세
- 프로젝트 기간 : 4월 1일 ~ 4월 12일 ( 기획 7일, 개발 6일 )
- 프로젝트 인원 : 4 인 
<br>
<br>
# 기획 및 설계
- 노션 : https://www.notion.so/195-8c0eb37b4b0f4c52839fa1cef7e3d33e
<br>
<br>
# 구현 화면 보기 
<br>
<br>
### 메인 화면
![메인1](https://user-images.githubusercontent.com/78346017/126985493-811308c2-d954-42e2-8052-10c645392baf.JPG)
![메인 2](https://user-images.githubusercontent.com/78346017/126985528-19071bb9-c7bb-4e61-a513-84c3c1f2e111.JPG)
![메인3](https://user-images.githubusercontent.com/78346017/126985663-a7c04788-5de6-44c6-b661-12e7a3ad54b1.JPG)
<br>
- 메인 화면 이미지 슬라이드를 삽입 하여, 사용자가 의류 쇼핑몰임을 한눈에 확인 가능하도록 했습니다.
- 메인 화면에 신상품 리스트를 카드 이미지 형식으로 노출하였습니다.
- HOME Controller에 index에 접근할 때, 조건문을 실행 시켜 등록 순으로 8개까지 노출 되도록 구현하였습니다.
- 의류 쇼핑몰 메인 페이지에 있는 기타 UI를 구현했습니다.
<br><br>

### 회원가입 및 로그인
![스크린샷(3)](https://user-images.githubusercontent.com/78346017/126997118-cf4b177d-2350-4e81-b339-932d673f6114.png)
![스크린샷(4)](https://user-images.githubusercontent.com/78346017/126997122-84f37774-1f83-4556-839d-6a91211f468d.png)
<br>
- 회원 가입은 html from tag를 이용하여, controller에서 member dto 형식으로 데이터를 받아, oracle DB에 저장하였습니다.
- 관리자와 회원 DB table을 따로 생성하였습니다. 
- 로그인 시, member DB table을 먼저 확인하고 return 값을 null일 경우 admin DB table을 확인하여, 로그인 정보를 구분하도록 했습니다.
- 로그인은 HTTP session을 이용했습니다.
<br><br>

### 마이페이지 
![스크린샷(16)](https://user-images.githubusercontent.com/78346017/126997778-47cf79c1-d5fd-445f-a3de-c321560bec04.png)
![스크린샷(17)](https://user-images.githubusercontent.com/78346017/126997807-632ad541-7317-42de-a4a2-3b47baa6c89e.png)
![스크린샷(18)](https://user-images.githubusercontent.com/78346017/126997820-4ba5cd2d-8ab6-4fca-95f0-570181745030.png)
<br>
- 로그인 session이 존재 할 시,  헤더에 마이 페이지 아이콘이 노출됩니다.
- 아이콘 클릭 시, session에 저장되어 있는 user 정보에서 id와 password를 get하여 일치하는 정보를 불러옵니다.
- 마이 페이지 내에는 내 정보 확인, 수정, 탈퇴, 주문 목록, 로그 아웃의 메뉴가 노출됩니다.
- 내 정보 수정은 원하는 정보를 입력 후 수정 버튼 클릭 시, member DB table이 수정 됩니다. 
- 회원 탈퇴는 Passsword 일치 확인 이후, 탈퇴 버튼을 클릭 할 시에 member DB table이 삭제 됩니다.
<br><br>

### 상품 목록 및 검색
![스크린샷(6)](https://user-images.githubusercontent.com/78346017/126998282-8a4be983-4de2-4661-89c8-f5acccbcf666.png)
![스크린샷(7)](https://user-images.githubusercontent.com/78346017/126998329-c210744e-adb0-42f4-9bb3-ba310dea4f36.png)
<br>
- 헤더의 product 버튼을 클릭하면 상품 목록 페이지로 이동합니다.
- 오른쪽에 보이는 상품 검색 바를 이용하여 원하는 옵션을 선택하여 상품 검색이 가능합니다.
<br><br>

## 장바구니
![스크린샷(8)](https://user-images.githubusercontent.com/78346017/126998636-e931857f-5da2-4a72-9140-64eea70214f5.png)
![스크린샷(9)](https://user-images.githubusercontent.com/78346017/126998654-21e81a89-a345-4933-b1f7-0d7aa0f5a3d8.png)
![스크린샷(10)](https://user-images.githubusercontent.com/78346017/126998658-b39bd335-3bb6-4c24-b5fb-c5bdbc6c7cc0.png)
![스크린샷(11)](https://user-images.githubusercontent.com/78346017/126998674-68f97398-ea80-450c-8f31-d0c020e52933.png)
<br>
- 원하는 상품을 클릭하면 해당 상품의 상세 보기 페이지로 이동합니다.
- 페이지 내에서 상품의 사이즈와 수량을 선택 할 수 있으며, 장바구니에 등록하기를 누르면 확인 창과 함께 장바구니에 상품이 보관됩니다. 
- 장바구니 내에서 수량을 변경 할 수 있으며, 결제 버튼을 누르면 결제가 이루어 집니다.
<br>ㅍ
## 주문 및 주문 목록
![스크린샷(12)](https://user-images.githubusercontent.com/78346017/126998869-2179feaf-a6ab-42b9-a215-62e6344630e9.png)
![스크린샷(14)](https://user-images.githubusercontent.com/78346017/126998877-a16c94a3-4d7a-46a1-ad71-1f42f1e12c54.png)
![스크린샷(15)](https://user-images.githubusercontent.com/78346017/126998887-a22fc5cf-d4dc-4ad4-8a6d-72113bfa1bbb.png)
<br>
- 결제 버튼을 누르면 결제 완료 후 주문 목록이 생성 됩니다. 
<br><br>

### 관리자 상품 등록
![스크린샷(19)](https://user-images.githubusercontent.com/78346017/126999009-0f438f2b-a5dd-424f-a2b9-d91df73755bb.png)
![스크린샷(20)](https://user-images.githubusercontent.com/78346017/126999016-70801690-f1ff-4085-bd56-75ae67208cec.png)
![스크린샷(21)](https://user-images.githubusercontent.com/78346017/126999020-67e05acf-7cd0-4ca3-83d1-cf69d954cc6b.png)
<br>
- 관리자 로그인 시, 상품 목록 내에 상품 등록 버튼이 생성 됩니다.
- 상품 등록 클릭 시, 상품 등록 페이지로 이동합니다.
- 상품 상세 보기를 클릭하면 상품 정보 수정과 상품 삭제 버튼이 노출 됩니다.
<br><br>
