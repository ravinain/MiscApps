class DuplicateFileService {

    getDuplicateFiles(successCallback, errorHandler) {
        $.get(
            {
//                url:'mocks/files-mock-response.json',
                url: 'http://localhost:9099/files',
                dataType: 'json'
            })
            .done(successCallback)
            .fail(errorHandler);
    }

    getFileNameAndInfoMapping(data) {
        let fileNameAndInfoMap = {};
        data.forEach(element => {
            if (fileNameAndInfoMap[element.fileName]) {
                fileNameAndInfoMap[element.fileName].push(element);
            } else {
                fileNameAndInfoMap[element.fileName] = [element];
            }
        });

        return fileNameAndInfoMap;
    }

    getFileNameListItem(fileName, count) {
        return `<li class="list-group-item list-group-item-action file-name-list-item" 
            onclick="refreshGallery('${fileName}')">
            ${fileName} 
            <span class="badge badge-dark">${count}</span>
            </li>`;
    }

    getImageThumbnail(imageSrc) {
        return `<div class="thumbnail">
                    <img src="${imageSrc}" class="img-thumbnail" onclick="refreshPreview(this.src)" />
                </div>`;
    }

}