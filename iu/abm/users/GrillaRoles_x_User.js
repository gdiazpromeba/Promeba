GrillaRoles_x_User = Ext.extend(Ext.grid.GridPanel, {
	constructor : function(config) {
		GrillaRoles_x_User.superclass.constructor.call(this, Ext.apply({
			store : new Ext.data.Store({
				proxy : new Ext.data.HttpProxy({
					url : '/prototipo/svc/conector/usuarios/seleccionaRoles'
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
                    {name : 'usuarioId', type : 'string', allowBlank : false },
                    {name : 'rolId', type : 'string', allowBlank : false },
                    {name : 'rolNombre', type : 'string', allowBlank : false }
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
              {header : 'usuarioId', dataIndex : 'usuarioId', hidden : true},
              {header : 'rolId', dataIndex : 'rolId', hidden : true},
              {header : 'Nombre del rol', dataIndex : 'rolNombre', width : 150, sortable : true}
            ],
			selModel : new Ext.grid.RowSelectionModel({
						singleSelect : true
			}),
		    id : 'grillaRoles',
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
