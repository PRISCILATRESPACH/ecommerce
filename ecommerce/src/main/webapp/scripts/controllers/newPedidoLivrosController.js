
angular.module('ecommerce').controller('NewPedidoLivrosController', function ($scope, $location, locationParser, PedidoLivrosResource , PedidosResource) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.pedidoLivros = $scope.pedidoLivros || {};
    
    $scope.pedidoslivrosList = PedidosResource.queryAll(function(items){
        $scope.pedidoslivrosSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.id
            });
        });
    });
    $scope.$watch("pedidoslivrosSelection", function(selection) {
        if ( typeof selection != 'undefined') {
            $scope.pedidoLivros.pedidoslivros = {};
            $scope.pedidoLivros.pedidoslivros.id = selection.value;
        }
    });
    

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