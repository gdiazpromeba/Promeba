GrillaAreasFuncionalesXRol = Ext.extend(GrillaABM, {
	constructor : function(config) {
		GrillaAreasFuncionalesXRol.superclass.constructor.call(this, Ext.apply({
			store : new Ext.data.Store({
				proxy : new Ext.data.HttpProxy({
					url : '/prototipo/svc/conector/areasFuncionalesXRol/selecciona'
                }),
                baseParams: {start: 0, limit: 15},
                remoteSort: true,
                autoLoad: false,
                tituloHijo: 'fechaMesagestion',
        		reader : new Ext.data.JsonReader({
        		  root : 'data',
        		  totalProperty : 'total',
        		  idProperty : 'id',
        		  fields : [
                    {name : 'id', type : 'string', allowBlank : false },
                    {name : 'areaFuncionalId', type : 'string', allowBlank : false },
                    {name : 'areaFuncionalNombre', type : 'string', allowBlank : false },
                    {name : 'rolId', type : 'string', allowBlank : false },
                    {name : 'rolNombre', type : 'string', allowBlank : false },
                    {name : 'vision', type : 'bool', allowBlank : true },
                    {name : 'lectura', type : 'bool', allowBlank : true },
                    {name : 'escritura', type : 'bool', allowBlank : true },
                    {name : 'ejecucion', type : 'bool', allowBlank : true },
                    {name : 'impresion', type : 'bool', allowBlank : true }
                  ]
        		}),
		        autoDestroy : true
	        }),
			columns : [
              {header : 'id', dataIndex : 'id', hidden : true},
              {header : 'Área Funcional', dataIndex : 'areaFuncionalNombre', width : 200},
              {header : 'Rol', dataIndex : 'rolNombre', width : 150},
              {header : 'Visión', dataIndex:  'vision', width: 80, renderer:function(value){return (value == 1)?'Sí':'No';}},
              {header : 'Lectura', dataIndex:  'lectura', width: 80, renderer:function(value){return (value == 1)?'Sí':'No';}},
              {header : 'Escritura', dataIndex:  'escritura', width: 80, renderer:function(value){return (value == 1)?'Sí':'No';}},
              {header : 'Ejecución', dataIndex:  'ejecucion', width: 80, renderer:function(value){return (value == 1)?'Sí':'No';}},
              {header : 'Impresión', dataIndex:  'impresion', width: 80, renderer:function(value){return (value == 1)?'Sí':'No';}}
            ],
		}, config));
	} //constructor
});
