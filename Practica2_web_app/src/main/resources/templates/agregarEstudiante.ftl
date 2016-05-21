<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Práctica 2</title>
</head>
<body>
    <h1>${Titulo}</h1>
    <form action="/studentList/" method="post">
        Matricula: <input name="matricula" type="text"/><br/>
        Nombre: <input name="nombre" type="text"/><br/>
        Apellido: <input name="apellido" type="text"/><br/>
        Teléfono: <input name="telefono" type="text"/><br/>
        <button name="Agregar" type="submit">Agregar</button>
    </form>

</body>
</html>