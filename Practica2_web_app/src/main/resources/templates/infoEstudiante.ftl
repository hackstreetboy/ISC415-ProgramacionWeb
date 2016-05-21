<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>Práctica 2</title>
</head>
<body>
    <h1>${Titulo}</h1>
    <h4>Matrícula: ${Student.studentID?string["0"]}</h4>
    <h4>Nombre: ${Student.name} ${Student.lastName}</h4>
    <h4>Teléfono: ${Student.phone}</h4>
    <a href="/studentList/">Volver a Listado</a>
</body>
</html>