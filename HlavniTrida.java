package sp;

/**
 * Hlavn� t��da cel� semestr�ln� pr�ce.
 * V t�to t��d� se spou�t� pouze nezbytn� procesy.
 *
 * @author  Pavel Pr�cha a Tom� Sl�va
 */
public class HlavniTrida {

	public static void main(String[] args) {

		NacitaniVstupnihoSouboru.nactiVstup();
		
		NacitaniSimulace.nactiSimulaci();
	}
}