/*
     INF1010 - Oblig 1
    ---------------------------
     petterju
*/
class Oblig1 {
    public static void main(String[] args) {
        Tester test = new Tester();
        test.kjor();
    }
}


// Class: Tester
class Tester {
    // Students
    Person ask = new Person("Ask", 3);
    Person dana = new Person("Dana", 3);
    Person tom = new Person("Tom", 3);
    Person brynjulf = new Person("Brynjulf", 3);

    // Run tests
    public void kjor() {
        // Student 1: Ask
        ask.blirKjentMed(brynjulf);
        ask.blirKjentMed(tom);
        ask.blirKjentMed(dana);
        ask.blirUvennMed(dana);
        ask.blirUvennMed(tom);
        ask.blirForelsketI(brynjulf);

        // Student 2: Dana
        dana.blirKjentMed(ask);
        dana.blirKjentMed(tom);
        dana.blirKjentMed(brynjulf);
        dana.blirUvennMed(brynjulf);
        dana.blirForelsketI(tom);

        // Student 3: Tom
        tom.blirKjentMed(brynjulf);
        tom.blirKjentMed(ask);
        tom.blirKjentMed(dana);
        tom.blirUvennMed(ask);
        tom.blirUvennMed(brynjulf);
        tom.blirForelsketI(dana);

        // Student 4: Meg
        brynjulf.blirKjentMed(ask);
        brynjulf.blirKjentMed(dana);
        brynjulf.blirKjentMed(tom);

        // Oversikt
        brynjulf.skrivUtAltOmMeg();
        ask.skrivUtAltOmMeg();
        dana.skrivUtAltOmMeg();
        tom.skrivUtAltOmMeg();

        // Bestevennen til Dana
        /*
        Person[] danasVenner = dana.hentVenner();
        System.out.print("Danas venner (" + dana.antVenner() + "): ");
        for (Person p : danasVenner)
            System.out.print(p.hentNavn() + " ");
        System.out.println("\nDanas bestevenn: " + dana.hentBestevenn().hentNavn());
        */
    }
}


// Class: Person
class Person {
    // Variables
    private String navn;
    private Person[] kjenner;
    private Person[] likerikke;
    private Person forelsketi;
    private Person sammenmed;

    // Constructor
    Person (String n, int lengde) {
        this.navn = n;

        this.kjenner = new Person[lengde];
        this.likerikke = new Person[lengde];
    }

    // Return name
    public String hentNavn() {
        return this.navn;
    }

    // Check if acquainted
    public boolean erKjentMed(Person p) {
        return this.inArray(p, kjenner);
    }

    // Add acquaintance
    public void blirKjentMed(Person p) {
        if (!this.erSegSelv(p))
            for (int i = 0; i < this.kjenner.length; i++)
                if (this.kjenner[i] == null) {
                    this.kjenner[i] = p;
                    break;
                }
    }

    // Set love interest
    public void blirForelsketI(Person p) {
        if (!this.erSegSelv(p)) this.forelsketi = p;
    }

    // Set acquaintance as enemy
    public void blirUvennMed(Person p) {
        if (!this.erSegSelv(p))
            for (int i = 0; i < this.likerikke.length; i++)
                if (this.likerikke[i] == null) {
                    this.likerikke[i] = p;
                    break;
                }
    }

    // Check if friend
    public boolean erVennMed(Person p) {
        if (inArray(p, kjenner) && !inArray(p, likerikke)) return true;
        return false;
    }

    // Set as friend (remove from enemies)
    public void blirVennMed(Person p) {
        for (int i = 0; i < this.likerikke.length; i++)
            if (this.likerikke[i] == p) {
                this.likerikke[i] = null;
                break;
            }
    }

    // Print friend list
    public void skrivUtVenner() {
        System.out.println("Venner:");

        for (Person p : this.hentVenner())
            System.out.println(" > " + p.hentNavn());
    }

    // Return best friend
    public Person hentBestevenn() {
        Person[] venner = this.hentVenner();
        return venner[0];
    }

    // Return friends
    public Person[] hentVenner() {
        Person[] venner = new Person[this.antVenner()];
        int vi = 0;

        for (int i = 0; i < this.kjenner.length; i++)
            if (this.erVennMed(this.kjenner[i]))
                venner[vi++] = this.kjenner[i];

        return venner;
    }

    // Return friend count
    public int antVenner() {
        int ant = 0;

        for (Person p : this.kjenner)
            if (this.erVennMed(p)) ant++;

        return ant;
    }

    // Print acquaintances
    public void skrivUtKjenninger() {
        for (Person p : this.kjenner)
            if (p != null) System.out.print(p.hentNavn( ) + " ");
        System.out.println("");
    }

    // Print enemies
    public void skrivUtLikerIkke( ) {
        for (Person p : this.likerikke)
            if (p != null) System.out.print(p.hentNavn( ) + " ");
        System.out.println("");
    }

    // Print profile
    public void skrivUtAltOmMeg ( ) {
        System.out.print(this.navn + " kjenner: ");
        skrivUtKjenninger();

        if (this.forelsketi != null)
            System.out.println(this.navn + " er forelsket i " + this.forelsketi.hentNavn());

        if (this.likerikke[0] != null) {
            System.out.print(this.navn + " liker ikke: ");
            skrivUtLikerIkke();
        }
    }

    // Check if person in array
    private boolean inArray(Person n, Person[] h) {
        for (Person p : h)
            if (p == n) return true;
        return false;
    }

    // Check if self
    private boolean erSegSelv(Person p) {
        if (this == p) return true;
        return false;
    }
}
