
angular.module('ecommerce').controller('NewLivroController', function ($scope, $location, locationParser, LivroResource , CategoriaResource) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.livro = $scope.livro || {};
    
    $scope.categoriaList = CategoriaResource.queryAll(function(items){
        $scope.categoriaSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.id
            });
        });
    });
    $scope.$watch("categoriaSelection", function(selection) {
        if ( typeof selection != 'undefined') {
            $scope.livro.categoria = {};
            $scope.livro.categoria.id = selection.value;
        }
    });
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $location.path('/Livros/edit/' + id);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        LivroResource.save($scope.livro, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Livros");
    };
});