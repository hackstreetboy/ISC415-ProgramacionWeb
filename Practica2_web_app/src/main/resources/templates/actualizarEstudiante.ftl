<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Práctica 2</title>
</head>
<body>
<h1>${Titulo}</h1>
<form action="/showStudentInfo/${Student.studentID?string["0"]}/" method="post">
    Nombre: <input name="nombre" type="text" value="${Student.name}"/><br/>
    Apellido: <input name="apellido" type="text" value="${Student.lastName}"/><br/>
    Teléfono: <input name="telefono" type="text" value="${Student.phone}"/><br/>
    <button name="Actualizar" type="submit">Actualizar</button>
</form>

</body>
</html>