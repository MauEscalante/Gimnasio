package modelo.usuarios;

import modelo.sedes.Actividad;
import modelo.sedes.Clase;
import modelo.sedes.Sede;
import modelo.supertlon.GimnasioSingleton;
import modelo.utilidad.Nivel;

public class SoporteTecnico extends Usuario {
	
	

	public SoporteTecnico(String nombre, String apellido, String dni) {
		super(nombre, apellido, dni);
		
	}
	
	public void crearSede(GimnasioSingleton gimnasio, String localidad, Nivel nivel, int capidad, String tipoSede) throws ExisteLocalidadException {
		
		for (int i = 0; i < gimnasio.getSedes().size(); i++) {
			if(gimnasio.getSedes().get(i).getLocalidad() == localidad) {
				throw new ExisteLocalidadException(); 
			}
				
		}
		
		gimnasio.agregarSede(new Sede(localidad, nivel, capidad, tipoSede));
		
	}
	
	public void cargarTipoClase(Clase clase, Actividad actividad) {
		
		clase.setActividad(actividad);		
		
	}

	@Override
	public boolean soySoporteTecnico() {
		return true;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return this.id;
	}	
	
	

}
