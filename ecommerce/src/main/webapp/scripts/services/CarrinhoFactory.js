angular.module('ecommerce').factory('CarrinhoResource', function($resource){
    var resource = $resource('rest/carrinhos/:CarrinhoId',{CarrinhoId:'@id'},{'queryAll':{method:'GET',isArray:true},'query':{method:'GET',isArray:false},'update':{method:'PUT'}});
    return resource;
});