package dialogicagents;

import appboot.LARVABoot;
import static crypto.Keygen.getHexaKey;

public class Main {

    static String[] stars = {"Zhang", "Salm", "Wasat", "Irena", "Petra", "Subra", "Uruk", "Gudja", "Rigel", "Yed", "Nihal", "Ain", "Beid", "Bibha", "Wezen", "Errai", "Yed", "Alcor", "Alya", "Monch", "Shama", "Arkab", "Koeia", "Bubup", "Gloas", "Merga", "Ankaa", "Arneb", "Hunor", "Cursa", "Chara", "Naos", "Tupa", "Arkab", "Atria", "Fuyue", "Acrux", "Matar", "Ogma", "Intan", "Skat", "Fulu", "Algol", "Kraz", "Tojil", "Kamuy", "Lich", "Tania", "Alkes", "Hamal", "Syrma", "Vega", "Acrab", "Avior", "Homam", "Deneb", "Diya", "Ginan", "Nunki", "Rigil", "Tabit", "Felis", "Biham", "Mira", "Baten", "Segin", "Izar", "Merak", "Zosma"};
    static String alias = "Alula";   /// Fill in your alias as in https://docs.google.com/document/d/1zjHWhPv2hXM-VSQ88pj3HkYUCcxANZaJnFb0H6OxRLc/edit

    public static void main(String[] args) {
        LARVABoot boot = new LARVABoot();
        boot.Boot("localhost", 1099);
        // Otherwise our server always run JADE  ;-)
//         boot.Boot("isg2.ugr.es", 1099);  // Our server is isg2
        // boot.Boot("150.214.190.126", 1099);  // Should there be problems with DNS
        boot.loadAgent(alias + "-F" + getHexaKey(3), Follower.class);
        boot.loadAgent(alias + "-S" + getHexaKey(3), Starter.class);
        boot.loadAgent(alias + "-B" + getHexaKey(3), Batch.class);
        boot.loadAgent(alias + "-I" + getHexaKey(3), Interleaved.class);

        // Closes the container and exits
        boot.WaitToShutDown();

    }

}
