'use strict';

/* Controllers */

angular.module('myApp.file.controllers', [])

    .controller('UploadPopUpController', function($scope,$uibModal) {
        $scope.open=function(){
            console.log('opening pop up');
            var popUpInstance=$uibModal.open({
                templateUrl:'Modules/file/uploadFile.html',
                controller:'FileUploadController'
            });
        }


    })

    .controller('FileUploadController', function ($scope,$http,RestService) {
        $scope.fileInfo={};
        $scope.fileInfo.fileId="1";
        $scope.fileInfo.userId="1";
        $scope.fileInfo.version="1"

        $scope.fileReaderSupported = window.FileReader != null;
        $scope.onChange = function(files) {
            $scope.fileInfo.uplaodedFile = files;
            //console.log(file);
        }
            $scope.uploadFile = function(){
                var fd = new FormData();
                fd.append('file',$scope.myfile);
                fd.append('name',$scope.fileInfo.fileName);

                $http.post('http://localhost:8080/filemanager/File/s3UploadFiles',fd,{transformRequest: angular.identity,
                    headers: {'Content-Type': undefined}})
                    .then(function (res) {
                        if(res.data.status=="SUCCESS"){
                                return RestService.post('File/insertFile', $scope.fileInfo)
                            .then(function (dbresp) {
                                    console.log(dbresp.data)
                                })

                            console.log(res.data.status)
                        }

                        //return res.data;
                        console.log(res.data.status)
                    })
                    
                }})

        //         return $http({
        //             method: 'POST',
        //             url: 'http://localhost:8080/filemanager/File/s3UploadFiles',
        //             //data: fd,
        //             transformRequest: angular.identity,
        //             headers: {
        //                 'Content-Type': undefined
        //             },
        //             params:{
        //               //  "name":$scope.fileInfo.fileName,
        //                 "file":fd
        //             }
        //         }).then(function (res) {
        //             return res.data;
        //             //return RestService.post('File/uploadFiles',$scope.fileInfo);
        //
        //         })
        // }})
    //     $scope.fileInfo={};
    //     //
    //
    //     $scope.uploadFile=function () {
    //
    //         /*var data={
    //             "userID" : $scope.user.userID,
    //             "password" :$scope.user.passcode
    //         };*/
    //         console.log(fileInfo.file.path());
    //         return RestService.post('File/uploadFiles',$scope.fileInfo);
    //
    //     }
    // })

// angular.module('test') .controller('UploadCtrl', function($scope, $timeout) {
//
// // Read the image using the file reader
//     $scope.fileReaderSupported = window.FileReader != null;
//     $scope.photoChanged = function(files) {
//         if (files != null) {
//             var file = files[0];
//             if ($scope.fileReaderSupported && file.type.indexOf('image') > -1) {
//                 $timeout(function() {
//                     var fileReader = new FileReader();
//                     fileReader.readAsDataURL(file); // convert the image to data url.
//                     fileReader.onload = function(e) {
//                         $timeout(function() {
//                             $scope.thumbnail.dataUrl = e.target.result; // Retrieve the image.
//                         });
//                     }
//                 });
//             }
//         }
//     };
// })

    .controller('S3Controller', function($scope) {
    $scope.$on('s3uploader:error', function(event, code, message, data) {
        //console.log(code, message, data);
    });
    $scope.form = {
        files: [],
        file:  []
    };
    $scope.options2 = {
        multiple:   false,
        extensions: ['png'],
        immediate:  true,
        bucket:     'private-cdn-ch',
        acl:        'public-read',
        folder:     "1/1/",
        filename:   "art",
        filesize:   '10000000',
        totalsize:  '10000000',
        region:     'us-east-1',
        limit:      1,
        policy:     {"policy":"eyJleHBpcmF0aW9uIjoiMjAxNy0wOS0wOVQwMzozNTozOVoiLCJjb25kaXRpb25zIjpbeyJidWNrZXQiOiJwcml2YXRlLWNkbi1jaCJ9LFsic3RhcnRzLXdpdGgiLCIka2V5IiwiIl0seyJhY2wiOiJwdWJsaWMtcmVhZCJ9LFsiY29udGVudC1sZW5ndGgtcmFuZ2UiLDAsMTA0ODU3NjBdLFsic3RhcnRzLXdpdGgiLCIkQ29udGVudC1UeXBlIiwiIl1dfQ==","signature":"CAsgQ1QdxnF\/qMhKYl7kCI+27lg=","key":"AKIAICAY5HY475TWHACA"}
    };
    $scope.delete2 = function(name) {
        $scope.form.file = [];
    };
    $scope.delete = function(name) {
        angular.forEach($scope.form.files, function(v, k) {
            if(v.name == name) {
                $scope.form.files.splice(k, 1);
                return false;
            }
        });
    };
    $scope.options = {
        multiple:   true,
        extensions: ['png', 'jpg', 'mp4'],
        immediate:  false,
        bucket:     'private-cdn-ch',
        acl:        'public-read',
        folder:     "1/1/",
        filename:   "art_[RND]",
        filesize:   '10000000',
        totalsize:  '10000000',
        region:     'us-east-1',
        limit:      5,
        cb_error:   function(msg) {
            console.log(msg);
        },
        replace:    true,
        policy:     {
            "policy":    "eyJleHBpcmF0aW9uIjoiMjAxNi0wNS0xOFQxNjo0OToyNFoiLCJjb25kaXRpb25zIjpbeyJidWNrZXQiOiJwcml2YXRlLWNkbi1jaCJ9LFsic3RhcnRzLXdpdGgiLCIka2V5IiwiIl0seyJhY2wiOiJwdWJsaWMtcmVhZCJ9LFsiY29udGVudC1sZW5ndGgtcmFuZ2UiLDAsMTA0ODU3NjBdLFsic3RhcnRzLXdpdGgiLCIkQ29udGVudC1UeXBlIiwiIl1dfQ==",
            "signature": "pzLtBoaue15H5s4jhU7voe7YaCQ=",
            "key":       "AKIAIBUOK6TJJUEDWPBA"
        },
        policyUrl:  ''
    };
    $scope.save = function(testForm) {
        if(testForm.$invalid) {
            //return;
        }
    }
});