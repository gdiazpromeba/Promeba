   
Ext.onReady(function(){
	

	var panel=new Ext.Panel({
  		  frame:true,
	      width:'100%',
	      autoHeight:true,
	      //layout:'fit',
	      title:'Panel Lindo:',
	      items: [cabeceraRecepciones]
	});
	


   var win = new Ext.Window({
//        layout:'fit',
        width:300,
        height:150,
        closable: false,
        resizable: false,
        plain: true,
        border: false,
        items: [panel]
	});
   
	win.show();
	
	
});
	
