

angular.module('ecommerce').controller('EditPedidoLivrosController', function($scope, $routeParams, $location, PedidoLivrosResource ) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.pedidoLivros = new PedidoLivrosResource(self.original);
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
    
    
    $scope.get();
});