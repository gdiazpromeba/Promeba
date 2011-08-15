GrillaSpdXSolicitud = Ext.extend(GrillaABM, {
	constructor : function(config) {
		GrillaSpdXSolicitud.superclass.constructor.call(this, Ext.apply({
			store : new Ext.data.Store({
				proxy : new Ext.data.HttpProxy({
					url : '/prototipo/svc/conector/solicitudes/seleccionaServiciosPublicosDisponibles'
                }),
                baseParams: {start: 0, limit: 15},
                remoteSort: true,
                autoLoad: false,
                tituloHijo: 'spdXSolicitudDescripcion',
        		reader : new Ext.data.JsonReader({
        		  root : 'data',
        		  totalProperty : 'total',
        		  idProperty : 'id',
        		  fields : [
                    {name : 'id', type : 'string', allowBlank : false },
                    {name : 'spdId', type : 'string', allowBlank : false },
                    {name : 'spdNombre', type : 'string', allowBlank : false },
                    {name : 'solicitudId', type : 'string', allowBlank : false },
                    {name : 'spdXSolicitudDescripcion', type : 'string', allowBlank : true }
                  ]
        		}),
		        autoDestroy : true
	        }),
			columns : [
              {header : 'id', dataIndex : 'id', hidden : true},
              {header : 'Servicio Público', dataIndex : 'spdNombre', width : 200, sortable : true},
              {header : 'Descripción', dataIndex : 'spdXSolicitudDescripcion', width : 450, sortable : true}
            ],
		}, config));
	} //constructor
});
