'use strict';

/* Controllers */

angular.module('myApp.controllers', [])


    // .controller('HelloController',function($scope,$http){
    //     $http.get('http://localhost:8080/FirstSpringMVCProject/welcome')
    //     .then(function(response) {
    //         $scope.greeting = response.data;
    //             console.log('Response from server : ' + data)
    //         });
    // })
    .controller('FileListController', function($scope,$http,RestService) {
        $scope.fileList=[{}];
        $scope.gridOptions=[{}];
        $scope.getFilelist = function(){
            RestService.getwithouparams('File/getAllFiles')
                .then(function(data){
                    $scope.fileList=data;


                    $scope.gridOptions={data: 'fileList',
                        columnDefs: [{field: 'userId', displayName: 'userId'},
                            {field:'fileName', displayName:'fileName'},
                            { field:'createdDate',displayName:'createdDate'},
                            {field:'fileName',displayName:'fileName'},
                            {field:'fileDsc', displayName:'fileDsc'},
                            {field:'modifiedDate', displayName:'modifiedDate'},
                            {field:'fileUrl', displayName:'fileUrl'},
                            {field:'version', displayName:'version'}]};
                    console.log($scope.gridOptions);
                });

        }

    })

    // .controller('FileUploadController', function($scope,$http,RestService){
    //     $scope.upload = function(file) {
    //         // Get The PreSigned URL
    //         $http.post('/presigned'),{ filename: file.name, type: file.type }
    //         .success(function(resp) {
    //             // Perform The Push To S3
    //             $http.put(resp.url, file, {headers: {'Content-Type': file.type}})
    //                 .success(function(resp) {
    //                     //Finally, We're done
    //                     alert('Upload Done!')
    //                 })
    //                 .error(function(resp) {
    //                     alert("An Error Occurred Attaching Your File");
    //                 });
    //         })
    //         .error(function(resp) {
    //             alert("An Error Occurred Attaching Your File");
    //         });
    // }
    // })

  .controller('MyCtrl2', function($scope,$routeParams) {
      $scope.firstname=$routeParams.firstname;
      $scope.lastname=$routeParams.lastname;
  });