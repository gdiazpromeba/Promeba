GrillaTirXProyecto = Ext.extend(GrillaABM, {
	constructor : function(config) {
		GrillaTirXProyecto.superclass.constructor.call(this, Ext.apply({
			store : new Ext.data.Store({
				proxy : new Ext.data.HttpProxy({
					url : '/prototipo/svc/conector/proyectos/seleccionaTiposRiesgo'
                }),
                baseParams: {start: 0, limit: 15},
                remoteSort: true,
                autoLoad: false,
                tituloHijo: 'tirXProyectoDescripcion',
        		reader : new Ext.data.JsonReader({
        		  root : 'data',
        		  totalProperty : 'total',
        		  idProperty : 'id',
        		  fields : [
                    {name : 'id', type : 'string', allowBlank : false },
                    {name : 'tirId', type : 'string', allowBlank : false },
                    {name : 'tirNombre', type : 'string', allowBlank : false },
                    {name : 'proyectoId', type : 'string', allowBlank : false },
                    {name : 'tirXProyectoDescripcion', type : 'string', allowBlank : true }
                  ]
        		}),
		        autoDestroy : true
	        }),
			columns : [
              {header : 'id', dataIndex : 'id', hidden : true},
              {header : 'Tipo de riesgo', dataIndex : 'tirNombre', width : 200, sortable : true},
              {header : 'Descripción', dataIndex : 'tirXProyectoDescripcion', width : 450, sortable : true}
            ],
		}, config));
	} //constructor
});
