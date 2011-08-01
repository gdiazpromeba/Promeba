GrillaUsers = Ext.extend(Ext.grid.GridPanel, {
	constructor : function(config) {
		GrillaUsers.superclass.constructor.call(this, Ext.apply({
			store : new Ext.data.Store({
				proxy : new Ext.data.HttpProxy({
					url : '/prototipo/svc/conector/usuarios/selecciona'
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
                    {name : 'login', type : 'string', allowBlank : false },
                    {name : 'email', type : 'string', allowBlank : false },
                    {name : 'clave', type : 'string', allowBlank : false },
                    {name : 'personaId', type : 'string', allowBlank : false },
                    {name : 'personaDenominacion', type : 'string', allowBlank : false }
                  ]
        		}),
		        autoDestroy : true
	        }),
			bbar : new Ext.PagingToolbar({
						pageSize : 15,
						displayInfo : true
					}),
			columns : [
              {header : 'id', dataIndex : 'usuarioId', hidden : true},
              {header : 'Login', dataIndex : 'login', width : 150, sortable : true},
              {header : 'Email', dataIndex : 'email', width : 150, sortable : true},
              {header : 'Denominación', dataIndex : 'personaDenominacion', width : 350, sortable : true}
            ],
			selModel : new Ext.grid.RowSelectionModel({
						singleSelect : true
			}),
		    id : 'grillaUsuarios',
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
