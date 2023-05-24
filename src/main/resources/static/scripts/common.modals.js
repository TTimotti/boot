const modals = {
    showSignUpModal : function () {
        const modal = new bootstrap.Modal('#signUpModal', {'backdrop':'static', 'focus':true, 'keyboard':true});
        $('#signUpForm')[0].reset();
        modal.show();
    },
    showSignInModal : function () {
        const modal = new bootstrap.Modal('#signInModal', {'backdrop':'static', 'focus':true, 'keyboard':true});
        $('#signInForm')[0].reset();
        modal.show();
    }
}
const userHandler = {
    signUp: function () {
        const form = $('#signUpForm')[0];
        const data = new FormData(form);

        $.ajax({
            type : 'POST',
            url : '/user/insert',
            data : data,
            contentType : false,
            processData : false,
            success : function(res){  // 성공
                alert(`${ res } 성공하였습니다`);
            },
            error : function(XMLHttpRequest, textStatus, errorThrown){ // 실패
                alert('실패하였습니다');
                console.log(textStatus);
                console.log(XMLHttpRequest);
                console.log(errorThrown);
            }
        });
    }
}