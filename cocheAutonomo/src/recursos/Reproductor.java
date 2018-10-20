/** @file Reproductor.java
 *  @brief Clase que permite crear un reproductor para reproducir musica .mp3
 *  @authors
 *  Nombre        | Apellido       | Email                                |
 *  ------------- | -------------- | ------------------------------------ |
 *  Iker	      | Mendi          | iker.mendi@alumni.mondragon.edu      |
 *  Julen	      | Uribarren	   | julen.uribarren@alumni.mondragon.edu |
 *  Unai          | Iraeta         | unai.iraeta@alumni.mondragon.edu     |
 *  Aitor         | Barreiro       | aitor.barreirom@alumni.mondragon.edu |
 *  @date 23/01/2017
 */

/** @brief Paquete recursos
 */
package recursos;

/** @brief Librer�as
 */
import java.io.File;
import javazoom.jlgui.basicplayer.BasicPlayer;

/** @brief Clase Reproductor
 */
public class Reproductor {
	/** @brief Atributos
	 */
	private BasicPlayer player;

	/** @brief Constructor de la clase
	 */
	public Reproductor() {
		player = new BasicPlayer();
	}
	
	/** @brief M�todo para reproducir el sonido
	 *  @return void
	 */
	public void Reproducir() throws BasicPlayerException {
		player.play();
	}

	/** @brief M�todo para abrir el fichero .mp3
	 * 	@param ruta Nombre de la ruta del fichero
	 *  @return void
	 */
	public void AbrirFichero(String ruta) throws BasicPlayerException {
		player.open(new File(ruta));
	}

	/** @brief M�todo para pausar el sonido
	 *  @return void
	 */
	public void Pausar() throws BasicPlayerException {
		player.pause();
	}

	/** @brief M�todo para continuar reproduciendo el sonido
	 *  @return void
	 */
	public void Continuar() throws BasicPlayerException {
		player.resume();
	}

	/** @brief M�todo para parar el sonido
	 *  @return void
	 */
	public void Parar() throws BasicPlayerException {
		player.stop();
	}
	
}