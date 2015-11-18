

angular.module('ecommerce').controller('EditPedidoLivrosController', function($scope, $routeParams, $location, PedidoLivrosResource , PedidosResource) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.pedidoLivros = new PedidoLivrosResource(self.original);
            PedidosResource.queryAll(function(items) {
                $scope.pedidoslivrosSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.id
                    };
                    if($scope.pedidoLivros.pedidoslivros && item.id == $scope.pedidoLivros.pedidoslivros.id) {
                        $scope.pedidoslivrosSelection = labelObject;
                        $scope.pedidoLivros.pedidoslivros = wrappedObject;
                        self.original.pedidoslivros = $scope.pedidoLivros.pedidoslivros;
                    }
                    return labelObject;
                });
            });
        };
        var errorCallback = function() {
            $location.path("/PedidoLivros");
        };
        PedidoLivrosResource.get({PedidoLivrosId:$routeParams.PedidoLivrosId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.pedidoLivros);
    };

    $scope.save = function() {
        var successCallback = function(){
            $scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.pedidoLivros.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/PedidoLivros");
    };

    $scope.remove = function() {
        var successCallback = function() {
            $location.path("/PedidoLivros");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        }; 
        $scope.pedidoLivros.$remove(successCallback, errorCallback);
    };
    
    $scope.$watch("pedidoslivrosSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.pedidoLivros.pedidoslivros = {};
            $scope.pedidoLivros.pedidoslivros.id = selection.value;
        }
    });
    
    $scope.get();
});