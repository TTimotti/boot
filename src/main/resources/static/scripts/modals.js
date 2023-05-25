const modals = {
    showMessageModal: function (title, msg, btn) {
        document.getElementById('messageModalTitle').innerText = title;
        document.getElementById('messageModalBody').innerText = msg;
        document.getElementById('messageModalBtn').innerText = btn;
        const modal = new bootstrap.Modal('#messageModal', {'backdrop': 'static'});
        modal.show();
    }
}