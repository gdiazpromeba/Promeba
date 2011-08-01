GrillaABM = Ext.extend(Ext.grid.GridPanel, {
	constructor : function(config) {
		GrillaABM.superclass.constructor.call(this, Ext.apply({
			bbar : new Ext.PagingToolbar({
						pageSize : 15,
						displayInfo : true
			}),
			selModel : new Ext.grid.RowSelectionModel({
						singleSelect : true
			}),
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
              var titulo=registro.get(this.config.tituloHijo);
              return titulo;
            }
	
		}, config));
	} //constructor
});
