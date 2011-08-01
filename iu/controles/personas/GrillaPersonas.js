GrillaPersonas = Ext.extend(GrillaABM, {
	constructor : function(config) {
		GrillaPersonas.superclass.constructor.call(this, Ext.apply({
			store : new Ext.data.Store({
				proxy : new Ext.data.HttpProxy({
					url : '/prototipo/svc/conector/personas/selecciona'
                }),
                baseParams: {start: 0, limit: 15},
                remoteSort: true,
                autoLoad: false,
        		reader : new Ext.data.JsonReader({
        		  root : 'data',
        		  totalProperty : 'total',
        		  idProperty : 'id',
        		  fields : [
                    {name : 'id', type : 'string', allowBlank : false },
                    {name : 'denominacion', type : 'string', allowBlank : false },
                    {name : 'tipo', type : 'string', allowBlank : true }
                  ]
        		}),
		        autoDestroy : true
	        }),
			columns : [
              {header : 'id', dataIndex : 'usuarioId', hidden : true},
              {header : 'Denominación', dataIndex : 'denominacion', width : 300, sortable : true},
              {header : 'Tipo', dataIndex : 'tipo', width : 120, sortable : true}
            ],

		}, config));
	} //constructor
});
