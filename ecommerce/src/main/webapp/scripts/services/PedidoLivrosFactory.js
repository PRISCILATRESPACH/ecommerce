angular.module('ecommerce').factory('PedidoLivrosResource', function($resource){
    var resource = $resource('rest/pedidolivros/:PedidoLivrosId',{PedidoLivrosId:'@id'},{'queryAll':{method:'GET',isArray:true},'query':{method:'GET',isArray:false},'update':{method:'PUT'}});
    return resource;
});