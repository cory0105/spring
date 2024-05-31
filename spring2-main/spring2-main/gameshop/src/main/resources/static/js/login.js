const urlLogin = "http://localhost:8080/user/login";
const urlLogout = "http://localhost:8080/user/logout";

let userId = "";
let password = "";

document.querySelector("#userId").addEventListener("change", (e)=>{
    console.log(e.target.value);
    userId = e.target.value;
})

document.querySelector("#password").addEventListener("change", (e)=>{
    console.log(e.target.value);
    password = e.target.value;
})

document.querySelector(".loginBtn").addEventListener("click",()=>{
    const data = {
        userId: userId,
        password: password
    }
    axios
    .post(urlLogin, data, {withCredentials: true})
    .then((response)=>{
        console.log("데이터: ", response);
        sessionCurrent();
    })
    .catch((error)=>{
        console.log("에러 발생: ", error);
    })
})

document.querySelector(".logoutBtn").addEventListener("click", ()=>{
    if(confirm("로그아웃하시겠습니까?")){
        axios
        .post(urlLogout, {}, {withCredentials: true})
        .then((response)=>{
            console.log("데이터: ", response);
            if(response.status == 200){
                document.querySelector(".login-box").classList.remove("hidden");
                document.querySelector(".user-box").classList.add("hidden");
            }
        })
        .catch((error)=>{
            console.log("에러 발생: ", error);
        })
    }
})

function sessionCurrent() {
    axios
    .get("http://localhost:8080/user/current", {withCredentials: true})
    .then((response)=>{
        console.log("데이터: ", response);
        if(response.status == 200){
            console.log("세션 유지");
            if(response.status == 200){
                document.querySelector(".login-box").classList.add("hidden");
                document.querySelector(".user-box").classList.remove("hidden");
                document.querySelector(".user-box p").textContent = response.data.userId + "님, 환영합니다.";
            }
        }
    })
    .catch((error)=>{
        console.log("에러 발생: ", error);
    })
}

// js 파일이 로드될때 호출됨
sessionCurrent();


{/* <gameshop 회원가입 기능 만들기>
1) signup-box 만들기 
: login-box와 유사하게 만들고 hidden으로 세팅
2) 회원가입 버튼 만들기
: 로그인버튼옆에 회원가입 버튼을 만들어서 클릭하면 화면에
signup-box가 보이도록 함
3) 회원가입에 필요한 input창 만들기
: userDto와 동일하게 정보를 입력하게 하고 change이벤트를
등록하여 변수에 담아둠 (login-box에 사용한 input 이벤트 참조)
4) signup-box안에 signup 버튼 이벤트 등록
: signup 버튼을 누르면 3)번에서 작성된 변수를 이용하여 
axios data 객체를 구성하고 회원가입 rest api 주소로 Post 요청함 */}

const urlSignup = "http://localhost:8080/user/signup";

let signupUserId = "";
let signupPassword = "";
let signupUserName = "";
let signupUserEmail = "";

document.querySelector(".signupBtn2").addEventListener("click",()=>{
    signup();
})

document.querySelector("#signupUserId").addEventListener("change", (e)=>{
    signupUserId = e.target.value;
})
document.querySelector("#signupPassword").addEventListener("change", (e)=>{
    signupPassword = e.target.value;
})
document.querySelector("#signupUserName").addEventListener("change", (e)=>{
    signupUserName = e.target.value;
})
document.querySelector("#signupUserEmail").addEventListener("change", (e)=>{
    signupUserEmail = e.target.value;
})

document.querySelector(".signupBtn").addEventListener("click",()=>{
    if(signupUserId==""||signupPassword==""||signupUserName==""||signupUserEmail==""){
        console.log("입력을 하시오.");
    } else{
        const data = {
            userId: signupUserId,
            password: signupPassword,
            userName: signupUserName,
            userEmail: signupUserEmail
        }
        axios
        .post(urlSignup, data)
        .then((response)=>{
            console.log("데이터: ", response);
            if(response.data!="이미 등록된 아이디입니다."){
                document.querySelector(".signup-box").classList.add("hidden");
            const data2 = {
                userId: signupUserId,
                password: signupPassword
            }
            axios
            .post(urlLogin, data2, {withCredentials: true})
            .then((response)=>{
                console.log("데이터: ", response);
                sessionCurrent();
            })
            .catch((error)=>{
                console.log("에러 발생: ", error);
            })
            }
        })
        .catch((error)=>{
            console.log("에러 발생: ", error);
        })
    }
})

function signup() {
    document.querySelector(".login-box").classList.add("hidden");
    document.querySelector(".signup-box").classList.remove("hidden");
}