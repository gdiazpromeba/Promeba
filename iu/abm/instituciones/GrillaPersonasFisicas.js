GrillaPersonasFisicas = Ext.extend(GrillaABM, {
	constructor : function(config) {
		GrillaPersonasFisicas.superclass.constructor.call(this, Ext.apply({
			store : new Ext.data.Store({
				proxy : new Ext.data.HttpProxy({
					url : '/prototipo/svc/conector/personasFisicas/selecciona'
                }),
                baseParams: {start: 0, limit: 15},
                remoteSort: true,
                autoLoad: true,
        		reader : new Ext.data.JsonReader({
        		  root : 'data',
        		  totalProperty : 'total',
        		  idProperty : 'id',
        		  fields : [
                    {name : 'id', type : 'string', allowBlank : false },
                    {name : 'nombre', type : 'string', allowBlank : false },
                    {name : 'apellido', type : 'string', allowBlank : false },
                    {name : 'tipoDocumentoNombre', type : 'string', allowBlank : false },
                    {name : 'tipoDocumentoId', type : 'string', allowBlank : false },
                    {name : 'documentoNumero', type : 'int', allowBlank : false },
                    {name : 'domicilioId', type : 'string', allowBlank : true },
                    {name : 'calle', type : 'string', allowBlank : true },
                    {name : 'numero', type : 'int', allowBlank : true },
                    {name : 'piso', type : 'string', allowBlank : true },
                    {name : 'departamento', type : 'string', allowBlank : true },
                    {name : 'regionId', type : 'string', allowBlank : true },
                    {name : 'regionNombre', type : 'string', allowBlank : true },
                    {name : 'provinciaId', type : 'string', allowBlank : true },
                    {name : 'provinciaNombre', type : 'string', allowBlank : true },
                    {name : 'departamentoId', type : 'string', allowBlank : true },
                    {name : 'departamentoNombre', type : 'string', allowBlank : true },
                    {name : 'localidadId', type : 'string', allowBlank : true },
                    {name : 'localidadNombre', type : 'string', allowBlank : true },
                    {name : 'codigoPostal', type : 'string', allowBlank : true },
                    {name : 'barrio', type : 'string', allowBlank : true },
                    {name : 'sector', type : 'string', allowBlank : true },
                    {name : 'manzana', type : 'string', allowBlank : true },
                    {name : 'tipoDomicilio', type : 'string', allowBlank : true }
                  ]
        		}),
		        autoDestroy : true
	        }),
			columns : [
              {header : 'id', dataIndex : 'usuarioId', hidden : true},
              {header : 'Nombre', dataIndex : 'nombre', width : 150, sortable : true},
              {header : 'Apellido', dataIndex : 'apellido', width : 150, sortable : true}
            ],

		}, config));
	} //constructor
});
