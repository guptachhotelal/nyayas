<%@ page language="java" contentType="text/html;  charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>High Courts</title>
<%@include file="../common/include.jsp"%>
<script type="text/javascript" src="js/hc.js"></script>
<style type="text/css">
body {
	overflow-y: scroll;
	height: 100%;
}

.dataTables_filter {
	float: left;
}

.dataTables_filter input {
	min-width: 300px;
}

#tblList_processing {
	z-index: 1;
}

.dataTables_length select {
	margin-top: 10px;
}

.rightAlign {
	text-align: right;
}

.centerAlign {
	text-align: center;
}
</style>
</head>
<body>
	<div class="container-fluid" style="width: 80%; margin-top: 10px;">
		<table id="tblList"
			class="table table-striped table-bordered table-sm nowrap responsive"
			style="width: 100%;">
			<thead>
				<tr>
					<th class="centerAlign">Sr. No</th>
					<th class="centerAlign">State Code</th>
					<th class="centerAlign">State Name</th>
					<th class="centerAlign">Court Name</th>
					<th class="centerAlign">Bench Code</th>
					<th class="centerAlign">Bench Name</th>
				</tr>
			</thead>
		</table>
	</div>
	<%@include file="../common/footer.jsp"%>
</body>
</html>