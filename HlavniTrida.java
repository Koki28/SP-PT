package sp;

/**
 * Hlavní tøída celé semestrální práce.
 * V této tøídì se spouští pouze nezbytné procesy.
 *
 * @author  Pavel Prùcha a Tomáš Slíva
 */
public class HlavniTrida {

	public static void main(String[] args) {

		NacitaniVstupnihoSouboru.nactiVstup();
		
		NacitaniSimulace.nactiSimulaci();
	}
}