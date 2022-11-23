package dialogiclient;

import agents.BasicPlayer;
import agents.NPCDialogicPlayer;
import appboot.JADEBoot;
import appboot.LARVABoot;
import static crypto.Keygen.getHexaKey;

public class Main {

    static String[] stars = {"Zhang",
"Salm",
"Wasat",
"Irena",
"Petra",
"Subra",
"Uruk",
"Gudja",
"Rigel",
"Yed",
"Nihal",
"Ain",
"Beid",
"Bibha",
"Wezen",
"Errai",
"Yed",
"Alcor",
"Alya",
"Monch",
"Shama",
"Arkab",
"Koeia",
"Bubup",
"Gloas",
"Merga",
"Ankaa",
"Arneb",
"Hunor",
"Cursa",
"Chara",
"Naos",
"Tupa",
"Arkab",
"Atria",
"Fuyue",
"Acrux",
"Matar",
"Ogma",
"Intan",
"Skat",
"Fulu",
"Algol",
"Kraz",
"Tojil",
"Kamuy",
"Lich",
"Tania",
"Alkes",
"Hamal",
"Syrma",
"Vega",
"Acrab",
"Avior",
"Homam",
"Deneb",
"Diya",
"Ginan",
"Nunki",
"Rigil",
"Tabit",
"Felis",
"Biham",
"Mira",
"Baten",
"Segin",
"Izar",
"Merak",
"Zosma"};

    public static void main(String[] args) {
        int nPlayers = 10; //stars.length;
        LARVABoot boot = new LARVABoot();
        boot.Boot("localhost", 1099);
        // Otherwise our server always run JADE  ;-)
//         boot.Boot("isg2.ugr.es", 1099);  // Our server is isg2
        // boot.Boot("150.214.190.126", 1099);  // Should there be problems with DNS
            boot.loadAgent("Alula-F"+getHexaKey(3), Follower.class);
            boot.loadAgent("Alula-S"+getHexaKey(3), Starter.class);
            boot.loadAgent("Alula-I"+getHexaKey(3), Interleaved.class);
//            boot.loadAgent("Alula-BT"+getHexaKey(3), BasicTemplates.class);
//            boot.loadAgent("Furud-NPC"+getHexaKey(3), AutoSequential.class);
//            boot.loadAgent("Rana-NPC"+getHexaKey(3), AutoSequential.class);

        // Closes the container and exits
        boot.WaitToShutDown();

    }

}
