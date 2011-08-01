Grilla = Ext.extend(Ext.grid.GridPanel, {
	constructor : function(config) {
	  Grilla.superclass.constructor.call(this, Ext.apply({
      region: 'center',
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
	  }, config));
	}, //constructor
	
  	obtieneSeleccionado : function() {
		var sm = this.getSelectionModel();
		if (sm.getSelected() != null) {
			return sm.getSelected();
		} else {
			return null;
		}
	}	
});
