let duplicateFileService = new DuplicateFileService();
let fileNameInfoMapping = {};
let successCallback = function(response) {
    fileNameInfoMapping = duplicateFileService.getFileNameAndInfoMapping(response);
    let listItems = '';
    let firstFileName;

    Object.keys(fileNameInfoMapping).forEach(key => {
        listItems += duplicateFileService.getFileNameListItem(key, fileNameInfoMapping[key].length);
        firstFileName = firstFileName ? firstFileName : key;
    });
    
    $('#fileNameList').html(listItems);

    refreshGallery(firstFileName);
};

let errorHandler = function(error) {
    console.log('Error : ', error);
};


$(document).ready(function(){
    duplicateFileService.getDuplicateFiles(successCallback, errorHandler);
});

function refreshGallery(fileName) {
    refreshPreview(fileNameInfoMapping[fileName][0]['filePath']);
    refreshThumbnails(fileName);
}

function refreshPreview(filePath) {
    $('#preview img').prop('src', filePath);
}

function refreshThumbnails(fileName) {
    let thumbnailsDiv = '';
    fileNameInfoMapping[fileName].forEach(fileInfo => {
        thumbnailsDiv += duplicateFileService.getImageThumbnail(fileInfo.filePath);
    });

    $('#thumbnails').html(thumbnailsDiv);
}