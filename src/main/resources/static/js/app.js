/**
 * @author jcuestas
 */

$(document).ready( () => {
	if(window.location.pathname.split("/").pop() === 'clientes'){
    	getPersonas();
    } else if(window.location.pathname.split("/").pop() === 'topestc'){
    	getTopesTC();
    } else if(window.location.pathname.split("/").pop() === 'entidadesbc'){
    	getEntidades();
    }
});

$('#formRegistro').submit((e)=>{
    e.preventDefault();

    console.log('Entra a enviar a guardar...');
    document.getElementById('btnRegistrar').disabled = true;
    document.getElementById('btnRegistrar').innerHTML = 'REGISTRANDO...';
    data = {
        'nombreCompleto': document.getElementById('nombreCompleto').value,
        'numeroIdentificacion': document.getElementById('numeroIdentificacion').value,
        'ingresos': parseFloat(document.getElementById('ingresos').value.replace(/[\,]/g,''))
    };
    
    sendPOSTRequestServer('/personas/new', 'insertPerson', data);

});

$('#formAsignaTC').submit((e)=>{
    e.preventDefault();
    
    document.getElementById('btnAsignarTC').disabled = true;
    document.getElementById('btnAsignarTC').innerHTML = 'ASIGNANDO...';
    
    let list = document.getElementById('listaDatosTC').childNodes;
    
    data = {
        'idPersona': {
        	'idPersona': document.getElementById('idPersonaHidden').value,
        },
        'idTope': {
        	'idTope': list[0].getAttribute('data-value'),
        	'marca': list[4].getAttribute('data-value'),
        	
        },
        'cupoTotal': parseFloat(list[2].getAttribute('data-value')),
        'interes': list[3].getAttribute('data-value'),
        'chip': document.getElementById('incluirChip').value
    };
    console.log(data);
    
    sendPOSTRequestServer('/tarjetas/new', 'asignarTarjeta', data);

});

formatearValor = (e) => {
	tecla = (document.all) ? e.keyCode : e.which;
	// Tecla de retroceso para borrar, siempre la permite
	if (tecla==8){
		return true;
	}
	
	let val = formatterPeso(e.target.value);
	
    document.getElementById('ingresos').value = val;    
};


limpiarFormRegistroCliente = () => {document.getElementById('formRegistro').reset();};

getPersonas = () => {
    sendGETRequestServer('/personas', 'listarPersonas');
};

getTarjetasDisponibles = (ingresos) => {
	sendGETRequestServer('/topes/mios/'+ingresos, 'listarTarjetasDisponibles');
};

getMisTarjetas = (idPersona) => {
	sendGETRequestServer('/tarjetas/mistarjetas/'+idPersona, 'listarMisTarjetasDisponibles');
};

getTopesTC = () => {
	sendGETRequestServer('/topes', 'listarTopes');
};

getEntidades = () => {
    sendGETRequestServer('/entidades', 'listarEntidades');
};

sendGETRequestServer = (urlRequest, option) => {
    fetch(urlRequest, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    }).then((response) => {
        return response.text();
    }).then((data) => {
        data = JSON.parse(data);
        if(data.codigo === 1){
        	if(option === 'listarPersonas') {
        		pintarTblClientes(data.datos);
        	} else if (option === 'listarTarjetasDisponibles') {
                pintarTblTCDisponibles(data.datos);
        	} else if (option === 'listarMisTarjetasDisponibles') {
        		pintarTblMisTarjetas(data.datos);
            } else if (option === 'listarTopes') {
            	pintarTblTopes(data.datos);
            } else if (option === 'listarEntidades') {
            	pintarTblEntidades(data.datos);
            }
        } else if (data.codigo === 0) {
        	if(option === 'listarTarjetasDisponibles') {
        		Swal.fire({
        			icon: 'warning',
        			text: 'No tenemos ningún producto en este momento para ofrecerle.'
        		}).then(() => {
                    window.location.reload();
                });;
        	} else {
        		Swal.fire({
        			icon: 'warning',
        			text: 'Sin resultados.'
        		});
        	}
        } else {
        	Swal.fire({
                icon: 'error',
                text: 'Algo salió mal al intentar procesar su solicitud.'
            });
        }
    }).catch((err)=> {
        console.log(err);
    });
}

sendPOSTRequestServer = (urlRequest, option, dataParams) => {
	fetch(urlRequest, {
        method: 'POST',
        body: JSON.stringify(dataParams),
        headers: {
            'Content-Type': 'application/json'
        }
    }).then((response) => {
        return response.text();
    }).then((data) => {
        console.log(data);
        data = JSON.parse(data);
        document.getElementById('btnRegistrar').innerHTML = 'REGISTRAR';
		document.getElementById('btnRegistrar').disabled = false;
		document.getElementById('btnAsignarTC').innerHTML = 'CONFIRMAR TARJETA';
		document.getElementById('btnAsignarTC').disabled = false;
        if(data.codigo === 1){
        	if(option === 'insertPerson') {
        		Swal.fire({
        			icon: 'success',
        			title: 'Registro Exitoso!',
        			html: 'Sus datos se registraron satisfactoriamente.',
        			allowEnterKey: false,
        			allowEscapeKey:false,
        			allowOutsideClick: false,
        			timer: 3000,
        			timerProgressBar: true,
        		});
        		
        		document.getElementById('idPersonaHidden').value = data.datos;
        		
        		$('#modalNewClient').modal('hide');
        		getTarjetasDisponibles(parseFloat(document.getElementById('ingresos').value.replace(/[\,]/g,'')));
        	} else if (option === 'asignarTarjeta') {
        		Swal.fire({
        			icon: 'success',
        			html: 'Tarjeta asignada correctamente.',
        			allowEnterKey: false,
        			allowEscapeKey:false,
        			allowOutsideClick: false,
        		}).then(() => {
        			limpiarFormRegistroCliente();
        			getPersonas();
        			$('#modalAsignaTC').modal('hide');
        		});
        	}
        } else if(data.codigo === -1) {
        	Swal.fire({
                icon: 'warning',
                text: 'Ya existe una persona con el número de idenficación ingresado.'
            });
        } else {
            Swal.fire({
                icon: 'error',
                text: 'Algo salió mal al intentar registrar sus datos.'
            });
        }
    }).catch((err)=> {
        console.log(err);
    });
};

pintarTblClientes = (data) => {
	let columns = [
        {"data" : "idPersona", "title": "ID"},
        {"data" : "nombreCompleto", "title": "NOMBRE COMPLETO"},
        {"data" : "numeroIdentificacion", "title": "IDENTIFICACIÓN"},
        {"data" : "ingresos", "title": "INGRESOS", render: $.fn.dataTable.render.number( ',', '.', 1 )},
        {"data" : "listaTarjetas", "title": "# TC", render: function (dataField) { if(dataField.length > 0){return '<a class="btn btn-info btn-sm" href="#" onclick="getRowClientes(this, \'misTC\')">Mis TC</a>';}else{return '<p class="bg-warning">Sin Tarjetas</p>'}}},
        {"data" : "", "title": "ACCIONES", render: function () { return '<a class="btn btn-success btn-sm" href="#" onclick="getRowClientes(this, \'tcDisponibles\')">Nueva Tarjeta</a>'; }}
    ];
	generarTabla('clientes', columns, data);
};

pintarTblMisTarjetas = (data) => {
	let columns = [
        {"data" : "idTope", "title": "ENTIDAD", render: function (dataField) {return dataField.idEntidad.nombreEntidad}},
        {"data" : "idTope", "title": "MARCA", render: function (dataField) {return dataField.marca}},
        {"data" : "cupoTotal", "title": "CUPON TOTAL", render: $.fn.dataTable.render.number( ',', '.', 1 )},
        {"data" : "cupoActual", "title": "CUPON ACTUAL", render: $.fn.dataTable.render.number( ',', '.', 1 )},
        {"data" : "idTope", "title": "INTERÉS FIJO", render: function (dataField) {return dataField.interesFijo + '%'}},
        {"data" : "numeroTarjeta", "title": "PAN"},
        {"data" : "estado", "title": "ESTADO"},
    ];
    $('#modalMisTC').modal('show');
    generarTabla('tblMisTarjetas', columns, data);
    setTimeout(() => {
    	$('#tblMisTarjetas').dataTable().fnAdjustColumnSizing();
	}, 1000);
};

pintarTblTopes = (data) => {
	let columns = [
        {"data" : "idTope", "title": "ID"},
        {"data" : "idEntidad", "title": "ENTIDAD", render: function (dataField) {return dataField.nombreEntidad}},
        {"data" : "rangoIngresos", "title": "RANGO INGRESOS"},
        {"data" : "montoPermitido", "title": "CUPO A APROBAR", render: $.fn.dataTable.render.number( ',', '.', 1 )},
        {"data" : "interesFijo", "title": "INTERÉS FIJO", render: function (dataField) {return dataField + '%'}},
        {"data" : "marca", "title": "MARCA"},
    ];
    generarTabla('tblTopes', columns, data);
};

pintarTblEntidades = (data) => {
	let columns = [
        {"data" : "idEntidad", "title": "ID"},
        {"data" : "nombreEntidad", "title": "ENTIDAD"},
    ];
    generarTabla('tblEntidades', columns, data);
};

pintarTblTCDisponibles = (data) => {
	let tbl = document.getElementById('tableTarjetasDisponibles');
	$("#tableTarjetasDisponibles > tbody").html("");
    let tbdy = document.createElement('tbody');

    for(let i=0; i<data.length; i++){
    	let tr = document.createElement('tr');
    	let td = document.createElement('td');
    	td.setAttribute('hidden', true);
    	td.appendChild(document.createTextNode(data[i].idTope));
    	tr.appendChild(td);
    	let td0 = document.createElement('td');
    	td0.setAttribute('style', 'text-align: center;');
        td0.appendChild(document.createTextNode(data[i].idEntidad.nombreEntidad));
        tr.appendChild(td0);
        let td1 = document.createElement('td');
        td1.setAttribute('style', 'text-align: center;');
        td1.appendChild(document.createTextNode(data[i].montoPermitido));
        tr.appendChild(td1);
        let td2 = document.createElement('td');
        td2.setAttribute('style', 'text-align: center;');
        td2.appendChild(document.createTextNode(data[i].interesFijo));
        tr.appendChild(td2);
        let td3 = document.createElement('td');
        td3.setAttribute('style', 'text-align: center;');
        td3.appendChild(document.createTextNode(data[i].marca));
        tr.appendChild(td3);
        let td4 = document.createElement('td');
        td4.setAttribute('style', 'text-align: center;');
        td4.insertAdjacentHTML('beforeend', '<a class="btn btn-success" href="#" onclick="getDataRowTarjeta(this)">LA QUIERO</a>');
        tr.appendChild(td4);
        tbdy.appendChild(tr);
    }
    tbl.appendChild(tbdy);
    $('#modalAsignaTC').modal('show');
}

getRowTable = (elem) => {
	let tbl = elem.parentNode.parentNode.parentNode.parentNode;
	let indexRow = elem.parentNode.parentNode.rowIndex;
	let dataRes = tbl.rows[indexRow].cells;

	return dataRes;
};

getRowClientes = (elem, option) => {
	let dataRes = getRowTable(elem);
	let ingresos = parseFloat(dataRes[3].innerText.replace(/[\,]/g,''));
    document.getElementById('idPersonaHidden').value = dataRes[0].innerText;
    if(option === 'tcDisponibles'){
        getTarjetasDisponibles(ingresos);
    } else if (option === 'misTC') {
        getMisTarjetas(dataRes[0].innerText);
    }
};

getDataRowTarjeta = (elem) =>{
	let dataRes = getRowTable(elem);
    let listDatos = document.getElementById('listaDatosTC');
    listDatos.innerHTML = '';
    let count = 0;
	for (let item of dataRes) {
        if(count < dataRes.length-1){
        	let li = document.createElement('li');
        	let valueText = item.innerText;
        	li.setAttribute('data-value', valueText);
        	
        	if(count === 0)
        		li.setAttribute('hidden', true);
        	
        	if(count === 1)
        		valueText = 'Entidad: '+ valueText;
        	if(count === 2)
        		valueText = 'Cupo Tarjeta: $ '+formatterPeso(valueText);
        	
        	if(count === 3)
        		valueText = 'Interés Fijo: '+ valueText+"%";
        	
        	if(count === 4)
        		valueText = 'Marca: '+ valueText;
        	
        	li.innerText = valueText;
        	listDatos.appendChild(li);
        	count++;
        }
	}
	
	document.getElementById('divTarjetas').setAttribute('style', 'display: none;');
	document.getElementById('divConfirmacion').setAttribute('style', 'display: block;');
	
};

/**
 * Pinta Tbla datatable clientes
 * @param data
 * @returns
 */
generarTabla = (idTbl, columns, data) => {
	$('#'+idTbl).dataTable({
		order : [ [ 0, "asc" ] ],
		clear: true,
		scrollX: true,
		destroy: true,
		autoWidth: false,
        data : data,
        columns: columns,
        lengthMenu: [
            [15, 30, 45, -1],
            ["15 Rows", "30 Rows", "45 Rows", "Todo"]
        ],
        language: {
            searchPlaceholder: "Buscar Datos",
            infoEmpty: "La tabla no tiene registros",
            zeroRecords: "Uppps, No tenemos resultados para tu busqueda",
        },
    });
};
