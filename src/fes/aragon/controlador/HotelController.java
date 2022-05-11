package fes.aragon.controlador;

import java.net.URL;
import java.util.ResourceBundle;

import fes.aragon.modelo.Hotel;
import fes.aragon.modelo.Hoteles;
import fes.aragon.modelo.TipoError;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class HotelController extends BaseController implements Initializable {
	private Hotel hotel;
	private String mensajes = "";
	@FXML
	private Button btnAceptar;

	@FXML
	private Button btnCancelar;

	@FXML
	private Button btnGerente;

	@FXML
	private Button btnHabitacion;

	@FXML
	private TextField txtCorreo;

	@FXML
	private TextField txtDireccion;

	@FXML
	private TextField txtNombre;

	@FXML
	private TextField txtTelefono;

	@FXML
	void cancelarHotel(ActionEvent event) {
		this.cerrarVentana(btnCancelar);
	}

	@FXML
	void nuevaHabitacion(ActionEvent event) {
		if (Hoteles.getInstancia().isModificarHotel()) {
			this.nuevaVentana("ModificarHabitacion");
		} else {
			this.nuevaVentana("Habitacion");
		}

	}

	@FXML
	void nuevoGerente(ActionEvent event) {
		this.nuevaVentana("Gerente");
	}

	@FXML
	void nuevoHotel(ActionEvent event) {
		if (this.verificar()) {
			hotel.setNombre(this.txtNombre.getText());
			hotel.setDireccion(this.txtDireccion.getText());
			hotel.setCorreo(this.txtCorreo.getText());
			hotel.setTelefono(this.txtTelefono.getText());
			if (Hoteles.getInstancia().isModificarHotel()) {
				Hoteles.getInstancia().getGrupoHoteles().set(Hoteles.getInstancia().getIndice(), hotel);
				Hoteles.getInstancia().setIndice(-1);
				Hoteles.getInstancia().setModificarHotel(false);
				Hoteles.getInstancia().setIndiceHabitacion(-1);
			} else {
				Hoteles.getInstancia().getGrupoHoteles().set(Hoteles.getInstancia().getGrupoHoteles().size() - 1,
						hotel);
			}
			this.cerrarVentana(btnAceptar);
		} else {
			this.ventanaEmergente("Mensaje", "Datos no Correctos", this.mensajes);
			this.mensajes = "";
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.verificarEntrada(txtCorreo, TipoError.CORREO);
		this.verificarEntrada(txtTelefono, TipoError.TELEFONO);
		if (Hoteles.getInstancia().isModificarHotel()) {
			this.hotel = Hoteles.getInstancia().getGrupoHoteles().get(Hoteles.getInstancia().getIndice());
			this.txtNombre.setText(hotel.getNombre());
			this.txtDireccion.setText(hotel.getDireccion());
			this.txtCorreo.setText(hotel.getCorreo());
			this.txtTelefono.setText(hotel.getTelefono());
		} else {
			hotel = Hoteles.getInstancia().getGrupoHoteles().get(Hoteles.getInstancia().getGrupoHoteles().size() - 1);

		}
	}

	private boolean verificar() {
		boolean valido = true;
		if ((this.txtNombre.getText() == null)
				|| (this.txtNombre.getText() != null && this.txtNombre.getText().isEmpty())) {
			this.mensajes += "El nombre del hotel no es valido, es vacio\n";
			valido = false;
		}
		if ((this.txtDireccion.getText() == null)
				|| (this.txtDireccion != null && this.txtDireccion.getText().isEmpty())) {
			this.mensajes += "La dirección del hotel no es valido, es vacio\n";
			valido = false;
		}
		if ((this.txtCorreo.getText() == null) || (this.txtCorreo != null && this.txtCorreo.getText().isEmpty())) {
			this.mensajes += "El correo del hotel no es valido,  vacio\n";
			valido = false;
		}
		if ((this.txtCorreo.getText() == null) || (this.txtCorreo != null && !this.txtCorreo.getText().isEmpty())) {
			if (!this.correoValido) {
				this.mensajes += "El correo del hotel no es valido, esta mal estructurado\n";
				valido = false;
			}

		}
		if ((this.txtTelefono.getText() == null)
				|| (this.txtTelefono != null && this.txtTelefono.getText().isEmpty())) {
			this.mensajes += "El teléfono del hotel no es valido, es vacio\n";
			valido = false;
		}
		if ((this.txtTelefono.getText() == null)
				|| (this.txtTelefono != null && !this.txtTelefono.getText().isEmpty())) {
			if (!this.telefonoValido) {
				this.mensajes += "El teléfono del hotel no es valido, minimo=10,maximo=10 números\n";
				valido = false;
			}

		}
		return valido;
	}

}