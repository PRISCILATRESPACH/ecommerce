
angular.module('ecommerce').controller('NewPedidoLivrosController', function ($scope, $location, locationParser, PedidoLivrosResource ) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.pedidoLivros = $scope.pedidoLivros || {};
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $location.path('/PedidoLivros/edit/' + id);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        PedidoLivrosResource.save($scope.pedidoLivros, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/PedidoLivros");
    };
});