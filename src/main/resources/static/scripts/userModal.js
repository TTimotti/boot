let signUpModal;
let signInModal;
const userModal = {
    showSignUpModal: function () {
        const modal = document.getElementById('signUpModal');
        modal.addEventListener('hidden.bs.modal', function (e) {
            replaceInput(modal.querySelector('#insertUserId'));
            modal.querySelector('#signUpForm').reset();
        });
        signUpModal = new bootstrap.Modal(modal, {'backdrop': 'static'});
        signUpModal.show();
    }, showSignInModal: function () {
        const modal = document.getElementById('signInModal');
        signInModal = new bootstrap.Modal(modal, {'backdrop': 'static'});
        modal.addEventListener('hidden.bs.modal', function (e) {
            modal.querySelector('#signInForm').reset();
        });
        signInModal.show();
    }
};

function signUpHandle() {
    const modal = document.getElementById('signUpModal');
    const id = modal.querySelector('#insertUserId').value;
    const pw = modal.querySelector('#insertPassword').value;
    if (checkId(id) === false || checkPw(pw) === false) return false;
    return false;
    // 아이디 중복 검사, 아이디와 비밀번호 유효성 검사 통과시
    // DB에 저장
    // 저장 성공 시 회원 가입 모달 종료 -> 회원가입 성공 안내(모달?)
    // 성공 안내 확인시 로그인 모달 띄움
    axios
        .post('/user/insert')
}

function checkId(id) {
    const idTag = document.getElementById('insertUserId');
    // 아이디 조건 및 유효성 검사
    if (id.trim() === '' || id === null) {
        console.log('실행됨');
        idTag.setAttribute('class', 'form-control rounded-3 is-invalid');
        return false;
    }
    idTag.setAttribute('class', 'form-control rounded-3 is-valid');
    return false;

    // 실패시 조건 안내문 출력
    // 성공시 아이디 중복검사
    // axios
    //     .post(아이디 체크, 아이디)
    //     .then(회원가입 모달 종료, 로그인 모달 실행)
    //     .catch(중복 메세지 안내, false 리턴)

}

function checkPw(pw) {
    // 비밀먼호 조건 및 유효성 검사
    // 실패시 조건 안내문 출력, false 리턴

}

function replaceInput(tag) {
    tag.setAttribute('class', 'form-control rounded-3')
}
