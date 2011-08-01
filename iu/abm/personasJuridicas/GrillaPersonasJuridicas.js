GrillaPersonasJuridicas = Ext.extend(GrillaABM, {
	constructor : function(config) {
		GrillaPersonasJuridicas.superclass.constructor.call(this, Ext.apply({
			store : new Ext.data.Store({
				proxy : new Ext.data.HttpProxy({
					url : '/prototipo/svc/conector/personasJuridicas/selecciona'
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
                    {name : 'inscripcion', type : 'date',  allowBlank : true,  dateFormat: 'Y-m-d' },
                    {name : 'personeria', type : 'string', allowBlank : false },
                    {name : 'cuit', type : 'string', allowBlank : false },
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
                    {name : 'ocupacion', type : 'string', allowBlank : true },
                    {name : 'sexo', type : 'string', allowBlank : true },
                    
                  ]
        		}),
		        autoDestroy : true
	        }),
			columns : [
              {header : 'id', dataIndex : 'usuarioId', hidden : true},
              {header : 'Nombre', dataIndex : 'nombre', width : 370, sortable : true},
            ],

		}, config));
	} //constructor
});
