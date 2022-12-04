angular.module('app',[]).controller('indexController', function($scope, $http){
    const contextPath = 'http://localhost:8080/myapp/api/v1';
    $scope.pageNumber = 1;

$scope.fillTable = function() {
                $http({
                    url: contextPath +'/products',
                    method: 'GET',
                    params: {
                                p: $scope.pageNumber,
                                min_cost: $scope.filter ? $scope.filter.min_cost : null,
                                max_cost: $scope.filter ? $scope.filter.max_cost : null,
                                title_part: $scope.filter ? $scope.filter.title_part : null
                    }
                }).then(function(response) {
                                  $scope.ProductList = response.data.content;
                              });
                      };


            $scope.change_page = function(pageVar) {
                 $scope.pageNumber = $scope.pageNumber + pageVar;
                    if($scope.pageNumber <= 0){
                    $scope.pageNumber = 1
                    }
                    $http({
                        url: contextPath +'/products',
                        method: 'GET',
                        params: {
                                p: $scope.pageNumber,
                                min_cost: $scope.filter ? $scope.filter.min_cost : null,
                                max_cost: $scope.filter ? $scope.filter.max_cost : null,
                                title_part: $scope.filter ? $scope.filter.title_part : null
                        }
                    }).then(function(response) {
                                  $scope.ProductList = response.data.content;
                              });
            };

/*удаление продукта*/
    $scope.deleteProductById = function(id){
            $http.delete(contextPath + '/products/' + id)
            .then(function(response) {
                location.reload();
            });
    };
/*добавление продукта*/
    $scope.submitCreateNewProduct = function(){
    /*alert("Отправка!"+ $scope.newProduct);*/
                $http.post(contextPath + '/products', $scope.newProduct)
                    .then(function(response) {
                     location.reload();
                });
        };

    $scope.fillTable();

});
/*
приходится делать location.reload(); так ка $scope.fillTable(); работает только в fillTable();*/
