let signUpModal;
let signInModal;
const userModal = {
    showSignUpModal: function () {
        const modal = document.getElementById('signUpModal');
        modal.addEventListener('hidden.bs.modal', function () {
            replaceInput(modal.querySelector('#insertUserId'));
            modal.querySelector('#signUpForm').reset();
        });
        signUpModal = new bootstrap.Modal(modal, {'backdrop': 'static'});
        signUpModal.show();
    }, showSignInModal: function () {
        const modal = document.getElementById('signInModal');
        signInModal = new bootstrap.Modal(modal, {'backdrop': 'static'});
        modal.addEventListener('hidden.bs.modal', function () {
            replaceInput(modal.querySelector('#selectUserId'));
            modal.querySelector('#signInForm').reset();
        });
        signInModal.show();
    }
};

function signUpHandle() {
    const modal = document.getElementById('signUpModal');
    const id = modal.querySelector('#insertUserId').value;
    const pw = modal.querySelector('#insertPassword').value;
    if (checkForm(id, pw) === false) return false;
}

async function checkForm(id, pw) {
    const idTag = document.getElementById('insertUserId');
    const idFeedback = document.getElementById('insertUserIdFeedback');
    const pwTag = document.getElementById('insertPassword');
    const pwFeedback = document.getElementById('insertPasswordFeedback');
    const feedback = {
        idValidFeedback: function (msg) {
            idTag.setAttribute('class', 'form-control rounded-3 is-valid');
            idFeedback.innerText = msg;
            idFeedback.setAttribute('class', 'valid-feedback');
        },
        idInValidFeedback: function (msg) {
            idTag.focus();
            idTag.setAttribute('class', 'form-control rounded-3 is-invalid');
            idFeedback.innerText = msg;
            idFeedback.setAttribute('class', 'invalid-feedback');
        },
        pwValidFeedback: function (msg) {
            pwTag.setAttribute('class', 'form-control rounded-3 is-valid');
            pwFeedback.innerText = msg;
            pwFeedback.setAttribute('class', 'valid-feedback');
        },
        pwInValidFeedback: function (msg) {
            pwTag.focus();
            pwTag.setAttribute('class', 'form-control rounded-3 is-invalid');
            pwFeedback.innerText = msg;
            pwFeedback.setAttribute('class', 'invalid-feedback');
        }
    }
    // 아이디 조건 및 유효성 검사
    // (1) 공백 또는 null 검사
    if (id.trim() === '') {
        feedback.idInValidFeedback('사용할 이메일 주소를 입력하세요');
        return false;
    }
    // (2) 정규식(이메일형식) 검사
    const idReg = /^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
    if (!idReg.test(id)) {
        feedback.idInValidFeedback('올바른 이메일을 사용하세요');
        return false;
    }
    // (3) 정규식(비밀번호) 검사
    const pwReg = /^(?=.+\d)(?=.+[a-zA-Z])[\da-zA-Z!@#$%&*]{4,24}$/;
    if (!pwReg.test(pw)) {
        feedback.pwInValidFeedback('사용할 수 없는 비밀번호입니다.');
        return false;
    }
    // (1), (2), (3) 성공시 아이디 중복검사
    // (4) 중복 검사, 비동기
    const data = new FormData();
    data.append('userId', id);
    const checkDB = function (data) {
        axios
            .post('/user/check', data)
            .then(res => {
                if (res.data !== '') {
                    feedback.idInValidFeedback('이미 가입된 이메일입니다');
                    return false;
                }
                feedback.idValidFeedback('사용 가능한 이메일입니다');
                signUpModal.hide();
                modals.showMessageCallbackModal('Success', `회원가입에 성공하였습니다.`, '로그인', function () {
                    console.log('test');
                    userModal.showSignInModal();
                });
            })
            .catch(err => {
                signUpModal.hide();
                modals.showMessageModal('Failed', err, '확인');
            })
    }
    checkDB(data);
}

function replaceInput(tag) {
    tag.setAttribute('class', 'form-control rounded-3')
}






