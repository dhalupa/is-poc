isc.defineClass('ZewRestDataSource', 'RestDataSource').addProperties({
	dataFormat : 'json',
	operationBindings : [ {
		operationType : "fetch",
		dataProtocol : "postMessage"
	}, {
		operationType : "update",
		dataProtocol : "postMessage"
	}, {
		operationType : "add",
		dataProtocol : "postMessage"
	}, {
		operationType : "remove",
		dataProtocol : "postMessage"
	} ]
});
/**
 * Rest DataSource for Book entity
 */
isc.ZewRestDataSource.create({
	ID : "BookDS",
	dataURL : "api/book",
	fields : [ {
		name : 'id',
		type : 'integer',
		primaryKey : true,
		hidden : true
	}, {
		name : "author",
		width : 150,
		title : 'Autor'
	}, {
		name : "title",
		title : 'Title'
	}, {
		name : "genre",
		title : 'Genre', 
		width : 150,
		editorType : "select",
		displayField : 'name',
		valueField : 'id',
		optionDataSource : 'GenreDS'
	} ]
});
/**
 * Rest DataSource for Genre entity
 */
isc.ZewRestDataSource.create({
	ID : "GenreDS",
	dataURL : "api/genre",
	fields : [ {
		name : 'id',
		type : 'integer',
		primaryKey : true,
		hidden : true
	}, {
		name : "name",
		title : 'Name'
	} ]
});
