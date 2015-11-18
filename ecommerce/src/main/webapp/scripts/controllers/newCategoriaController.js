
angular.module('ecommerce').controller('NewCategoriaController', function ($scope, $location, locationParser, CategoriaResource , LivroResource) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.categoria = $scope.categoria || {};
    
    $scope.CadLivroList = LivroResource.queryAll(function(items){
        $scope.CadLivroSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.id
            });
        });
    });
    $scope.$watch("CadLivroSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.categoria.CadLivro = [];
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;
                $scope.categoria.CadLivro.push(collectionItem);
            });
        }
    });
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $location.path('/Categoria/edit/' + id);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        CategoriaResource.save($scope.categoria, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Categoria");
    };
});