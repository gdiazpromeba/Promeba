GrillaSubejecutores = Ext.extend(GrillaABM, {
	constructor : function(config) {
		GrillaSubejecutores.superclass.constructor.call(this, Ext.apply({
			store : new Ext.data.Store({
				proxy : new Ext.data.HttpProxy({
					url : '/prototipo/svc/conector/subejecutores/selecciona'
                }),
                baseParams: {start: 0, limit: 15},
                remoteSort: true,
                autoLoad: false,
                tituloHijo: 'nombre',
        		reader : new Ext.data.JsonReader({
        		  root : 'data',
        		  totalProperty : 'total',
        		  idProperty : 'id',
        		  fields : [
                    {name : 'id', type : 'string', allowBlank : false },
                    {name : 'nombre', type : 'string', allowBlank : false },
                    {name : 'email', type : 'string', allowBlank : false },
                    {name : 'caracter', type : 'string', allowBlank : false },
                    {name : 'contacto', type : 'string', allowBlank : false },
                    {name : 'legitimacion', type : 'date',  allowBlank : false,  dateFormat: 'd/m/Y' },
                    {name : 'convenio', type : 'string', allowBlank : false },
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
                    {name : 'tipoDomicilio', type : 'string', allowBlank : true },
                    {name : 'personaId', type : 'string', allowBlank : true },
                    {name : 'personaDenominacion', type : 'string', allowBlank : true },
                  ]
        		}),
		        autoDestroy : true
	        }),
			columns : [
              {header : 'id', dataIndex : 'id', hidden : true},
              {header : 'Denominación', dataIndex : 'personaDenominacion', hidden : false},
              {header : 'Código', dataIndex : 'nombre', width : 120, sortable : true},
              {header : 'personaId', dataIndex : 'personaId', hidden : true},
              {header : 'Carácter', dataIndex : 'caracter', width : 150, sortable : true},
              {header : 'Región', dataIndex : 'regionNombre', width : 150, sortable : true},
              {header : 'Provincia', dataIndex : 'provinciaNombre', width : 150, sortable : true},
              {header : 'Legitimación', dataIndex : 'legitimacion', width : 90, sortable : true, renderer: Ext.util.Format.dateRenderer('d/m/Y')},
              {header : 'Email', dataIndex : 'email', width : 150, sortable : true}
            ],
		    id : 'grillaSubejecutores',
		}, config));
	} //constructor
});
