PanelUsuarios = Ext.extend(Ext.TabPanel, {
  constructor : function(config) {
    PanelUsuarios.superclass.constructor.call(this, Ext.apply({
      id: 'panelUsuarios',
      region: 'center',
      activeItem: 0, 
      deferredRender : false,
      listeners: {
        render: function(me) {
          var cabecera=me.creaTabCabecera(me);
          var roles=me.creaTabRoles(me);
          me.agregaTabs(cabecera, roles);
        }
      }  
 
    }, config));
    
    var panForm;
    var panBusqueda;
    var panGrilla;
    var grillaDet;
    var formDet;
  }, //constructor
  
  creaTabCabecera :  function(me){
    me.panForm=new FormUsers({
      region: 'center'
    });
    me.panBusqueda=new  BusquedaUsers({
      region: 'west',
      width: 400,
    });
    var panSuperior=new Ext.Container({
        layout: 'border',
        region: 'north',
        height: 330,
        items: [me.panForm, me.panBusqueda]
     });
     me.panGrilla = new GrillaUsers({
       region: 'center'
     });
     var panelAbm=new Ext.Panel({
         title: 'Usuarios',
         id: 'panAbmUsers',
         name: 'panAbmUsers',
         height: 500,
         layout: 'border',
         items: [
           panSuperior,      
           me.panGrilla
         ]
     });
     return panelAbm;
  },
  
  creaTabRoles : function(me){
     me.formDet=new FormRoles_x_User({
       region: 'north',
       height: 170
     });
     me.grillaDet = new GrillaRoles_x_User({
       region:'center'
     });
     var panelDet=new Ext.Panel({
       title: '[Roles]',
       id: 'rolesXUsuario',
       name: 'rolesXUsuario',
       height: 500,
       layout: 'border',
       items: [
         me.formDet,      
	     me.grillaDet
       ]
     });
     return panelDet;      
  }, 
  
  agregaTabs : function(cabecera, roles){
    this.add(cabecera);
    this.add(roles);
    
     abmIza(this.panGrilla, this.panForm, this.panBusqueda);
     cabeceraDetalle(this.grillaDet, this.formDet, this.panGrilla); 
  }
  
     
  
});