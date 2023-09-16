package ar.edu.unlu.poo.academia;

import java.util.ArrayList;

public class Academia {
    private Secretaria secretaria = new Secretaria();
    private ArrayList<String> nombresDisciplinas = new ArrayList<>();
    public void crearDisciplina(String nombre, Nivel nivel, String dias, String horario, String profe, int comision){
        for (int i = 0; i < nombresDisciplinas.size();i++){
            if (nombresDisciplinas.get(i).equals(nombre)){
                agregarDiagramacion(nombre, nivel, dias, horario, profe, comision);
                return;
                //si la disciplina ya existe solo le agrego su nueva diagramacion
            }
        }
        //sino creo la displina y la diagramacion
        Disciplinas nueva_disciplina = new Disciplinas();
        nueva_disciplina.setNombreDisciplina(nombre);
        nombresDisciplinas.add(nombre);
        Diagramaciones nueva_diagramacion = new Diagramaciones();
        nueva_diagramacion.setNivelDisciplina(nivel);
        nueva_diagramacion.setDiasClase(dias);
        nueva_diagramacion.setHorarios(horario);
        nueva_diagramacion.setProfesor(profe);
        nueva_diagramacion.setComision(comision);
        nueva_disciplina.agregarComisiones(nueva_diagramacion);
        secretaria.agregarListaDisciplinas(nueva_disciplina);
    }

    private void agregarDiagramacion(String nombre, Nivel nivel, String dias, String horario, String profe, int comision) {
        ArrayList<Disciplinas> listaDisciplinasAux = secretaria.getListaDisciplinas();
        for (int i = 0; i < listaDisciplinasAux.size(); i++){
            if (listaDisciplinasAux.get(i).getNombreDisciplina().equals(nombre)){
                Diagramaciones nuevaDiagramacion = new Diagramaciones();
                nuevaDiagramacion.setNivelDisciplina(nivel);
                nuevaDiagramacion.setDiasClase(dias);
                nuevaDiagramacion.setHorarios(horario);
                nuevaDiagramacion.setProfesor(profe);
                nuevaDiagramacion.setComision(comision);
                listaDisciplinasAux.get(i).agregarComisiones(nuevaDiagramacion);
            }
        }
    }

    public CredencialAlumno inscripcion(boolean es_alumno, String nombre_apellido, String DNI, String numero, String disciplina, CredencialAlumno credencial, Nivel nivel){
        if (!es_alumno){
            CredencialAlumno nueva_credencial;
            nueva_credencial = secretaria.inscribirse_primera_vez(nombre_apellido, DNI, numero, disciplina, nivel);
            return nueva_credencial;
        }else {
            credencial = secretaria.inscribirse(credencial, disciplina, nivel);
            return credencial;
        }
    }

    public  void registrarse (CredencialAlumno credencial, String disciplina, Nivel nivel){
        secretaria.registrar_asistencia(credencial, disciplina, nivel);
    }

    public void reporteMayorIngreso(){
        String displinaMayorI = "";
        double cantidadMayorIngreso = 0;
        //calcular ingresos aca?
        ArrayList<Disciplinas> listaDisplinasAux = secretaria.getListaDisciplinas();
        for (int i = 0; i < listaDisplinasAux.size(); i++){
            if (listaDisplinasAux.get(i).getIngresosTotalesDisciplina() > cantidadMayorIngreso) {
                displinaMayorI = listaDisplinasAux.get(i).getNombreDisciplina();
                cantidadMayorIngreso = listaDisplinasAux.get(i).getIngresosTotalesDisciplina();
            }
        }
        System.out.println("La displina con mayor ingresos es: " + displinaMayorI + "con " + cantidadMayorIngreso + "$ en ingresos");
    }


    public void listadoAlumnosAsistidos(String profe){
        System.out.println("Listado de alumnos que asistieron a la clase del profesor " + profe + ": ");
        ArrayList<Asistencia> asistenciasAux = secretaria.getAsistenciasAlumno();
        double importeTotal = 0;
        for (int i = 0; i < asistenciasAux.size(); i++){
            if (asistenciasAux.get(i).getComisionElegida().getProfesor().equals(profe)){
                importeTotal += (asistenciasAux.get(i).cant_asistencias * 10);
                System.out.println("El alumno " + asistenciasAux.get(i).alumno.getNombre_apellido() + " Asistio a las clases de " + asistenciasAux.get(i).disciplina +
                        " con un total de " + asistenciasAux.get(i).cant_asistencias + " asistencias");
            }
        }
        System.out.println("El profesor " + profe + "va a cobrar" + importeTotal + "$");
    }
}