

angular.module('ecommerce').controller('EditPedidosController', function($scope, $routeParams, $location, PedidosResource , PedidoLivrosResource, UsuarioResource) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.pedidos = new PedidosResource(self.original);
            PedidoLivrosResource.queryAll(function(items) {
                $scope.pedidoslivrosSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.id
                    };
                    if($scope.pedidos.pedidoslivros){
                        $.each($scope.pedidos.pedidoslivros, function(idx, element) {
                            if(item.id == element.id) {
                                $scope.pedidoslivrosSelection.push(labelObject);
                                $scope.pedidos.pedidoslivros.push(wrappedObject);
                            }
                        });
                        self.original.pedidoslivros = $scope.pedidos.pedidoslivros;
                    }
                    return labelObject;
                });
            });
            UsuarioResource.queryAll(function(items) {
                $scope.usuarioSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.id
                    };
                    if($scope.pedidos.usuario && item.id == $scope.pedidos.usuario.id) {
                        $scope.usuarioSelection = labelObject;
                        $scope.pedidos.usuario = wrappedObject;
                        self.original.usuario = $scope.pedidos.usuario;
                    }
                    return labelObject;
                });
            });
        };
        var errorCallback = function() {
            $location.path("/Pedidos");
        };
        PedidosResource.get({PedidosId:$routeParams.PedidosId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.pedidos);
    };

    $scope.save = function() {
        var successCallback = function(){
            $scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.pedidos.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/Pedidos");
    };

    $scope.remove = function() {
        var successCallback = function() {
            $location.path("/Pedidos");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        }; 
        $scope.pedidos.$remove(successCallback, errorCallback);
    };
    
    $scope.pedidoslivrosSelection = $scope.pedidoslivrosSelection || [];
    $scope.$watch("pedidoslivrosSelection", function(selection) {
        if (typeof selection != 'undefined' && $scope.pedidos) {
            $scope.pedidos.pedidoslivros = [];
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;
                $scope.pedidos.pedidoslivros.push(collectionItem);
            });
        }
    });
    $scope.$watch("usuarioSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.pedidos.usuario = {};
            $scope.pedidos.usuario.id = selection.value;
        }
    });
    
    $scope.get();
});