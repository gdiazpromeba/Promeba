function creaCombo(store, displayFieldName, valueFieldName, applyToName,  nombreCampoId, anchoMinimo){

	var search= new Ext.form.ComboBox({
			      store: store,
			      displayField: displayFieldName,
			      valueField: valueFieldName,
			      typeAhead: false,
			      hiddenName: nombreCampoId,
			      hiddenId: nombreCampoId,
			      loadingText: recursosInter.buscando,
			      typeAhead: false,
			      typeAheadDelay: 1000,
			      forceSelecion: true,
			      //width: 200,
			      minListWidth: 150, //<-- adding this line makes it work fine
		          pageSize:15,
			      hideTrigger:true,
			      tpl: new Ext.XTemplate(
					    '<tpl for="."><div class="search-item">',
				            '<h4>{' + displayFieldName + '}</h4>',
				        '</div></tpl>'
				    ),
			      minChars: 1,
			      applyTo:  applyToName,
			      itemSelector: 'div.search-item',
				  validator: function(value){
					  //uso la validación por intermedio del 
					  //formulario hijo, porque validar el combo es un kilombo
					  return true;
					}
	
	        });
	//esto se hace porque los combos se muestran mal en los tabs, si no están en la primera solapa
	if (anchoMinimo!=undefined){
		search.minListWidth=anchoMinimo;
	}
	return search;
}


function creaComboLocal(store, displayFieldName, valueFieldName, applyToName,  nombreCampoId, anchoMinimo){
	
	var search= new Ext.form.ComboBox({
			      store: store,
			      displayField: displayFieldName,
			      valueField: valueFieldName,
			      typeAhead: true,
			      hiddenName: nombreCampoId,
			      hiddenId: nombreCampoId,
			      forceSelecion: true,
			      //width: 200,
			      hideTrigger: false,
			      triggerAction: 'all',
			      mode: 'remote',
			      tpl: new Ext.XTemplate(
					    '<tpl for="."><div class="search-item">',
				            '<h4>{' + displayFieldName + '}</h4>',
				        '</div></tpl>'
				    ),
			      applyTo:  applyToName,
			      itemSelector: 'div.search-item'
	        });
	//esto se hace porque los combos se muestran mal en los tabs, si no están en la primera solapa
	if (anchoMinimo!=undefined){
		search.minListWidth=anchoMinimo;
	}	
	return search;
}