GrillaSolicitudes = Ext.extend(Ext.grid.GridPanel, {
	constructor : function(config) {
		GrillaSolicitudes.superclass.constructor.call(this, Ext.apply({
			store : new Ext.data.Store({
				proxy : new Ext.data.HttpProxy({
					url : '/prototipo/svc/conector/solicitudes/selecciona'
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
                    {name : 'descripcion', type : 'string', allowBlank : false },
                    {name : 'subejecutorId', type : 'string', allowBlank : false },
                    {name : 'subejecutorNombre', type : 'string', allowBlank : false },
                    {name : 'estado', type : 'string', allowBlank : false },
                    {name : 'cantidadLotes', type : 'int', allowBlank : false },
                    {name : 'presupuestoEstimado', type : 'float', allowBlank : false },
                    {name : 'fechaDesde', type : 'date',  allowBlank : false,  dateFormat: 'Y-m-d' },
                    {name : 'fechaHasta', type : 'date',  allowBlank : false,  dateFormat: 'Y-m-d'  },
                    {name : 'fechaIngresoPOA', type : 'date',  allowBlank : false,  dateFormat: 'Y-m-d'  },
                    {name : 'fechaIngresoPGEP', type : 'date',  allowBlank : false,  dateFormat: 'Y-m-d'  },
                    {name : 'fechaIngresoPA', type : 'date',  allowBlank : false,  dateFormat: 'Y-m-d'  },
                    {name : 'vinculo', type : 'string', allowBlank : true },
                    {name : 'situacionDominialId', type : 'string', allowBlank : true },
                    {name : 'situacionDominialNombre', type : 'string', allowBlank : true },
                    {name : 'tipoInversionId', type : 'string', allowBlank : true },
                    {name : 'tipoInversionNombre', type : 'string', allowBlank : true }
                  ]
        		}),
		        autoDestroy : true
	        }),
			bbar : new Ext.PagingToolbar({
						pageSize : 15,
						displayInfo : true
					}),
			columns : [
              {header : 'id', dataIndex : 'id', hidden : true},
              {header : 'Descripcion', dataIndex : 'descripcion', width : 200, sortable : true},
              {header : 'Subejecutor', dataIndex : 'subejecutorNombre', width : 150, sortable : true},
              {header : 'Presupuesto Estimado', dataIndex : 'presupuestoEstimado', width : 150, sortable : false, align: 'right', renderer: Ext.util.Format.usMoney},
              {header : 'Lotes', dataIndex : 'cantidadLotes', width : 100, align: 'right', sortable : false},
              {header : 'Estado', dataIndex : 'estado', width : 100, sortable : true},
              {header : 'Ingreso POA', dataIndex : 'fechaIngresoPOA', width : 90, sortable : true, renderer: Ext.util.Format.dateRenderer('d/m/Y')},
            ],
			selModel : new Ext.grid.RowSelectionModel({
						singleSelect : true
			}),
		    id : 'grillaSolicitudes',
		    listeners : {
			  render : {
				scope : this,
				fn : function() {
					var store = this.store;
					var ptool = this.getBottomToolbar();
					ptool.bindStore(store);
				}
			  }
		    },
	  	    obtieneSeleccionado : function() {
				var sm = this.getSelectionModel();
				if (sm.getSelected() != null) {
					return sm.getSelected();
				} else {
					return null;
				}
			},
      
            tituloHijo : function (){
              var registro=this.obtieneSeleccionado();
              var titulo=registro.get('descripcion');
              return titulo;
            }

		}, config));
	} //constructor
});
