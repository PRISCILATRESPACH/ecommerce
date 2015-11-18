angular.module('ecommerce').factory('PedidosResource', function($resource){
    var resource = $resource('rest/pedidos/:PedidosId',{PedidosId:'@id'},{'queryAll':{method:'GET',isArray:true},'query':{method:'GET',isArray:false},'update':{method:'PUT'}});
    return resource;
});