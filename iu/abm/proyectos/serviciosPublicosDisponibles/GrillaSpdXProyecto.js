GrillaSpdXProyecto = Ext.extend(GrillaABM, {
	constructor : function(config) {
		GrillaSpdXProyecto.superclass.constructor.call(this, Ext.apply({
			store : new Ext.data.Store({
				proxy : new Ext.data.HttpProxy({
					url : '/prototipo/svc/conector/proyectos/seleccionaServiciosPublicosDisponibles'
                }),
                baseParams: {start: 0, limit: 15},
                remoteSort: true,
                autoLoad: false,
                tituloHijo: 'spdXProyectoDescripcion',
        		reader : new Ext.data.JsonReader({
        		  root : 'data',
        		  totalProperty : 'total',
        		  idProperty : 'id',
        		  fields : [
                    {name : 'id', type : 'string', allowBlank : false },
                    {name : 'spdId', type : 'string', allowBlank : false },
                    {name : 'spdNombre', type : 'string', allowBlank : false },
                    {name : 'proyectoId', type : 'string', allowBlank : false },
                    {name : 'spdXProyectoDescripcion', type : 'string', allowBlank : true }
                  ]
        		}),
		        autoDestroy : true
	        }),
			columns : [
              {header : 'id', dataIndex : 'id', hidden : true},
              {header : 'Servicio Público', dataIndex : 'spdNombre', width : 200, sortable : true},
              {header : 'Descripción', dataIndex : 'spdXProyectoDescripcion', width : 450, sortable : true}
            ],
		}, config));
	} //constructor
});
