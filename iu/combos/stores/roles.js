function getDsRoles(){

  var dsRoles = new Ext.data.JsonStore({
      url: '/prototipo/svc/conector/roles/selecciona?start=0&limit=100',
      root: 'data',
      fields: ['id', 'nombre']
   });
   
   return dsRoles;
 
}


