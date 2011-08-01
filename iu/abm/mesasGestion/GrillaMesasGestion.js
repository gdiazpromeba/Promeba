GrillaMesasGestion = Ext.extend(GrillaABM, {
	constructor : function(config) {
		GrillaMesasGestion.superclass.constructor.call(this, Ext.apply({
			store : new Ext.data.Store({
				proxy : new Ext.data.HttpProxy({
					url : '/prototipo/svc/conector/mesasGestion/selecciona'
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
                    {name : 'fechaMesaGestion', type : 'date',  allowBlank : true,  dateFormat: 'Y-m-d' },
                    {name : 'fechaActaAcuerdo', type : 'date',  allowBlank : true,  dateFormat: 'Y-m-d' },
                    {name : 'estado', type : 'String',  allowBlank : false }
                  ]
        		}),
		        autoDestroy : true
	        }),
			columns : [
              {header : 'id', dataIndex : 'id', hidden : true},
              {header : 'Mesa de gestión', dataIndex : 'fechaMesaGestion', width : 90, sortable : true, renderer: Ext.util.Format.dateRenderer('d/m/Y')},
              {header : 'Acta de acuerdo', dataIndex : 'fechaActaAcuerdo', width : 90, sortable : true, renderer: Ext.util.Format.dateRenderer('d/m/Y')},
              {header : 'Estado', dataIndex : 'estado', width : 130, sortable : true}
            ],
		}, config));
	} //constructor
});
