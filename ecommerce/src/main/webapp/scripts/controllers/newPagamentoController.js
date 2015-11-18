
angular.module('ecommerce').controller('NewPagamentoController', function ($scope, $location, locationParser, PagamentoResource ) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.pagamento = $scope.pagamento || {};
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $location.path('/Pagamentos/edit/' + id);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        PagamentoResource.save($scope.pagamento, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Pagamentos");
    };
});