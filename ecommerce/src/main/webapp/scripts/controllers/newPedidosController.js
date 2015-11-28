
angular.module('ecommerce').controller('NewPedidosController', function ($scope, $location, locationParser, PedidosResource , PedidoLivrosResource, UsuarioResource) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.pedidos = $scope.pedidos || {};
    
    $scope.pedidoslivrosList = PedidoLivrosResource.queryAll(function(items){
        $scope.pedidoslivrosSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.id
            });
        });
    });
    $scope.$watch("pedidoslivrosSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.pedidos.pedidoslivros = [];
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;
                $scope.pedidos.pedidoslivros.push(collectionItem);
            });
        }
    });
    
    $scope.usuarioList = UsuarioResource.queryAll(function(items){
        $scope.usuarioSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.id
            });
        });
    });
    $scope.$watch("usuarioSelection", function(selection) {
        if ( typeof selection != 'undefined') {
            $scope.pedidos.usuario = {};
            $scope.pedidos.usuario.id = selection.value;
        }
    });
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $location.path('/Pedidos/edit/' + id);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        PedidosResource.save($scope.pedidos, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Pedidos");
    };
});