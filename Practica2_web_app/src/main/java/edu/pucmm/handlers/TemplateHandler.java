package edu.pucmm.handlers;

import edu.pucmm.data.Constants;
import edu.pucmm.data.Student;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;

import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import static spark.Spark.*;

/**
 * Created by MEUrena on 5/26/16.
 * All rights reserved.
 */
public class TemplateHandler {

    DatabaseHandler dbHandler = new DatabaseHandler(Constants.DB_DRIVER, Constants.DB_URL);

    public void startApp() {
        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(TemplateHandler.class, "/templates");
        FreeMarkerEngine engine = new FreeMarkerEngine(configuration);

        listAllStudentsTemplate(engine);
        addStudentTemplate(engine);
        showStudentInfoTemplate(engine);
        updateStudentTemplate(engine);
        deleteStudentTemplate();
    }

    /***
     * http://localhost:4567/addStudent/
     * @param engine
     */
    public void addStudentTemplate(FreeMarkerEngine engine) {
        get("/addStudent/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("Titulo", "Agregar Nuevo Estudiante");
            return new ModelAndView(attributes, "agregarEstudiante.ftl");
        }, engine);
    }

    /***
     * http://localhost:4567/deleteStudent/:studentID/
     */
    public void deleteStudentTemplate() {
        get("/deleteStudent/:studentID/", (request, response) -> {
            int studentID = Integer.parseInt(request.params(":studentID"));

            dbHandler.deleteItemWithId(studentID);

            response.redirect("/studentList/");
            return "";
        });
    }

    /***
     * http://localhost:4567/studentList/
     * @param engine
     */
    public void listAllStudentsTemplate(FreeMarkerEngine engine) {
        get("/studentList/", (request, response) -> {
            List<Student> studentList = dbHandler.getAllItemsFromTable();
            String htmlString = generateDynamicRows(dbHandler.getAllItemsFromTable());
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("Titulo", "Listado de Estudiantes");
            attributes.put("tableRows", htmlString);
            return new ModelAndView(attributes, "listadoEstudiantes.ftl");
        }, engine);

        post("/studentList/", (request, response) -> {
            int studentID = Integer.parseInt(request.queryParams("matricula"));
            String name = request.queryParams("nombre");
            String lastName = request.queryParams("apellido");
            String phone = request.queryParams("telefono");
            Student student = new Student(studentID, name, lastName, phone);

            dbHandler.insertItemIntoTable(student);

            String htmlString = generateDynamicRows(dbHandler.getAllItemsFromTable());

            Map<String, Object> attributes = new HashMap<>();
            attributes.put("Titulo", "Listado de Estudiantes");
            attributes.put("tableRows", htmlString);

            return new ModelAndView(attributes, "listadoEstudiantes.ftl");
        }, engine);

        delete("/studentList/", (request, response) -> {
            int studentID = Integer.parseInt(request.queryParams("eliminar"));

            dbHandler.deleteItemWithId(studentID);

            String htmlString = generateDynamicRows(dbHandler.getAllItemsFromTable());

            Map<String, Object> attributes = new HashMap<>();
            attributes.put("Titulo", "Listado de Estudiantes");
            attributes.put("tableRows", htmlString);

            return new ModelAndView(attributes, "listadoEstudiantes.ftl");
        }, engine);
    }

    /***
     * http://localhost:4567/showStudentInfo/
     * @param engine
     */
    public void showStudentInfoTemplate(FreeMarkerEngine engine) {
        get("/showStudentInfo/:studentID/", (request, response) -> {
            Student student = dbHandler.getItemWithId(Integer.parseInt(request.params(":studentID")));
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("Titulo", "Información de Estudiante");
            attributes.put("Student", student);
            return new ModelAndView(attributes, "infoEstudiante.ftl");
        }, engine);

        post("/showStudentInfo/:studentID/", (request, response) -> {
            int studentID = Integer.parseInt(request.params(":studentID"));
            String name = request.queryParams("nombre");
            String lastName = request.queryParams("apellido");
            String phone = request.queryParams("telefono");
            Student student = new Student(studentID, name, lastName, phone);

            dbHandler.updateItemInTable(student);

            Map<String, Object> attributes = new HashMap<>();
            attributes.put("Titulo", "Información de Estudiante");
            attributes.put("Student", student);
            return new ModelAndView(attributes, "infoEstudiante.ftl");
        }, engine);
    }

    /***
     * http://localhost:4567/updateStudent/
     * @param engine
     */
    public void updateStudentTemplate(FreeMarkerEngine engine) {
        get("/updateStudent/:studentID/", (request, response) -> {
            Student student = dbHandler.getItemWithId(Integer.parseInt(request.params(":studentID")));
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("Titulo", "Actualizando Estudiante");
            attributes.put("Student", student);
            return new ModelAndView(attributes, "actualizarEstudiante.ftl");
        }, engine);
    }

    private String generateDynamicRows(List<Student> list) {
        String htmlString = "";
        for (Student st : list) {
            htmlString += "<tr onclick=\"document.location = '/showStudentInfo/" + st.getStudentID() + "/';\">" + "\n\t\t" +
                    "<td>" + st.getStudentID() + "</td>" + "\n\t\t" +
                    "<td>" + st.getName() + "</td>" + "\n\t\t" +
                    "<td>" + st.getLastName() + "</td>" + "\n\t\t" +
                    "<td>" + st.getPhone() + "</td>" + "\n\t\t" +
                    "<td>" + "\n\t\t\t" +
                    "<a href=\"/updateStudent/" + st.getStudentID() + "/\">Actualizar</a>" + "\n\t\t\t" +
                    "<a href=\"/deleteStudent/" + st.getStudentID() + "/\">Eliminar</a>" + "\n\t\t\t" +
                    "</td>" + "\n\t    " +
                    "</tr>\n\t    ";
        }

        return htmlString;
    }

}
