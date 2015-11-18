

angular.module('ecommerce').controller('EditCategoriaController', function($scope, $routeParams, $location, CategoriaResource , LivroResource) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.categoria = new CategoriaResource(self.original);
            LivroResource.queryAll(function(items) {
                $scope.CadLivroSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.id
                    };
                    if($scope.categoria.CadLivro){
                        $.each($scope.categoria.CadLivro, function(idx, element) {
                            if(item.id == element.id) {
                                $scope.CadLivroSelection.push(labelObject);
                                $scope.categoria.CadLivro.push(wrappedObject);
                            }
                        });
                        self.original.CadLivro = $scope.categoria.CadLivro;
                    }
                    return labelObject;
                });
            });
        };
        var errorCallback = function() {
            $location.path("/Categoria");
        };
        CategoriaResource.get({CategoriaId:$routeParams.CategoriaId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.categoria);
    };

    $scope.save = function() {
        var successCallback = function(){
            $scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.categoria.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/Categoria");
    };

    $scope.remove = function() {
        var successCallback = function() {
            $location.path("/Categoria");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        }; 
        $scope.categoria.$remove(successCallback, errorCallback);
    };
    
    $scope.CadLivroSelection = $scope.CadLivroSelection || [];
    $scope.$watch("CadLivroSelection", function(selection) {
        if (typeof selection != 'undefined' && $scope.categoria) {
            $scope.categoria.CadLivro = [];
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;
                $scope.categoria.CadLivro.push(collectionItem);
            });
        }
    });
    
    $scope.get();
});