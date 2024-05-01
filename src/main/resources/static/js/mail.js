$(document).ready(function () {
    // 페이지가 준비되면 모달을 숨깁니다.
    $('#loadingModal').modal('hide');
});

let multipartFiles = []; // 클라이언트 측에서 관리할 MultipartFile 배열

document.querySelector('input[type="file"]').addEventListener('change', handleFileInputChange);

function handleFileInputChange(event) {
    const files = event.target.files;

    for (let i = 0; i < files.length; i++) {
        multipartFiles.push(files[i]); // 선택한 파일을 배열에 추가
    }

    renderFileList(); // 파일 목록을 UI에 업데이트
}

function renderFileList() {
    const selectedFilesContainer = document.getElementById('selectedFiles');
    selectedFilesContainer.innerHTML = ''; // 기존 목록을 초기화

    const fileList = document.createElement('ul');
    fileList.className = 'list-unstyled';

    multipartFiles.forEach((file, index) => {
        const listItem = document.createElement('li');
        listItem.className = 'file-list-item';
        const fileName = document.createElement('span');
        fileName.className = 'file-name';
        fileName.textContent = file.name;
        const deleteButton = document.createElement('span');
        deleteButton.className = 'delete-button';
        deleteButton.textContent = 'x';
        deleteButton.addEventListener('click', () => {
            // 해당 파일을 배열에서 제거
            multipartFiles.splice(index, 1);
            renderFileList(); // 목록을 다시 렌더링하여 업데이트
        });
        listItem.appendChild(fileName);
        listItem.appendChild(deleteButton);
        fileList.appendChild(listItem);
    });

    selectedFilesContainer.appendChild(fileList);
}

function sendFilesToServer() {

    const formData = new FormData();

    multipartFiles.forEach((file, index) => {
        formData.append('files', file); // 'files'는 서버에서 받을 파라미터 이름
    });

    formData.append('address', sendForm.address.value);
    formData.append('content', sendForm.content.value);
    formData.append('title', sendForm.title.value);

    const url = '/mail/send';

    // AJAX 요청 전에 로딩 모달 표시
    $('#loadingModal').modal('show');

    $.ajax({
        url: url,
        type: 'POST',
        data: formData,
        processData: false,  // processData를 false로 설정하여 formData를 변환하지 않음
        contentType: false,  // contentType을 false로 설정하여 jQuery가 Content-Type 설정을 처리하지 않음
        success: function (response) {
            console.log('파일 전송 성공');
            window.location.href = '/result'; // 성공 시 결과 페이지로 이동
        },
        error: function (xhr, status, error) {
            console.error('서버 응답 오류:', xhr.status);
            alert('서버 응답 오류가 발생했습니다.');
        },
        complete: function () {
            // AJAX 요청 완료 후 로딩 모달 닫기
            $('#loadingModal').modal('hide');
        }
    });

}