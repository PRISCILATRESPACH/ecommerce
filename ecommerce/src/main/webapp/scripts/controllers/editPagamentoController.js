

angular.module('ecommerce').controller('EditPagamentoController', function($scope, $routeParams, $location, PagamentoResource ) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.pagamento = new PagamentoResource(self.original);
        };
        var errorCallback = function() {
            $location.path("/Pagamentos");
        };
        PagamentoResource.get({PagamentoId:$routeParams.PagamentoId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.pagamento);
    };

    $scope.save = function() {
        var successCallback = function(){
            $scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.pagamento.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/Pagamentos");
    };

    $scope.remove = function() {
        var successCallback = function() {
            $location.path("/Pagamentos");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        }; 
        $scope.pagamento.$remove(successCallback, errorCallback);
    };
    
    
    $scope.get();
});