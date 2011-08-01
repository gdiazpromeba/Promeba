GrillaEqSocXSolicitud = Ext.extend(GrillaABM, {
	constructor : function(config) {
		GrillaEqSocXSolicitud.superclass.constructor.call(this, Ext.apply({
			store : new Ext.data.Store({
				proxy : new Ext.data.HttpProxy({
					url : '/prototipo/svc/conector/solicitudes/seleccionaEquipamientosSociales'
                }),
                baseParams: {start: 0, limit: 15},
                remoteSort: true,
                autoLoad: false,
                tituloHijo: 'eqSocXSolicitudDescripcion',
        		reader : new Ext.data.JsonReader({
        		  root : 'data',
        		  totalProperty : 'total',
        		  idProperty : 'id',
        		  fields : [
                    {name : 'id', type : 'string', allowBlank : false },
                    {name : 'eqSocId', type : 'string', allowBlank : false },
                    {name : 'eqSocNombre', type : 'string', allowBlank : false },
                    {name : 'solicitudId', type : 'string', allowBlank : false },
                    {name : 'eqSocXSolicitudDescripcion', type : 'string', allowBlank : true }
                  ]
        		}),
		        autoDestroy : true
	        }),
			columns : [
              {header : 'id', dataIndex : 'id', hidden : true},
              {header : 'Equipamiento Social', dataIndex : 'eqSocNombre', width : 200, sortable : true},
              {header : 'Descripción', dataIndex : 'eqSocXSolicitudDescripcion', width : 450, sortable : true}
            ],
		}, config));
	} //constructor
});
