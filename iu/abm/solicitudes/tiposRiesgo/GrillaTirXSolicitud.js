GrillaTirXSolicitud = Ext.extend(GrillaABM, {
	constructor : function(config) {
		GrillaTirXSolicitud.superclass.constructor.call(this, Ext.apply({
			store : new Ext.data.Store({
				proxy : new Ext.data.HttpProxy({
					url : '/prototipo/svc/conector/solicitudes/seleccionaTiposRiesgo'
                }),
                baseParams: {start: 0, limit: 15},
                remoteSort: true,
                autoLoad: false,
                tituloHijo: 'tirXSolicitudDescripcion',
        		reader : new Ext.data.JsonReader({
        		  root : 'data',
        		  totalProperty : 'total',
        		  idProperty : 'id',
        		  fields : [
                    {name : 'id', type : 'string', allowBlank : false },
                    {name : 'tirId', type : 'string', allowBlank : false },
                    {name : 'tirNombre', type : 'string', allowBlank : false },
                    {name : 'solicitudId', type : 'string', allowBlank : false },
                    {name : 'tirXSolicitudDescripcion', type : 'string', allowBlank : true }
                  ]
        		}),
		        autoDestroy : true
	        }),
			columns : [
              {header : 'id', dataIndex : 'id', hidden : true},
              {header : 'Tipo de riesgo', dataIndex : 'tirNombre', width : 200, sortable : true},
              {header : 'Descripción', dataIndex : 'tirXSolicitudDescripcion', width : 450, sortable : true}
            ],
		}, config));
	} //constructor
});
