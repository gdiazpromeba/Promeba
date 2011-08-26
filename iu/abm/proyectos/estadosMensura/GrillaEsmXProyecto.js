GrillaEsmXProyecto = Ext.extend(GrillaABM, {
	constructor : function(config) {
		GrillaEsmXProyecto.superclass.constructor.call(this, Ext.apply({
			store : new Ext.data.Store({
				proxy : new Ext.data.HttpProxy({
					url : '/prototipo/svc/conector/proyectos/seleccionaEstadosMensura'
                }),
                baseParams: {start: 0, limit: 15},
                remoteSort: true,
                autoLoad: false,
                tituloHijo: 'esmXProyectoDescripcion',
        		reader : new Ext.data.JsonReader({
        		  root : 'data',
        		  totalProperty : 'total',
        		  idProperty : 'id',
        		  fields : [
                    {name : 'id', type : 'string', allowBlank : false },
                    {name : 'esmId', type : 'string', allowBlank : false },
                    {name : 'esmNombre', type : 'string', allowBlank : false },
                    {name : 'proyectoId', type : 'string', allowBlank : false },
                    {name : 'esmXProyectoDescripcion', type : 'string', allowBlank : true }
                  ]
        		}),
		        autoDestroy : true
	        }),
			columns : [
              {header : 'id', dataIndex : 'id', hidden : true},
              {header : 'Estado de mensura', dataIndex : 'esmNombre', width : 200, sortable : true},
              {header : 'Descripción', dataIndex : 'esmXProyectoDescripcion', width : 450, sortable : true}
            ],
		}, config));
	} //constructor
});
