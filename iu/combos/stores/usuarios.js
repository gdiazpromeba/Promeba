var dsUsuarios = new Ext.data.JsonStore({
    url: '/prototipo/svc/conector/usuarios.php/selecciona',
    root: 'data',
    fields: ['usuarioId', 'usuarioNombreCompleto']
});

var dsUsuariosHabilitadores = new Ext.data.JsonStore({
    url: '/prototipo/svc/conector/usuarios.php/selecciona',
    root: 'data',
    baseParams: {grupoId: 'c163fa72050260bb74b3a8dac092d4a4'},
    fields: ['usuarioId', 'usuarioNombreCompleto']
});