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
    if (checkFormAndInsert(id, pw) === false) return false;
}

function signInHandle() {
    const modal = document.getElementById('signInModal');
    const id = modal.querySelector('#selectUserId').value;
    const pw = modal.querySelector('#selectPassword').value;
    axios
        .post('/login')
}

function checkFormAndInsert(id, pw) {
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
        pwInValidFeedback: function (msg) {
            pwTag.focus();
            pwTag.setAttribute('class', 'form-control rounded-3 is-invalid');
            pwFeedback.innerText = msg;
            pwFeedback.setAttribute('class', 'invalid-feedback');
        }
    }
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
    if (pw.trim() === '') {
        feedback.pwInValidFeedback('사용할 비밀번호를 입력하세요.');
        return false;
    }
    if (!pwReg.test(pw)) {
        feedback.pwInValidFeedback('사용할 수 없는 비밀번호입니다.');
        return false;
    }
    const checkDB = function () {
        axios
            .post('/user/check',{
                userId: id
            })
            .then(res => {
                if (res.data !== '') {
                    feedback.idInValidFeedback('이미 가입된 이메일입니다');
                    return false;
                }
                feedback.idValidFeedback('사용 가능한 이메일입니다');
            })
            .then((res) => {
                if (res === false) return false;
                axios
                    .post('/user/insert', {
                        userId: id,
                        password: pw
                    })
                    .then(res => {
                        signUpModal.hide();
                        modals.showMessageCallbackModal('Success', `${res.data}님, 환영합니다.`, '로그인', function () {
                            console.log('test');
                            userModal.showSignInModal();
                        });
                    })
                    .catch(err => {
                        signUpModal.hide();
                        modals.showMessageModal('Failed', err, '확인');
                    })
            })
            .catch(err => {
                signUpModal.hide();
                modals.showMessageModal('Failed', err, '확인');
            })
    }
    checkDB();
}
function replaceInput(tag) {
    tag.setAttribute('class', 'form-control rounded-3')
}