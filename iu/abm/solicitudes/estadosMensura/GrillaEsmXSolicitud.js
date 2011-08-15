GrillaEsmXSolicitud = Ext.extend(GrillaABM, {
	constructor : function(config) {
		GrillaEsmXSolicitud.superclass.constructor.call(this, Ext.apply({
			store : new Ext.data.Store({
				proxy : new Ext.data.HttpProxy({
					url : '/prototipo/svc/conector/solicitudes/seleccionaEstadosMensura'
                }),
                baseParams: {start: 0, limit: 15},
                remoteSort: true,
                autoLoad: false,
                tituloHijo: 'esmXSolicitudDescripcion',
        		reader : new Ext.data.JsonReader({
        		  root : 'data',
        		  totalProperty : 'total',
        		  idProperty : 'id',
        		  fields : [
                    {name : 'id', type : 'string', allowBlank : false },
                    {name : 'esmId', type : 'string', allowBlank : false },
                    {name : 'esmNombre', type : 'string', allowBlank : false },
                    {name : 'solicitudId', type : 'string', allowBlank : false },
                    {name : 'esmXSolicitudDescripcion', type : 'string', allowBlank : true }
                  ]
        		}),
		        autoDestroy : true
	        }),
			columns : [
              {header : 'id', dataIndex : 'id', hidden : true},
              {header : 'Estado de mensura', dataIndex : 'esmNombre', width : 200, sortable : true},
              {header : 'Descripción', dataIndex : 'esmXSolicitudDescripcion', width : 450, sortable : true}
            ],
		}, config));
	} //constructor
});
