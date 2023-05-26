const modals = {
    title:  document.getElementById('messageModalTitle'),
    msg: document.getElementById('messageModalBody'),
    btn: document.getElementById('messageModalBtn'),
    showMessageModal: function (title, msg, btn) {
        this.title.innerText = title;
        this.msg.innerText = msg;
        this.btn.innerText = btn;
        const modal = new bootstrap.Modal('#messageModal', {'backdrop': 'static'});
        modal.show();
    },
    showMessageCallbackModal: function (title, msg, btn, callback) {
        this.title.innerText = title;
        this.msg.innerText = msg;
        this.btn.innerText = btn;
        this.btn.addEventListener('click', callback, { once : true });
        const modal = new bootstrap.Modal('#messageModal', {'backdrop': 'static'});
        modal.show();
    }
}