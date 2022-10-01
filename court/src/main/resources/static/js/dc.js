$(function () {
    var tbl = $('#tblList').DataTable({
        "pagingType": "full_numbers",
        "language": {
            "lengthMenu": "_MENU_",
            "paginate": {"previous": "&#10094;&#10094;", "first": "&#10094;", "next": "&#10095;&#10095;", "last": "&#10095;"},
            "processing": "Please wait..."
        },
        "dom": "<'row'<'col-sm-4'f><'col-sm-4'><'col-sm-4'B>><'row'<'col-sm-12'tr>><'row'<'col-sm-5'i><'col-sm-3'l><'col-sm-4'p>>",
        "scrollX": true,
        "start": 0,
        "length": 10,
        "stateSave": true,
        "responsive": true,
        "lengthMenu": [[10, 20, 30, -1], [10, 20, 30, 'All']],
        "processing": true,
        "serverSide": true,
        "ajax": {
            "url": "v1/high-courts",
            "type": "POST",
            "error" : function (jqXHR, textStatus, errorThrown){
            	/*if(jqXHR.status!=200){
            		var settings = $('#tblList').DataTable();
            		console.log(settings);
            	}*/
            	/*alert(jqXHR.status +" " + jqXHR.statusText);*/
            	$("#tblList_processing").hide();
            }
        },
        "columns": [
            {"targets": 0, "data": null, "orderable": false, "className": "rightAlign", render: function (data, type, row, meta) {
                return meta.row + meta.settings._iDisplayStart + 1;
            }},
            {"targets": 1, "data": "stateCode"},
            {"targets": 2, "data": "stateName"},
            {"targets": 3, "data": "courtName"},
            {"targets": 4, "data": "benchCode"},
            {"targets": 5, "data": "benchName"}
        ],
        "order": [[1, "asc"]],
        "buttons": [
            "excelHtml5",
            "csvHtml5",
            "pdfHtml5"
        ],
        "fnServerParams": function (data) {
            data['order'].forEach(function (items, index) {
                data['order'][index]['column'] = data['columns'][items.column]['data'];
            });
        }
    });
    $('div.dataTables_filter input').attr('title', 'Press enter to filter');
    $('div.dataTables_filter input').focus();
    $('div.dataTables_filter input').unbind();
    $('div.dataTables_filter input').bind('keyup change', function (e) {
        if (e.keyCode === 13) {
            tbl.search($(this).val()).draw();
        }
    });
});