GrillaEqSocXProyecto = Ext.extend(GrillaABM, {
	constructor : function(config) {
		GrillaEqSocXProyecto.superclass.constructor.call(this, Ext.apply({
			store : new Ext.data.Store({
				proxy : new Ext.data.HttpProxy({
					url : '/prototipo/svc/conector/proyectos/seleccionaEquipamientosSociales'
                }),
                baseParams: {start: 0, limit: 15},
                remoteSort: true,
                autoLoad: false,
                tituloHijo: 'eqSocXProyectoDescripcion',
        		reader : new Ext.data.JsonReader({
        		  root : 'data',
        		  totalProperty : 'total',
        		  idProperty : 'id',
        		  fields : [
                    {name : 'id', type : 'string', allowBlank : false },
                    {name : 'eqSocId', type : 'string', allowBlank : false },
                    {name : 'eqSocNombre', type : 'string', allowBlank : false },
                    {name : 'proyectoId', type : 'string', allowBlank : false },
                    {name : 'eqSocXProyectoDescripcion', type : 'string', allowBlank : true }
                  ]
        		}),
		        autoDestroy : true
	        }),
			columns : [
              {header : 'id', dataIndex : 'id', hidden : true},
              {header : 'Equipamiento Social', dataIndex : 'eqSocNombre', width : 200, sortable : true},
              {header : 'Descripción', dataIndex : 'eqSocXProyectoDescripcion', width : 450, sortable : true}
            ],
		}, config));
	} //constructor
});
