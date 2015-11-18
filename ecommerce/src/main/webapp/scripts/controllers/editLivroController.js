

angular.module('ecommerce').controller('EditLivroController', function($scope, $routeParams, $location, LivroResource , CategoriaResource) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.livro = new LivroResource(self.original);
            CategoriaResource.queryAll(function(items) {
                $scope.CategoriaSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.id
                    };
                    if($scope.livro.Categoria && item.id == $scope.livro.Categoria.id) {
                        $scope.CategoriaSelection = labelObject;
                        $scope.livro.Categoria = wrappedObject;
                        self.original.Categoria = $scope.livro.Categoria;
                    }
                    return labelObject;
                });
            });
        };
        var errorCallback = function() {
            $location.path("/Livros");
        };
        LivroResource.get({LivroId:$routeParams.LivroId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.livro);
    };

    $scope.save = function() {
        var successCallback = function(){
            $scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.livro.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/Livros");
    };

    $scope.remove = function() {
        var successCallback = function() {
            $location.path("/Livros");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        }; 
        $scope.livro.$remove(successCallback, errorCallback);
    };
    
    $scope.$watch("CategoriaSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.livro.Categoria = {};
            $scope.livro.Categoria.id = selection.value;
        }
    });
    
    $scope.get();
});