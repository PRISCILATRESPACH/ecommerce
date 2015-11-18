angular.module('ecommerce').factory('PagamentoResource', function($resource){
    var resource = $resource('rest/pagamentos/:PagamentoId',{PagamentoId:'@id'},{'queryAll':{method:'GET',isArray:true},'query':{method:'GET',isArray:false},'update':{method:'PUT'}});
    return resource;
});