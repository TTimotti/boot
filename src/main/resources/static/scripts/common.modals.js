const modals = {
    showSignUpModal: function () {
        const modal = new bootstrap.Modal('#signUpModal', {'backdrop': 'static', 'focus': true, 'keyboard': true});
        document.getElementById('signUpForm').reset();
        modal.show();
    },
    showSignInModal: function () {
        const modal = new bootstrap.Modal('#signInModal', {'backdrop': 'static', 'focus': true, 'keyboard': true});
        document.getElementById('signInForm').reset();
        modal.show();
    },
    showInfoModal: function (title, body, btn, callback) {
        document.getElementById('infoModalTitle').text(title);
        document.getElementById('infoModalBody').text(body);
        document.getElementById('infoModalBtn').text(btn);
        document.getElementById("infoModalBtn").off("click").on("click", callback);
        const modal = new bootstrap.Modal('#infoModal', {'backdrop': 'static', 'focus': true, 'keyboard': true});
        modal.show();
    }
}
const userHandler = {
    signUp: function () {
        const form = document.getElementById('signUpForm');
        const data = new FormData(form);

        axios
            .post('/user/insert', data, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            })
            .then(res => {
                alert(`${res.data}님 환영합니다.`);
            })
            .catch(err => {
                alert(`${err}, 계정생성 실패.`);
            })
    }
}