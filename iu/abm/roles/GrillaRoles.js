GrillaRoles = Ext.extend(Ext.grid.GridPanel, {
	constructor : function(config) {
		GrillaRoles.superclass.constructor.call(this, Ext.apply({
			store : new Ext.data.Store({
				proxy : new Ext.data.HttpProxy({
					url : '/prototipo/svc/conector/roles/selecciona'
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
                    {name : 'nombre', type : 'string', allowBlank : false },                   ]
        		}),
		        autoDestroy : true
	        }),
			bbar : new Ext.PagingToolbar({
						pageSize : 15,
						displayInfo : true
					}),
			columns : [
              {header : 'id', dataIndex : 'usuarioId', hidden : true},
              {header : 'Nombre', dataIndex : 'nombre', width : 150, sortable : true}
            ],
			selModel : new Ext.grid.RowSelectionModel({
						singleSelect : true
			}),
		    id : 'grillaAreasFuncionales',
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
              var titulo=registro.get('nombre');
              return titulo;
            }

		}, config));
	} //constructor
});
