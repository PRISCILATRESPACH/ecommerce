angular.module('ecommerce').factory('LivroResource', function($resource){
    var resource = $resource('rest/livros/:LivroId',{LivroId:'@id'},{'queryAll':{method:'GET',isArray:true},'query':{method:'GET',isArray:false},'update':{method:'PUT'}});
    return resource;
});