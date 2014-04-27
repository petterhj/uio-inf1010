// Import
import java.util.ArrayList;


//	Klasse: Rute
// =================================================================================
class Rute {
	// Variabler
	private int verdi;

	private Brett brett;
	private Rad rad;
	private Kolonne kolonne;
	private Boks boks;

	public Rute neste;

	// Konstruktør
	Rute(int verdi, Brett brett) {
		this.verdi = verdi;
		this.brett = brett;
	}

	// Fyll ut resten av brettet
	public void fyllUtRestenAvBrettet() {
		this.hentBrett().tomBrett(this);

		if (this instanceof VariabelRute)
			for (int verdi : this.finnMuligeVerdier()) {
				this.settVerdi(verdi);

				if (this.neste != null)
					this.neste.fyllUtRestenAvBrettet();
			}

		if (this instanceof StatiskRute)
			if (this.neste != null)
				this.neste.fyllUtRestenAvBrettet();

		
		// Siste rute
		if (this.neste == null)
			// Sjekk om komplett
			if (this.hentBrett().erUtfylt()) {
				// Lagre kopi av løst brett i beholder
				try {
					Brett losning = (Brett) this.hentBrett().clone();

					this.hentBrett().hentSpill().hentBeholder().settInn(losning);
				} catch(CloneNotSupportedException e) {
					System.out.println("[FEIL] Løsningen ble ikke lagret!");
				}
			}
	}

	// Finn mulige verdier i felter
	public ArrayList<Integer> finnMuligeVerdier() {
		ArrayList<Integer> verdier = new ArrayList<Integer>();

		for (int i = 1; i <= this.rad.storrelse(); i++)
			// Sjekk rad
			if (!this.rad.inneholderVerdi(i))
				// Sjekk kolonne
				if (!this.kolonne.inneholderVerdi(i))
					// Sjekk boks
					if (!this.boks.inneholderVerdi(i))
						verdier.add(i);
		
		return verdier;
	}

	// Sett forelder-rad
	public void settRad(Rad r) {
		this.rad = r;
	}

	// Sett forelder-kolonne
	public void settKolonne(Kolonne k) {
		this.kolonne = k;
	}

	// Sett forelder-boks
	public void settBoks(Boks b) {
		this.boks = b;
	}

	// Hent forelder-rad
	public Rad hentRad() {
		return this.rad;
	}

	// Hent forelder-boks
	public Boks hentBoks() {
		return this.boks;
	}

	// Hent brett
	public Brett hentBrett() {
		return this.brett;
	}

	// Sett nestepeker
	public void settNeste(Rute n) {
		this.neste = n;
	}

	// Sett verdi
	public void settVerdi(int verdi) {
		this.verdi = verdi;
	}

	// Hent verdi
	public int hentVerdi() {
		return this.verdi;
	}
}


// 	Klasse: StatiskRute
// =================================================================================
class StatiskRute extends Rute {
	// Konstruktør
	StatiskRute(int verdi, Brett brett) {
		super(verdi, brett);
	}
}


// 	Klasse: VariabelRute
// =================================================================================
class VariabelRute extends Rute {
	// Konstruktør
	VariabelRute(Brett brett) {
		super(0, brett);
	}
}