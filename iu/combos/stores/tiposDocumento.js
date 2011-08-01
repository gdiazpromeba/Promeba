var dsTiposDocumento = new Ext.data.JsonStore({
    url: '/prototipo/svc/conector/tiposDocumento.php/selecciona',
    root: 'data',
    fields: ['id', 'nombre']
});


